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
public abstract class DescVector3<T>
  extends Desc {
    //**************************************************************************
    // DescVector3
    //**************************************************************************
    public T x, y, z;

    //----------------------------------------------------------------------
    public DescVector3() {
    }

    //----------------------------------------------------------------------
    public DescVector3<T> Copy(DescVector3<T> v) {
        return Copy(v.x, v.y, v.z);
    }

    //----------------------------------------------------------------------
    public DescVector3<T> Copy(T x, T y, T z) {
        Reset();
        if(x != null) this.x = Copy(x);
        if(y != null) this.y = Copy(y);
        if(z != null) this.z = Copy(z);
        return this;
    }

    //----------------------------------------------------------------------
    public DescVector3<T> Reset() {
        x = y = z = null;
        return this;
    }

    //----------------------------------------------------------------------
    public abstract T Copy(T val);

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        return true;
    }
}
