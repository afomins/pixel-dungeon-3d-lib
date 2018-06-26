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
public class DescChar 
  extends Desc {
    //**************************************************************************
    // DescChar
    //**************************************************************************
    public Integer id;
    public Integer pos;
    public Integer hp;
    public Integer ht;
    public Integer sprite_id;
    public String emotion;
    public Float time;
    public LinkedList<String> anims;
    public LinkedList<DescStringInst> status_string;
    public LinkedList<DescSpriteInst> status_sprite;
    public LinkedList<Integer> buffs;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        // Mandatory params
        if(!Utils.NotNull(id, pos, hp, ht, sprite_id)) {
            return OnValidateError("param error", null);
        }

        // Status string list
        if(status_string != null && 
          !Desc.ValidateIt(status_string.iterator(), "status_string", null)) {
            return false;
        }

        // Status icon list
        if(status_sprite != null && 
          !Desc.ValidateIt(status_sprite.iterator(), "status_sprite", null)) {
            return false;
        }
        return true;
    }
}
