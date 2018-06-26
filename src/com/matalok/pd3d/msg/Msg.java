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
import com.matalok.pd3d.shared.GsonUtils;
import com.matalok.pd3d.shared.Logger;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public abstract class Msg 
  implements IMsg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    private static int request_id = 100;
    private static String ex_separator = "|";
    private static Msg[] msg_types = {
        MsgCommand.CreateRequest(),
        MsgGetInventory.CreateRequest(null, null, null, null),
        MsgGetScene.CreateRequest(),
        MsgHeroInteract.CreateRequest((String)null, (Integer)null),
        MsgLocal.CreateRequest(),
        MsgRunGame.CreateRequest((String)null, (String)null),
        MsgRunItemAction.CreateRequest(null, null, null),
        MsgSelectInventoryItem.CreateRequest(null),
        MsgSelectQuickslotItem.CreateRequest((Integer)null, (Integer)null),
        MsgSwitchScene.CreateRequest(null),
        MsgUpdateScene.CreateRequest(),
        MsgUpdateSprites.CreateRequest(),
        MsgQuestStart.CreateRequest(),
        MsgQuestAction.CreateRequest(),
    };

    //--------------------------------------------------------------------------
    public static Msg Deserialize(String str) {
        Msg msg = null;

        int hdr_size = str.indexOf(ex_separator);
        if(hdr_size > 0) {
            String hdr_name = str.substring(0, hdr_size);
            for(Msg msg_type : msg_types) {
                if(msg_type.hdr_name.equals(hdr_name)) {
                    msg = (Msg)GsonUtils.Deserialize(
                      str.substring(hdr_size + 1), msg_type.getClass());
                    break;
                }
            }
        }

        if(msg == null) {
            Logger.e("Failed to deserialize message :: msg=%s", str);
        }
        return msg;
    }

    //--------------------------------------------------------------------------
    public static Msg CreateRequest(Msg msg, String name) {
        return msg.SetHeader(request_id++, Type.REQUEST, name);
    }

    //--------------------------------------------------------------------------
    public static Msg CreateResponse(Msg request) {
        // Only request can create response
        if(!request.IsRequest()) {
            Logger.e("Failed to create msg response, wrong initial type");
            return null;
        }

        // Create response
        try {
            Msg response = request.getClass().newInstance();
            return response.SetHeader(request.hdr_id, Type.RESPONSE, 
              request.hdr_name).SetStatus(true, "ok"); 

        } catch (Exception ex) {
            Utils.LogException(
              ex, "Failed to create pmsg response, new instance error");
        }
        return null;
    }

    //**************************************************************************
    // ProxyMsg
    //**************************************************************************
    public Integer hdr_id;      // Header
    public Type    hdr_type;
    public String  hdr_name;

    public Boolean status_code; // Status
    public String  status_text;

    //--------------------------------------------------------------------------
    public boolean IsRequest() {
        return (hdr_type != null && hdr_type == Type.REQUEST);
    }

    //--------------------------------------------------------------------------
    public boolean IsResponse() {
        return (hdr_type != null && hdr_type == Type.RESPONSE);
    }

    //--------------------------------------------------------------------------
    public Msg SetHeader(int id, Type type, String name) {
        hdr_id = id;
        hdr_type = type;
        hdr_name = name;
        return this;
    }

    //--------------------------------------------------------------------------
    public Msg SetStatus(boolean code, String fmt, Object... args) {
        status_code = code;
        status_text = String.format(fmt, args);
        return this;
    }

    //--------------------------------------------------------------------------
    public String ToJsonStringEx() {
        return hdr_name + ex_separator + ToJsonString();
    }

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Validate header
        if(!Utils.NotNull(hdr_id, hdr_type, hdr_name)) {
            return OnValidateError("header error", null);
        }

        // Validate request
        if(IsRequest()) {
            return ValidateRequest();

        // Validate response
        } else if(IsResponse()) {
            // Validate status
            if(!Utils.NotNull(status_code, status_text)) {
                return OnValidateError("status error", null);
            }
            return ValidateResponse();
        }
        return false;
    }

    //--------------------------------------------------------------------------
    @Override public boolean OnValidateError(String desc, String fmt, 
      Object...args) {
        Logger.e("Failed to validate msg, %s :: type=%s class=%s %s",
          desc, (hdr_type == null) ? "null" : hdr_type.toString().toLowerCase(), 
          getClass().getSimpleName(), 
          (fmt != null) ? String.format(fmt, args) : "");
        return false;
    }

    //--------------------------------------------------------------------------
    @Override public String ToJsonString() {
        return GsonUtils.Serialize(this, false);
    }

    //--------------------------------------------------------------------------
    @Override public String ToShortString() {
        return String.format("%d:%s:%s", hdr_id, hdr_type, hdr_name);
    }
}
