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
public class MsgHeroInteract
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgHeroInteract CreateRequest(String interact_type, 
      Integer cell_idx) {
        MsgHeroInteract msg = (MsgHeroInteract)Msg.CreateRequest(
          new MsgHeroInteract(), "hero-interact");
        msg.interact_type = interact_type;
        msg.cell_idx = cell_idx;
        return msg;
    }

    //**************************************************************************
    // MsgHeroInteract
    //**************************************************************************
    public String interact_type;
    public Integer cell_idx;
    public Boolean try_interrupt;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        // Mandatory params
        if(!Utils.NotNull(interact_type)) {
            return OnValidateError("parsing error", null);
        }

        // Interrupt is mandatory when moving
        if(interact_type.startsWith("move-") && !Utils.NotNull(try_interrupt)) {
            return OnValidateError("parsing error, no interrupt", null);
        }

        // Cell index should be present when selecting cell
        if(interact_type.equals("select-cell") && 
          cell_idx == null) {
            return OnValidateError("cell-idx error", null);
        }
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        return true;
    }
}
