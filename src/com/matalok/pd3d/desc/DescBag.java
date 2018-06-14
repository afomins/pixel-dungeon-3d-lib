//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import java.util.LinkedList;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescBag 
  extends Desc {
    //**************************************************************************
    // DescBag
    //**************************************************************************
    public String name;
    public LinkedList<DescItem> items;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(name)) {
            return OnValidateError("param error", null);
        }

        // Items
        if(items != null && 
          !Desc.ValidateIt(items.iterator(), "item", null)) {
            return false;
        }
        return true;
    }
}
