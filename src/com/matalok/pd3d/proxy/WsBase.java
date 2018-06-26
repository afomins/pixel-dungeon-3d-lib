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
import com.matalok.pd3d.proxy.Interfaces.IProxy;
import com.matalok.pd3d.proxy.Interfaces.IProxyListener;
import com.matalok.pd3d.shared.Logger;

//------------------------------------------------------------------------------
public class WsBase
  implements IProxy {
    //**************************************************************************
    // WsBase
    //**************************************************************************
    protected IProxyListener m_listener;
    protected MsgLogger m_msg_handler;

    //--------------------------------------------------------------------------
    public WsBase(MsgLogger msg_handler) {
        m_msg_handler = msg_handler;
    }

    //**************************************************************************
    // IProxy
    //**************************************************************************
    @Override public void SetListener(IProxyListener listener) {
        m_listener = listener;
    }

    //--------------------------------------------------------------------------
    @Override public void Start() {
        Logger.d("Starting WS %s", m_msg_handler.GetName());
    }

    //--------------------------------------------------------------------------
    @Override public void Stop() {
        Logger.d("Stopping WS %s", m_msg_handler.GetName());
    }

    //--------------------------------------------------------------------------
    @Override public void Send(Msg msg) {
        m_msg_handler.OnSend(msg);
    }

    //--------------------------------------------------------------------------
    @Override public void Process() {
    }
}
