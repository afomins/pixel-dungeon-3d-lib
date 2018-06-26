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
package com.matalok.pd3d.shared;

//------------------------------------------------------------------------------
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

//------------------------------------------------------------------------------
public class GsonUtils {
    //--------------------------------------------------------------------------
    public static Gson gson = new Gson();
    public static Gson gson_pretty = new GsonBuilder().setPrettyPrinting().create();

    //--------------------------------------------------------------------------
    public static String Serialize(Object obj, boolean pretty) {
        return pretty ? gson_pretty.toJson(obj) : gson.toJson(obj);
    }

    //--------------------------------------------------------------------------
    public static Object Deserialize(String json_str, Class<?> obj_class) {
        Object obj = null;
        try {
            obj = gson.fromJson(json_str, obj_class);
        } catch(JsonSyntaxException ex) {
            Logger.e("Failed to deserialize JSON :: class=%s", obj_class.toString());
        }
        return obj;
    }
}
