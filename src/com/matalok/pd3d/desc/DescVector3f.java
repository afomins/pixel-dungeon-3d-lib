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
public class DescVector3f
  extends DescVector3<Float> {
    //**************************************************************************
    // DescVector3f
    //**************************************************************************
    public DescVector3f(DescVector3f v) {
        this(v.x, v.y, v.z);
    }

    //------------------------------------------------------------------------------
    public DescVector3f(Float x, Float y, Float z) {
        Copy(x, y, z);
    }

    //------------------------------------------------------------------------------
    @Override public Float Copy(Float val) {
        return new Float(val);
    }
}
