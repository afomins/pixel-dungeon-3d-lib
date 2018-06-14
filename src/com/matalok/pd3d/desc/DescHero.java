//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import java.util.LinkedList;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescHero 
  extends Desc {
    //**************************************************************************
    // DescHero
    //**************************************************************************
    public Integer id;
    public Boolean is_locked;
    public Integer depth;
    public Integer level;
    public Boolean has_challenges;
    public String mastery;
    public LinkedList<String> perks;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(id, is_locked, depth, level, has_challenges, mastery, perks)) {
            return OnValidateError("param error", null);
        }
        return true;
    }
}
