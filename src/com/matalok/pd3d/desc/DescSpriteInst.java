//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescSpriteInst
  extends Desc {
    //**************************************************************************
    // DescSpriteInst
    //**************************************************************************
    public Integer id;
    public String type;
    public Integer color;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(id, type)) {
            return OnValidateError("param error", null);
        }
        return true;
    }
}
