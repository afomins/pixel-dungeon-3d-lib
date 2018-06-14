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
