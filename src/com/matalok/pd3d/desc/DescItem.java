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
import java.util.LinkedList;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescItem 
  extends Desc {
    //**************************************************************************
    // ItemDesc
    //**************************************************************************
    public Integer item_id;
    public Integer sprite_id;
    public Integer level;
    public Integer level_effective;
    public Integer count;
    public Float durability;
    public String name;
    public String name_real;
    public String type;
    public String info;
    public String default_action;
    public Boolean is_broken;
    public Boolean is_broken_visibly;
    public Boolean is_equipped;
    public Boolean is_cursed;
    public Boolean is_cursed_known;
    public Boolean is_cursed_visibly;
    public Boolean is_unique;
    public Boolean is_level_known;
    public Boolean is_selectable;
    public Boolean is_identified;
    public DescStringInst txt_top_left;
    public DescStringInst txt_top_right;
    public DescStringInst txt_bottom_right;
    public DescStringInst txt_bottom_left;
    public LinkedList<String> actions;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(item_id, sprite_id, level, level_effective, count, name, 
          name_real, type, info, is_broken, is_broken_visibly, is_equipped, 
          is_cursed, is_cursed_known, is_cursed_visibly, is_unique, 
          is_level_known, is_selectable, is_identified)) {
            return OnValidateError("param error", null);
        }

        // Quickslot
        if(!Desc.ValidateArray(new DescStringInst[] 
          {txt_top_left, txt_top_right, txt_bottom_right}, "txt", null)) {
            return false;
        }
        return true;
    }
}
