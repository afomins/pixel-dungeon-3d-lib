//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescStringInst 
  extends Desc {
    //**************************************************************************
    // DescStringInst
    //**************************************************************************
    public String text;
    public Integer color;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(text, color)) {
            return OnValidateError("param error", null);
        }
        return true;
    }
}
