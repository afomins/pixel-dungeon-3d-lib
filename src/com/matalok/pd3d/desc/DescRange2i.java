//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
public class DescRange2i
  extends DescRange2<Integer> {
    //**************************************************************************
    // DescRange2i
    //**************************************************************************
    public DescRange2i(DescRange2i r) {
        this(r.low, r.high);
    }

    //------------------------------------------------------------------------------
    public DescRange2i(Integer low, Integer high) {
        Copy(low, high);
    }

    //------------------------------------------------------------------------------
    @Override public Integer Copy(Integer val) {
        return new Integer(val);
    }
}
