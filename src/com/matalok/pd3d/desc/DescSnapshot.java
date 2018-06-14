//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescSnapshot 
  extends Desc {
    //**************************************************************************
    // DescSnapshot
    //**************************************************************************
    public String version;
    public String terrain_texture;
    public DescSceneGame game;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(version, terrain_texture, game)) {
            return OnValidateError("param error", null);
        }
        return true;
    }
}
