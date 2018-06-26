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
import java.util.LinkedList;
import com.matalok.pd3d.desc.Desc;
import com.matalok.pd3d.desc.DescBag;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class MsgGetInventory
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgGetInventory CreateRequest(String title, String mode, 
      String listener, Integer listener_ex) {
        MsgGetInventory msg = (MsgGetInventory)Msg.CreateRequest(
          new MsgGetInventory(), "get-inventory");
        msg.title = title;
        msg.mode = mode;
        msg.listener = listener;
        msg.listener_ex = listener_ex;
        return msg;
    }

    //**************************************************************************
    // MsgGetInventory
    //**************************************************************************
    public String title;
    public String mode;
    public String listener;
    public Integer listener_ex;
    public Integer gold_num;
    public LinkedList<DescBag> bags;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        // Mandatory params
        if(!Utils.NotNull(mode, listener)) {
            return OnValidateError("parsing error", null);
        }

        // Quickslot index should be present when selecting quickslot
        if(listener.equals("quickslot") && 
          listener_ex == null) {
            return OnValidateError("quickslot-idx error", null);
        }
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        // Mandatory params
        if(!Utils.NotNull(mode, listener, gold_num, bags)) {
            return OnValidateError("parsing error", null);
        }

        // Quickslot index should be present when selecting quickslot
        if(mode.equals("quickslot") && 
          listener_ex == null) {
            return OnValidateError("quickslot-idx error", null);
        }

        // Bags
        Desc.ValidateIt(bags.iterator(), "bag", null);
        return true;
    }
}
