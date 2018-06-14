//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import java.util.LinkedList;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescQuest 
  extends Desc {
    //**************************************************************************
    // DescQuest
    //**************************************************************************
    public String name;
    public String title;
    public String text;
    public Boolean need_response;
    public DescSpriteInst sprite_id;
    public Integer target_item_id, target_item_id2;
    public Integer target_char_id;
    public Integer target_cell_id;
    public LinkedList<DescQuestAction> actions;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(name, need_response)) {
            return OnValidateError("param error", null);
        }

        // Mandatory response params
        if(need_response && !Utils.NotNull(title, text, sprite_id) && 
          !sprite_id.Validate()) {
            return OnValidateError("param error", null);
        }

        switch(name) {
        // Item quests
        case "read-tome-of-mastery":
        case "sell-item":
        case "apply-weightstone": {
            if(!Utils.NotNull(target_item_id)) {
                return OnValidateError("param error", "name=%s", name);
            }
        } break;

        // Cell quests
        case "buy-item": {
            if(!Utils.NotNull(target_cell_id)) {
                return OnValidateError("param error", "name=%s", name);
            }
        } break;

        // Blacksmith reforge
        case "blacksmith-reforge":
        case "blacksmith-reforge-update": {
            if(!Utils.NotNull(target_char_id, target_item_id, target_item_id2)) {
                return OnValidateError("param error", "name=%s", name);
            }
        } break;

        // NPC quests
        case "sadghost": 
        case "wandmaker": 
        case "blacksmith": 
        case "imp": {
            if(!Utils.NotNull(target_char_id)) {
                return OnValidateError("param error", "name=%s", name);
            }
        } break;
        }
        return true;
    }
}
