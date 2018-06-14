//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescHeroStats 
  extends Desc {
    //**************************************************************************
    // DescHeroStats
    //**************************************************************************
    public Integer hp;
    public Integer ht;
    public Integer exp;
    public Integer exp_max;
    public Integer strength;
    public Integer level;
    public Float time_hero;
    public Float time_global;
    public String status;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(hp, ht, exp, exp_max, strength, level, 
          time_hero, time_global, status)) {
            return OnValidateError("param error", null);
        }
        return true;
    }
}
