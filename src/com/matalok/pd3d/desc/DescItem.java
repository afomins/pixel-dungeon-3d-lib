//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import java.util.LinkedList;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescItem 
  extends Desc {
    //**************************************************************************
    // ItemDesc
    //**************************************************************************
    public Integer item_id;
    public Integer sprite_id;
    public Integer level;
    public Integer level_effective;
    public Integer count;
    public Float durability;
    public String name;
    public String name_real;
    public String type;
    public String info;
    public String default_action;
    public Boolean is_broken;
    public Boolean is_broken_visibly;
    public Boolean is_equipped;
    public Boolean is_cursed;
    public Boolean is_cursed_known;
    public Boolean is_cursed_visibly;
    public Boolean is_unique;
    public Boolean is_level_known;
    public Boolean is_selectable;
    public Boolean is_identified;
    public DescStringInst txt_top_left;
    public DescStringInst txt_top_right;
    public DescStringInst txt_bottom_right;
    public DescStringInst txt_bottom_left;
    public LinkedList<String> actions;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(item_id, sprite_id, level, level_effective, count, name, 
          name_real, type, info, is_broken, is_broken_visibly, is_equipped, 
          is_cursed, is_cursed_known, is_cursed_visibly, is_unique, 
          is_level_known, is_selectable, is_identified)) {
            return OnValidateError("param error", null);
        }

        // Quickslot
        if(!Desc.ValidateArray(new DescStringInst[] 
          {txt_top_left, txt_top_right, txt_bottom_right}, "txt", null)) {
            return false;
        }
        return true;
    }
}
