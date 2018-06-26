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

//------------------------------------------------------------------------------
public class LocalServer
  extends LocalBase {
    //**************************************************************************
    // LocalServer
    //**************************************************************************
    public LocalServer(int log_level, LocalQueue queue_recv, LocalQueue queue_send) {
        super(new MsgLogger(log_level, "server"), 
          queue_recv, queue_send);
    }

    //**************************************************************************
    // LocalBase
    //**************************************************************************
    public void OnRecvConnecting(Msg msg) {
        super.OnRecvConnecting(msg);
        m_listener.OnRecv(msg);
    }
}
