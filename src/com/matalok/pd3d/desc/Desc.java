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
import java.util.Iterator;
import com.matalok.pd3d.msg.IMsg;
import com.matalok.pd3d.shared.GsonUtils;
import com.matalok.pd3d.shared.Logger;

//------------------------------------------------------------------------------
public abstract class Desc 
  implements IMsg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static boolean ValidateIt(Iterator<? extends Desc> it, String type, 
      String fmt, Object... args) {
        if(!it.hasNext()) {
            Logger.e("Failed to validate desc, empty iterator :: type=%s %s",
              type, (fmt != null) ? String.format(fmt, args) : "");
            return false;
        }

        while(it.hasNext()) {
            Desc desc = (Desc)it.next();
            if(!desc.Validate()) {
                return desc.OnValidateError(type + " error", fmt, args);
            }
        }
        return true;
    }

    //--------------------------------------------------------------------------
    public static boolean ValidateArray(Object[] desc_array, String type, 
      String fmt, Object... args) {
        for(int i = 0; i < desc_array.length; i++) {
            Desc desc = (Desc)desc_array[i];
            if(desc == null) {
                continue;
            }
            if(!desc.Validate()) {
                return desc.OnValidateError(type + " error", fmt, args);
            }
        }
        return true;
    }

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean OnValidateError(String desc, String fmt, 
      Object...args) {
        Logger.e("Failed to validate desc, %s :: class=%s %s",
          desc, getClass().getSimpleName(), 
          (fmt != null) ? String.format(fmt, args) : "");
        return false;
    }

    //--------------------------------------------------------------------------
    @Override public String ToJsonString() {
        return GsonUtils.Serialize(this, false);
    }

    //--------------------------------------------------------------------------
    @Override public String ToShortString() {
        return getClass().getSimpleName();
    }
}
