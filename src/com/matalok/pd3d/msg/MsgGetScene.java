//------------------------------------------------------------------------------
package com.matalok.pd3d.msg;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class MsgGetScene
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgGetScene CreateRequest() {
        MsgGetScene msg = (MsgGetScene)Msg.CreateRequest(
          new MsgGetScene(), "get-scene");
        return msg;
    }

    //**************************************************************************
    // MsgGetScene
    //**************************************************************************
    public String scene_name;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        // Mandatory params
        if(!Utils.NotNull(scene_name)) {
            return OnValidateError("parsing error", null);
        }
        return true;
    }
}
