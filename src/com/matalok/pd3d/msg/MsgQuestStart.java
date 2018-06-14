//------------------------------------------------------------------------------
package com.matalok.pd3d.msg;

//------------------------------------------------------------------------------
import com.matalok.pd3d.desc.DescQuest;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class MsgQuestStart
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgQuestStart CreateRequest() {
        MsgQuestStart msg = (MsgQuestStart)Msg.CreateRequest(
          new MsgQuestStart(), "quest-start");
        return msg;
    }

    //**************************************************************************
    // MsgQuestStart
    //**************************************************************************
    public DescQuest quest;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        // Mandatory params
        if(!Utils.NotNull(quest)) {
            return OnValidateError("parsing error", null);
        }
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        // Mandatory params
        if(!Utils.NotNull(quest)) {
            return OnValidateError("parsing error", null);
        }
        return quest.Validate();
    }
}
