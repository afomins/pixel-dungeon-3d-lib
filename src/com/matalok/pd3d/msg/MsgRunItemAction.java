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
package com.matalok.pd3d.msg;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class MsgRunItemAction
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgRunItemAction CreateRequest(Integer item_idx, String action, 
      Integer dest_cell_idx) {
        MsgRunItemAction msg = (MsgRunItemAction)Msg.CreateRequest(
          new MsgRunItemAction(), "run-item-action");
        msg.item_idx = item_idx;
        msg.action = action;
        msg.dest_cell_idx = dest_cell_idx;
        return msg;
    }

    //**************************************************************************
    // MsgRunItemAction
    //**************************************************************************
    public Integer item_idx;
    public String action;
    public Integer src_cell_idx;
    public Integer dest_cell_idx;
    public Integer sprite_id;
    public Boolean do_circle_back;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        // Mandatory params
        if(!Utils.NotNull(item_idx, action)) {
            return OnValidateError("parsing error", null);
        }

        // Test mandatory parameters when throwing/zapping
        if(action.equals("throw") || action.equals("zap")) {
            if(!Utils.NotNull(dest_cell_idx)) {
                return OnValidateError("throw error", null);
            }
        }
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        // Mandatory params
        if(!Utils.NotNull(action)) {
            return OnValidateError("parsing error", null);
        }

        // Test mandatory parameters when throwing/zapping
        if(action.equals("throw") || action.equals("zap")) {
            if(!Utils.NotNull(src_cell_idx, dest_cell_idx, sprite_id, 
              do_circle_back)) {
                return OnValidateError("throw error", null);
            }
        }
        return true;
    }
}
