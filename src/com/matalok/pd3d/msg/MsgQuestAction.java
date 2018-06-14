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
