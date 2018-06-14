//------------------------------------------------------------------------------
package com.matalok.pd3d.msg;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class MsgSelectQuickslotItem
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgSelectQuickslotItem CreateRequest(Integer quickslot_idx, 
      Integer item_idx) {
        MsgSelectQuickslotItem msg = (MsgSelectQuickslotItem)Msg.CreateRequest(
          new MsgSelectQuickslotItem(), "select-quickslot-item");
        msg.quickslot_idx = quickslot_idx;
        msg.item_idx = item_idx;
        return msg;
    }

    //**************************************************************************
    // MsgSelectQuickslotItem
    //**************************************************************************
    public Integer quickslot_idx;
    public Integer item_idx;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        // Mandatory params
        if(!Utils.NotNull(quickslot_idx, item_idx)) {
            return OnValidateError("parsing error", null);
        }
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        return true;
    }
}
