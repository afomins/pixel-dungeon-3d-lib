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
public class Utils {
    //--------------------------------------------------------------------------
    public static void Assert(Boolean statement, String fmt, Object... args) {
        if(statement) {
            return;
        }

        Logger.e(fmt, args);
        int a = 42, b = 0, c = a / b;
        Logger.e(fmt, "Oooops, I'm still alive :: o_O :: a=%d b=%d c=%d", a, b, c);
    }

    //--------------------------------------------------------------------------
    public static void LogException(Exception e, String fmt, Object... args) {
        if(fmt.length() > 0) {
            Logger.e("EXCEPT >> " + fmt, args);
        }
        Logger.e("EXCEPT >> " + e.getMessage());
        StackTraceElement[] stack_trace = e.getStackTrace();
        for(int i = 0; i < stack_trace.length; i++) {
            Logger.e("EXCEPT >> " + stack_trace[i].toString());
        }
    }

    //--------------------------------------------------------------------------
    public static int StrToInt(String str) {
        int value = 0;
        try {
            value = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            Utils.LogException(e, "Failed to convert string to int :: str=%s", str);
        }
        return value;
    }

    //--------------------------------------------------------------------------
    public static boolean StrToBool(String str) {
        return Boolean.parseBoolean(str);
    }

    //--------------------------------------------------------------------------
    public static int BoolToFlags(boolean value, int bit_num) {
        return (value ? 1 << bit_num : 0);
    }

    // -------------------------------------------------------------------------
    public static float MsecToSec(long msec) {
        return msec / 1000.0f;
    }

    // -------------------------------------------------------------------------
    public static int SecToMsec(float sec) {
        return (int)(sec * 1000);
    }

    //--------------------------------------------------------------------------
    public static int FlagSet(int flags, int mask) {
        return flags | mask;
    }

    //--------------------------------------------------------------------------
    public static int FlagUnset(int flags, int mask) {
        return flags & (~mask);
    }

    //--------------------------------------------------------------------------
    public static boolean FlagTest(int flags, int mask) {
        return ((flags & mask) == mask);
    }

    //--------------------------------------------------------------------------
    public static float NormalizeAngle(float val) {
        val %= 360.0f;
        return (val >= 0.0f) ? val : (val + 360.0f);
    }

    //--------------------------------------------------------------------------
    public static float MinimizeRotAngle(float val) {
        if(val > 180.0f) {
            val -= 360.0f;
        } else if(val < -180.0f) {
            val += 360.0f;
        }
        return val;
    }

    //--------------------------------------------------------------------------
    public static int CountLines(String str) {
        if(str == null || str.isEmpty()) {
            return 0;
        }
        int lines = 1;
        int pos = 0;
        while ((pos = str.indexOf("\n", pos) + 1) != 0) {
            lines++;
        }
        return lines;
    }

    //--------------------------------------------------------------------------
    public static String CapitalizeFirst(String text) {
        return Character.toUpperCase(text.charAt(0)) + text.substring(1);
    }

    //--------------------------------------------------------------------------
    public static String GetFriendlyName(Class<?> obj_class) {
        return GetFriendlyName(obj_class.getSimpleName());
    }

    //--------------------------------------------------------------------------
    public static String GetFriendlyName(String name) {
        int len = name.length();

        char chars_src[] = new char[len];
        char chars_dst[] = new char[len * 2];
        name.getChars(0, len, chars_src, 0);

        int ptr = 0;
        for(char ch : chars_src) {
            if(Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);
                if(ptr > 0) {
                    chars_dst[ptr++] = '-';
                }
            }
            chars_dst[ptr++] = ch;
        }
        return String.copyValueOf(chars_dst, 0, ptr);
    }

    //--------------------------------------------------------------------------
    public static String GetPaddedString(String str, int size) {
        String fmt = String.format("%%-%ds", size);
        return String.format(fmt, str);
    }

    //--------------------------------------------------------------------------
    public static boolean NotNull(Object... args) {
        for(Object obj : args) {
            if(obj == null) {
                return false;
            }
        }
        return true;
    }

    //--------------------------------------------------------------------------
    public static int GetColor(int r, int g, int b, int a) {
        return (r << 24 | g << 16 | b << 8 | a << 0);
    }

    //--------------------------------------------------------------------------
    public static int GetColor(float r, float g, float b, float a) {
        return GetColor(
          (int)(r * 255.0f), (int)(g * 255.0f), 
          (int)(b * 255.0f), (int)(a * 255.0f));
    }

    //--------------------------------------------------------------------------
    public static int GetColorR(int color) {
        return (color >> 24) & 0xff;
    }

    //--------------------------------------------------------------------------
    public static int GetColorG(int color) {
        return (color >> 16) & 0xff;
    }

    //--------------------------------------------------------------------------
    public static int GetColorB(int color) {
        return (color >> 8) & 0xff;
    }

    //--------------------------------------------------------------------------
    public static int GetColorA(int color) {
        return color & 0xff;
    }

    //--------------------------------------------------------------------------
    public static int GetRGBAfromARGB(int color) {
        return (color << 8) | ((color >> 24) & 0xff);
    }

    //--------------------------------------------------------------------------
    public static long GetMsec() {
        return java.util.Calendar.getInstance().getTimeInMillis();
    }

    //--------------------------------------------------------------------------
    public static int UpdateCsum(int csum, int old_val, int new_val) {
        return (old_val == new_val) ? csum : csum - old_val + new_val;
    }

    //--------------------------------------------------------------------------
    public static String[] GetEnumNames(Enum<?> enums[]) {
        String names[] = new String[enums.length]; 
        for(int i = 0; i < enums.length; i++) {
            names[i] = enums[i].name();
        }
        return names;
    }

    //--------------------------------------------------------------------------
    public static int GetFirstSetBit(long value) {
        if(value == 0) {
            return 0;
        }
        for(int i = 0; i < Long.SIZE; i++) {
            if((value & 1) != 0) {
                return i + 1;
            } else {
                value = value >> 1;
            }
        }
        return 0;
    }
}
