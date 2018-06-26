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
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescEvent 
  extends Desc {
    //**************************************************************************
    // DescEvent
    //**************************************************************************
    public Integer event_id;
    public Integer cell_id;
    public DescPfxMutator pfx_mutator;

    //--------------------------------------------------------------------------
    public DescEvent SetEventId(Enum<?> e) {
        return SetEventId(e.ordinal());
    }

    //--------------------------------------------------------------------------
    public DescEvent SetEventId(int val) {
        event_id = val;
        return this;
    }

    //--------------------------------------------------------------------------
    public DescEvent SetCellId(int val) {
        cell_id = val;
        return this;
    }

    //--------------------------------------------------------------------------
    public DescEvent SetPfxMutator(DescPfxMutator.Field field, Object... args) {
        if(pfx_mutator == null) {
            pfx_mutator = new DescPfxMutator();
        }
        if(field != null) {
            pfx_mutator.Set(field, args);
        }
        return this;
    }

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(event_id)) {
            return OnValidateError("param error", null);
        }
        return true;
    }
}
