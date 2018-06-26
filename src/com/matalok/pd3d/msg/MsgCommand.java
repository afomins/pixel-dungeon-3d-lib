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
public class MsgCommand
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgCommand CreateRequest() {
        MsgCommand msg = (MsgCommand)Msg.CreateRequest(
          new MsgCommand(), "command");
        return msg;
    }

    //**************************************************************************
    // MsgCommand
    //**************************************************************************
    public Boolean iddqd;
    public Boolean item_info_ext;
    public Boolean music;
    public Boolean sound;
    public String game_op;
    public String game_args[];

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        return true;
    }
}
