//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescQuestAction 
  extends Desc {
    //**************************************************************************
    // DescQuestAction
    //**************************************************************************
    public String name;
    public String desc;
    public Boolean is_active;

    //--------------------------------------------------------------------------
    public DescQuestAction(String name, String desc, Boolean is_active) {
        this.name = name;
        this.desc = desc;
        this.is_active = is_active;
    }

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(name, desc, is_active)) {
            return OnValidateError("param error", null);
        }
        return true;
    }
}
