//------------------------------------------------------------------------------
package com.matalok.pd3d.msg;

//------------------------------------------------------------------------------
import java.util.LinkedList;

import com.matalok.pd3d.desc.Desc;
import com.matalok.pd3d.desc.DescSprite;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class MsgUpdateSprites
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgUpdateSprites CreateRequest() {
        return (MsgUpdateSprites)Msg.CreateRequest(
          new MsgUpdateSprites(), "update-sprites");
    }

    //**************************************************************************
    // MsgUpdateSprites
    //**************************************************************************
    public LinkedList<DescSprite> sprites;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        // Mandatory params
        if(!Utils.NotNull(sprites)) {
            return OnValidateError("parsing error", null);
        }

        // Plants
        if(!Desc.ValidateIt(sprites.iterator(), "sprite", null)) {
            return false;
        }
        return true;
    }
}
