//------------------------------------------------------------------------------
package com.matalok.pd3d.msg;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class MsgSwitchScene
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgSwitchScene CreateRequest(String scene_name) {
        MsgSwitchScene msg = (MsgSwitchScene)Msg.CreateRequest(
          new MsgSwitchScene(), "switch-scene");
        msg.scene_name = scene_name;
        return msg;
    }

    //**************************************************************************
    // MsgSwitchScene
    //**************************************************************************
    public String scene_name;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        // Mandatory params
        if(!Utils.NotNull(scene_name)) {
            return OnValidateError("parsing error", null);
        }
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        return true;
    }
}
