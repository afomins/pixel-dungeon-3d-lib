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
