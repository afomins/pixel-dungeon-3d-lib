//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import java.util.LinkedList;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescSceneStart
  extends Desc {
    //**************************************************************************
    // DescStartScene
    //**************************************************************************
    public LinkedList<DescHero> heros;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(heros)) {
            return OnValidateError("param error", null);
        }

        // Heros
        if(!Desc.ValidateIt(heros.iterator(), "hero", null)) {
            return false;
        }
        return true;
    }
}
