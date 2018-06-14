//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescRect 
  extends Desc {
    //**************************************************************************
    // DescRect
    //**************************************************************************
    public Integer x, y, width, height;
    public Float unit_x, unit_y, unit_width, unit_height;
    public boolean is_unit_size_ready;

    //--------------------------------------------------------------------------
    public DescRect Set(int x, int y, int width, int height) {
        this.x = x; this.width = width;
        this.y = y; this.height = height;
        return this;
    }

    //--------------------------------------------------------------------------
    public DescRect Set(DescRect src_rect) {
        Set(src_rect.x, src_rect.y, src_rect.width, src_rect.height);
        Set(src_rect.unit_x, src_rect.unit_y, src_rect.unit_width, src_rect.unit_height);
        return this;
    }

    //--------------------------------------------------------------------------
    public DescRect Set(float x, float y, float width, float height) {
        unit_x = x; unit_width = width;
        unit_y = y; unit_height = height;
        is_unit_size_ready = true;
        return this;
    }

    //--------------------------------------------------------------------------
    public void CalculateUnitSize(int total_width, int total_height) {
        unit_x = (float)x / total_width;
        unit_y = (float)y / total_height;
        unit_width = (float)width / total_width;
        unit_height = (float)height / total_height;
        is_unit_size_ready = true;
    }

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(x, y, width, height)) {
            return OnValidateError("param error", null);
        }
        return true;
    }
}
