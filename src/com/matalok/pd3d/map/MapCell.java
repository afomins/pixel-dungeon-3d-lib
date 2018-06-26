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
package com.matalok.pd3d.map;

//------------------------------------------------------------------------------
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class MapCell 
  implements Map.ICell {
    //**************************************************************************
    // MapCell
    //**************************************************************************
    public MapEnum.TerrainType type;
    public int flags;

    //--------------------------------------------------------------------------
    public MapCell() {
        type = MapEnum.TerrainType.CHASM;//.EMPTY;
        flags = 0;
    }

    //--------------------------------------------------------------------------
    public int GetCsum() {
        // Exclude DIRTY from checksum
        return type.ordinal() + 
          Utils.FlagUnset(flags, MapEnum.TerrainFlags.PD3D_DIRTY.flag);
    }

    //**************************************************************************
    // Map.ICell
    //**************************************************************************
    @Override public int GetRawSize() {
        return 3;
    }

    //--------------------------------------------------------------------------
    @Override public void Serialize(byte[] dest, int ptr) {
        // Type:  1 byte
        int raw = type.ordinal();
        dest[ptr + 0] = (byte)raw;

        // Flags: 2 bytes
        raw = flags;
        dest[ptr + 1] = (byte)raw;            // 1st byte
        dest[ptr + 2] = (byte)(raw >> 8);     // 2nd byte
    }

    //--------------------------------------------------------------------------
    @Override public void Deserialize(byte[] src, int ptr) {
        // Type: 1 byte
        int raw = src[ptr + 0];
        type = MapEnum.TerrainType.Get(raw);

        // Flags: 2 bytes
        raw = (((int)src[ptr + 1]     ) & 0x00ff) |  // 1st byte 
              (((int)src[ptr + 2] << 8) & 0xff00);   // 2nd byte
        flags = raw;
    }
}
