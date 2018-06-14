//------------------------------------------------------------------------------
package com.matalok.pd3d.msg;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.ClientAPI;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class MsgLocal
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgLocal CreateRequest() {
        return (MsgLocal)Msg.CreateRequest(
          new MsgLocal(), "local");
    }

    //**************************************************************************
    // MsgLocal
    //**************************************************************************
    public String state;
    public ClientAPI client_api;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        // Mandatory params
        if(!Utils.NotNull(state)) {
            return OnValidateError("parsing error", null);
        }

        // Client API
        if(state.equals("connect") && client_api == null) {
            return OnValidateError("client-api error", null);
        }
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        return true;
    }
}
