//------------------------------------------------------------------------------
package com.matalok.pd3d.msg;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class MsgSelectInventoryItem
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgSelectInventoryItem CreateRequest(Integer item_idx) {
        MsgSelectInventoryItem msg = (MsgSelectInventoryItem)Msg.CreateRequest(
          new MsgSelectInventoryItem(), "select-inventory-item");
        msg.item_idx = item_idx;
        return msg;
    }

    //**************************************************************************
    // MsgSelectInventoryItem
    //**************************************************************************
    public Integer item_idx;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        // Mandatory params
        if(!Utils.NotNull(item_idx)) {
            return OnValidateError("parsing error", null);
        }
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        return true;
    }
}
