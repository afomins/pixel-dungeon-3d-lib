//------------------------------------------------------------------------------
package com.matalok.pd3d.msg;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class MsgHeroInteract
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgHeroInteract CreateRequest(String interact_type, 
      Integer cell_idx) {
        MsgHeroInteract msg = (MsgHeroInteract)Msg.CreateRequest(
          new MsgHeroInteract(), "hero-interact");
        msg.interact_type = interact_type;
        msg.cell_idx = cell_idx;
        return msg;
    }

    //**************************************************************************
    // MsgHeroInteract
    //**************************************************************************
    public String interact_type;
    public Integer cell_idx;
    public Boolean try_interrupt;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        // Mandatory params
        if(!Utils.NotNull(interact_type)) {
            return OnValidateError("parsing error", null);
        }

        // Interrupt is mandatory when moving
        if(interact_type.startsWith("move-") && !Utils.NotNull(try_interrupt)) {
            return OnValidateError("parsing error, no interrupt", null);
        }

        // Cell index should be present when selecting cell
        if(interact_type.equals("select-cell") && 
          cell_idx == null) {
            return OnValidateError("cell-idx error", null);
        }
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        return true;
    }
}
