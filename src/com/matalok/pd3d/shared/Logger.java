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

//-----------------------------------------------------------------------------
package com.matalok.pd3d.shared;

//-----------------------------------------------------------------------------
import java.text.DecimalFormat;
import java.util.Calendar;
import com.matalok.pd3d.shared.UtilsClass.Callback;

//-----------------------------------------------------------------------------
public abstract class Logger {
    // *************************************************************************
    // STATIC
    // *************************************************************************
    private static boolean m_is_new_step;
    private static boolean m_do_logging;
    private static Logger m_logger;
    private static Callback m_error_cb;
    private static long m_startup_time = Calendar.getInstance().getTimeInMillis();
    private static DecimalFormat m_formatter = new DecimalFormat("00000000.000");
    private static String m_prefix = "";
    private static long m_frame;

    // -------------------------------------------------------------------------
    public static void Register(Logger logger, Callback error_cb) {
        m_logger = logger.Init();
        m_error_cb = error_cb;
        SetLogging(true);
    }

    // -------------------------------------------------------------------------
    public static void SetLogging(boolean is_enabled) {
        m_do_logging = is_enabled;
    }

    // -------------------------------------------------------------------------
    public static void NewStep(boolean is_next_frame) {
        m_is_new_step = true;
        if(is_next_frame) {
            m_frame++;
        }
    }

    // -------------------------------------------------------------------------
    public static void SetPrefix(String prefix) {
        m_prefix = prefix;
    }

    // -------------------------------------------------------------------------
    private static void WriteStr(String str) {
        m_logger.WriteRaw(str);
    }

    // -------------------------------------------------------------------------
    public static void Write(String lvl, String fmt, Callback cb, Object... args) {
        if(!m_do_logging) {
            return;
        }

        String msg = String.format(fmt, args);
        long cur_time = Calendar.getInstance().getTimeInMillis();
        float time = Utils.MsecToSec(cur_time - m_startup_time);

        if(cb != null) {
            cb.Run(
              String.format("%s @ %s", lvl, m_formatter.format(time)));
        }

        String str = String.format("%s :: %04d:%08d :: %s :: %s :: %s", 
          m_formatter.format(time), Thread.currentThread().getId(), 
          m_frame, lvl, m_prefix, msg);

        // Write new step
        if(m_is_new_step) {
            m_is_new_step = false;
            WriteStr("");
        }

        // Write message body
        WriteStr(str);
    }

    // -------------------------------------------------------------------------
    public static void d(String fmt, Object... args) {
        Write("DBG", fmt, null, args);
    }

    // -------------------------------------------------------------------------
    public static void i(String fmt, Object... args) {
        Write("INF", fmt, null, args);
    }

    // -------------------------------------------------------------------------
    public static void e(String fmt, Object... args) {
        Write("ERR", fmt, m_error_cb, args);
    }

    // *************************************************************************
    // Logger
    // *************************************************************************
    public abstract Logger Init();
    public abstract void WriteRaw(String str);
}
