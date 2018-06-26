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
public class MsgQuestAction
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgQuestAction CreateRequest() {
        MsgQuestAction msg = (MsgQuestAction)Msg.CreateRequest(
          new MsgQuestAction(), "quest-action");
        return msg;
    }

    //**************************************************************************
    // MsgQuestAction
    //**************************************************************************
    public String action;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        // Mandatory params
        if(!Utils.NotNull(action)) {
            return OnValidateError("parsing error", null);
        }
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        return true;
    }
}
