//------------------------------------------------------------------------------
package com.matalok.pd3d.msg;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class MsgRunItemAction
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgRunItemAction CreateRequest(Integer item_idx, String action, 
      Integer dest_cell_idx) {
        MsgRunItemAction msg = (MsgRunItemAction)Msg.CreateRequest(
          new MsgRunItemAction(), "run-item-action");
        msg.item_idx = item_idx;
        msg.action = action;
        msg.dest_cell_idx = dest_cell_idx;
        return msg;
    }

    //**************************************************************************
    // MsgRunItemAction
    //**************************************************************************
    public Integer item_idx;
    public String action;
    public Integer src_cell_idx;
    public Integer dest_cell_idx;
    public Integer sprite_id;
    public Boolean do_circle_back;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        // Mandatory params
        if(!Utils.NotNull(item_idx, action)) {
            return OnValidateError("parsing error", null);
        }

        // Test mandatory parameters when throwing/zapping
        if(action.equals("throw") || action.equals("zap")) {
            if(!Utils.NotNull(dest_cell_idx)) {
                return OnValidateError("throw error", null);
            }
        }
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        // Mandatory params
        if(!Utils.NotNull(action)) {
            return OnValidateError("parsing error", null);
        }

        // Test mandatory parameters when throwing/zapping
        if(action.equals("throw") || action.equals("zap")) {
            if(!Utils.NotNull(src_cell_idx, dest_cell_idx, sprite_id, 
              do_circle_back)) {
                return OnValidateError("throw error", null);
            }
        }
        return true;
    }
}
