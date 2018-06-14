//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescEvent 
  extends Desc {
    //**************************************************************************
    // DescEvent
    //**************************************************************************
    public Integer event_id;
    public Integer cell_id;
    public DescPfxMutator pfx_mutator;

    //--------------------------------------------------------------------------
    public DescEvent SetEventId(Enum<?> e) {
        return SetEventId(e.ordinal());
    }

    //--------------------------------------------------------------------------
    public DescEvent SetEventId(int val) {
        event_id = val;
        return this;
    }

    //--------------------------------------------------------------------------
    public DescEvent SetCellId(int val) {
        cell_id = val;
        return this;
    }

    //--------------------------------------------------------------------------
    public DescEvent SetPfxMutator(DescPfxMutator.Field field, Object... args) {
        if(pfx_mutator == null) {
            pfx_mutator = new DescPfxMutator();
        }
        if(field != null) {
            pfx_mutator.Set(field, args);
        }
        return this;
    }

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(event_id)) {
            return OnValidateError("param error", null);
        }
        return true;
    }
}
