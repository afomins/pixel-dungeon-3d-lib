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
