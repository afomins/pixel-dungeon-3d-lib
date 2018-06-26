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
import java.util.LinkedList;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class DescHero 
  extends Desc {
    //**************************************************************************
    // DescHero
    //**************************************************************************
    public Integer id;
    public Boolean is_locked;
    public Integer depth;
    public Integer level;
    public Boolean has_challenges;
    public String mastery;
    public LinkedList<String> perks;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(id, is_locked, depth, level, has_challenges, mastery, perks)) {
            return OnValidateError("param error", null);
        }
        return true;
    }
}
