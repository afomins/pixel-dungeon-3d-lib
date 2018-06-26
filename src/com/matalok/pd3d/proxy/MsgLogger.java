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
package com.matalok.pd3d.proxy;

//------------------------------------------------------------------------------
import com.matalok.pd3d.msg.Msg;
import com.matalok.pd3d.shared.Logger;

//------------------------------------------------------------------------------
public class MsgLogger {
    //**************************************************************************
    // MsgLogger
    //**************************************************************************
    private int m_log_level;
    private String m_name;
    private String m_recv_prefix; 
    private String m_send_prefix;

    //--------------------------------------------------------------------------
    public MsgLogger(int log_level, String name) {
        m_log_level = log_level;
        m_name = name;
        m_recv_prefix = name.equals("client") ? "C <<< S" : "C >>> S";
        m_send_prefix = name.equals("client") ? "C >>> S" : "C <<< S";
    }

    //--------------------------------------------------------------------------
    public String GetName() {
        return m_name;
    }

    //--------------------------------------------------------------------------
    public void OnSend(Msg msg) {
        if(m_log_level > 0) {
            Logger.d("%s :: msg=%s", m_send_prefix, msg.ToShortString());
        }
    }

    //--------------------------------------------------------------------------
    public void OnRecv(Msg msg) {
        if(m_log_level == 1) {
            Logger.d("%s :: msg=%s", m_recv_prefix, 
              msg.ToShortString());
        } else if(m_log_level == 2) {
            Logger.d("%s :: msg=%s json=%s", 
              m_recv_prefix, msg.ToShortString(), msg.ToJsonString());
        }
    }
}
