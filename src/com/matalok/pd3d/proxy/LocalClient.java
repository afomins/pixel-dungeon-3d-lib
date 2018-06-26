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
import com.matalok.pd3d.msg.MsgLocal;
import com.matalok.pd3d.shared.ClientAPI;

//------------------------------------------------------------------------------
public class LocalClient
  extends LocalBase {
    //**************************************************************************
    // LocalClient
    //**************************************************************************
    private ClientAPI m_client_api;

    //--------------------------------------------------------------------------
    public LocalClient(int log_level, LocalQueue queue_recv, 
      LocalQueue queue_send, ClientAPI client_api) {
        super(new MsgLogger(log_level, "client"), 
          queue_recv, queue_send);
        m_client_api = client_api;
    }

    //**************************************************************************
    // IProxyClient
    //**************************************************************************
    @Override public void Start() {
        super.Start();

        // Connect to server
        MsgLocal msg = MsgLocal.CreateRequest();
        msg.state = "connect";
        msg.client_api = m_client_api;
        Send(msg);
    }
}
