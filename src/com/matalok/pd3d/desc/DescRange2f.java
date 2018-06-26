/*
 * Pixel Dungeon 3D
 * Copyright (C) 2016-2018 Alex Fomins
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
public class DescRange2f
  extends DescRange2<Float> {
    //**************************************************************************
    // DescRange2f
    //**************************************************************************
    public DescRange2f(DescRange2f r) {
        this(r.low, r.high);
    }

    //------------------------------------------------------------------------------
    public DescRange2f(Float low, Float high) {
        Copy(low, high);
    }

    //------------------------------------------------------------------------------
    @Override public Float Copy(Float val) {
        return new Float(val);
    }
}
