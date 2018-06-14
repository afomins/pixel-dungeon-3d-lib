//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import java.util.LinkedList;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescAnim 
  extends Desc {
    //**************************************************************************
    // DescAnim
    //**************************************************************************
    public String name;
    public Integer fps;
    public Boolean is_looped;
    public LinkedList<Integer> frames;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(name, fps, is_looped, frames)) {
            return OnValidateError("param error", null);
        }
        return true;
    }
}
