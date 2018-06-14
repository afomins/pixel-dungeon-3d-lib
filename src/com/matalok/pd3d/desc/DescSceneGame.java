//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import java.util.LinkedList;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescSceneGame
  extends Desc {
    //**************************************************************************
    // DescSceneGame
    //**************************************************************************
    public Integer map_width;
    public Integer map_height;
    public String map_srlz;
    public Integer map_csum;
    public Integer step;
    public Integer dungeon_depth;
    public Integer loot_item_id;
    public String interrupt;
    public DescHeroStats hero_stats;
    public LinkedList<DescChar> chars;
    public LinkedList<DescHeap> heaps;
    public LinkedList<DescHeap> plants;
    public LinkedList<DescEvent> events;
    public DescItem quickslot0;
    public DescItem quickslot1;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(map_width, map_height, dungeon_depth, 
          hero_stats, step)) {
            return OnValidateError("param error", null);
        }

        // Hero stats
        if(!hero_stats.Validate()) {
            return OnValidateError("hero stats error", null);
        }

        // Chars
        if(chars != null && 
          !Desc.ValidateIt(chars.iterator(), "char", null)) {
            return false;
        }

        // Heaps
        if(heaps != null && 
          !Desc.ValidateIt(heaps.iterator(), "heap", null)) {
            return false;
        }

        // Plants
        if(plants != null && 
          !Desc.ValidateIt(plants.iterator(), "plant", null)) {
            return false;
        }

        // Evetns
        if(events != null && 
          !Desc.ValidateIt(events.iterator(), "event", null)) {
            return false;
        }

        // Quickslot
        if(!Desc.ValidateArray(new DescItem[] 
          {quickslot0, quickslot1}, "quickslot item", null)) {
            return false;
        }
        return true;
    }
}
