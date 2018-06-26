/*
 * Pixel Dungeon 3D
 * Copyright (C) 2016-2018 Alex Fomins
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import java.util.HashMap;
import java.util.LinkedList;
import com.matalok.pd3d.shared.Logger;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescSprite 
  extends Desc {
    //**************************************************************************
    // DescSprite
    //**************************************************************************
    public Integer obj_id;
    public String obj_type;
    public String texture;
    public Integer tile_offset;
    public Integer tile_width;
    public Integer tile_height;
    public LinkedList<DescRect> rects;
    public HashMap<String, DescAnim> anims;

    //--------------------------------------------------------------------------
    public DescRect GetFirstAnimRect(String anim_name) {
        DescAnim anim = anims.get(anim_name);
        if(anim == null) {
            Logger.e(
              "Failed to get first rect of sprite, animation missing :: anim=%s",
              anim_name);
            return null;
        }

        if(anim.frames == null || anim.frames.size() == 0) {
            Logger.e(
              "Failed to get first rect of sprite, frames array is empty :: anim=%s",
              anim_name);
            return null;
        }

        int rect_idx = anim.frames.getFirst();
        if(rect_idx < 0 || rect_idx >= rects.size()) {
            Logger.e(
              "Failed to get first rect of sprite, wrong rect index :: anim=%s idx=%d",
              anim_name, rect_idx);
            return null;
        }
        return rects.get(rect_idx);
    }

    //--------------------------------------------------------------------------
    private static HashMap<String, LinkedList<DescRect>> rect_map = 
      new HashMap<String, LinkedList<DescRect>>();
    public void FinalizeRects(int tx_width, int tx_height) {
        // Build tilemap rects
        if(rects == null) {
            Utils.Assert(tile_width > 0 && tile_height > 0, 
              "Failed to finalize sprite rects, tile size missing :: width=%d height=%d",
              tile_width, tile_height);

            // Generate rect key
            String key = String.format("%d-%d-%d-%d", 
              tx_width, tx_height, tile_width, tile_height);

            // Read existing rect from map
            if(DescSprite.rect_map.containsKey(key)) {
                rects = DescSprite.rect_map.get(key);
                return;
            }

            // Create new rect list
            rects = new LinkedList<DescRect>();

            // Generate new rects
            int col_num = tx_width / tile_width;
            int row_num = tx_height / tile_height;
            for(int y = 0; y < row_num; y++) {
                for(int x = 0; x < col_num; x++) {
                    DescRect rect = new DescRect();
                    rect.x = x * tile_width; rect.width = tile_width;
                    rect.y = y * tile_height; rect.height = tile_height;
                    rects.add(rect);
                }
            }

            // Save rects in map
            DescSprite.rect_map.put(key, rects);
        }

        // Calculate unit-size of each rect
        if(!rects.getFirst().is_unit_size_ready) {
            for(DescRect rect : rects) {
                rect.CalculateUnitSize(tx_width, tx_height);
            }
        }
    }

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(obj_id, obj_type, texture, tile_offset, anims)) {
            return OnValidateError("param error", null);
        }

        // If rects is empty then tile size should be defined
        if(rects == null && (tile_width == null || tile_height == null)) {
            return OnValidateError("tile size error", null);
        }

        // Rects
        if(rects != null && 
          !Desc.ValidateIt(rects.iterator(), "rect", null)) {
            return false;
        }

        // Anims
        if(!Desc.ValidateIt(anims.values().iterator(), "anim", null)) {
            return false;
        }
        return true;
    }
}
