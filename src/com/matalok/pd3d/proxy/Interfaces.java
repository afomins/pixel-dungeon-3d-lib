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
public class Interfaces {
    //--------------------------------------------------------------------------
    public interface IProxy { // ClientWs, ServerWs
        void SetListener(IProxyListener listener);
        void Start();
        void Stop();
        void Send(Msg msg);
        void Process();
    }

    //--------------------------------------------------------------------------
    public interface IProxyBase { // ProxyClient, ProxyServer
        void SetListener(IProxyListener listener);
        boolean IsConnected();
        void Start(int log_level, String address, int port);
        void Stop();
        void Send(Msg msg);
        void Process();
    }

    //--------------------------------------------------------------------------
    public interface IProxyListener {
        void OnConnected();
        void OnDisconnected();
        void OnRecv(Msg msg);
        void OnError();
    }

    //--------------------------------------------------------------------------
    public interface IProxyQueueHandler {
        void Process(Object msg);
    }
}
