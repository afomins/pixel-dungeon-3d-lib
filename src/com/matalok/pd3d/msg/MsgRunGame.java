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
public class MsgRunGame
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgRunGame CreateRequest(String hero_name, String game_type) {
        MsgRunGame msg = (MsgRunGame)Msg.CreateRequest(
          new MsgRunGame(), "run-game");
        msg.hero_name = hero_name;
        msg.game_type = game_type;
        return msg;
    }

    //**************************************************************************
    // MsgRunGame
    //**************************************************************************
    public String hero_name;
    public String game_type;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        // Mandatory params
        if(!Utils.NotNull(hero_name, game_type)) {
            return OnValidateError("parsing error", null);
        }
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        // Mandatory params
        if(!Utils.NotNull(hero_name)) {
            return OnValidateError("parsing error", null);
        }
        return true;
    }
}
