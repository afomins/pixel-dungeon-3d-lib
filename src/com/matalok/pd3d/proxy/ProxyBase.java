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
import com.matalok.pd3d.proxy.ProxyQueue;
import com.matalok.pd3d.msg.Msg;
import com.matalok.pd3d.proxy.Interfaces.IProxy;
import com.matalok.pd3d.proxy.Interfaces.IProxyBase;
import com.matalok.pd3d.proxy.Interfaces.IProxyListener;
import com.matalok.pd3d.shared.Logger;

//------------------------------------------------------------------------------
public abstract class ProxyBase 
  implements IProxyBase, IProxyListener {
    //**************************************************************************
    // ProxyMsg
    //**************************************************************************
    private static class ProxyMsg {
        public enum Type { CONNECT, DISCONNECT, RECV, ERROR };
        public Type type;
        public Object arg;
        public ProxyMsg(Type _type, Object _arg) {
            type = _type; arg = _arg;
        }
        public ProxyMsg(Type _type) {
            this(_type, null);
        }
    }

    //**************************************************************************
    // ProxyBase
    //**************************************************************************
    private IProxy m_backend;
    private IProxyListener m_listener;
    private ProxyQueue m_queue_out, m_queue_in;
    private boolean m_is_connected;

    //--------------------------------------------------------------------------
    public ProxyBase() {
        m_queue_out = new ProxyQueue("out");
        m_queue_in = new ProxyQueue("in");
    }

    //--------------------------------------------------------------------------
    public void SetListener(IProxyListener listener) {
        m_listener = listener;
    }

    //--------------------------------------------------------------------------
    public boolean IsConnected() {
        return m_is_connected;
    }

    //--------------------------------------------------------------------------
    public IProxy CreateBackend(int log_level, String address, int port) {
        return null;
    }

    //--------------------------------------------------------------------------
    public Object PeekLast(boolean in) {
        ProxyQueue queue = (in) ? m_queue_in : m_queue_out;
        ProxyMsg last = (ProxyMsg)queue.PeekLast();
        return (last != null) ? last.arg : null;
    }

    //**************************************************************************
    // IProxyBase
    //**************************************************************************
    @Override public void Start(int log_level, String address, int port) {
        m_backend = CreateBackend(log_level, address, port);
        m_backend.SetListener(this);
        m_backend.Start();
    }

    //--------------------------------------------------------------------------
    @Override public void Stop() {
        if(m_backend == null) {
            Logger.e("Failed to stop base-proxy, not connected");
            return;
        }
        m_backend.Stop();
        m_backend = null;
        m_is_connected = false;
    }

    //--------------------------------------------------------------------------
    @Override public void Send(Msg msg) {
        m_queue_out.Put(msg);

//        m_backend.Send((Msg)msg);
    }

    //--------------------------------------------------------------------------
    @Override public void Process() {
        // Run backend messaging
        m_backend.Process();

        // Process input queue
        m_queue_in.Process(new ProxyQueue.MsgHandler() {
            @Override public void Process(Object msg_obj) {
                ProxyMsg msg = (ProxyMsg)msg_obj;
                if(msg.type == ProxyMsg.Type.RECV) {
                    m_listener.OnRecv((Msg)msg.arg);
                } else if(msg.type == ProxyMsg.Type.CONNECT) {
                    m_listener.OnConnected();
                } else if(msg.type == ProxyMsg.Type.DISCONNECT) {
                    m_listener.OnDisconnected();
                } else if(msg.type == ProxyMsg.Type.ERROR) {
                    m_listener.OnError();
                }
            }
        });

        // Process output queue
        m_queue_out.Process(new ProxyQueue.MsgHandler() {
            @Override public void Process(Object msg) {
                m_backend.Send((Msg)msg);
            }
        });
    }

    // *************************************************************************
    // IProxyListener
    // *************************************************************************
    @Override public void OnConnected() {
        m_queue_in.Put(new ProxyMsg(ProxyMsg.Type.CONNECT));
        m_is_connected = true;
    }

    //--------------------------------------------------------------------------
    @Override public void OnDisconnected() {
        m_queue_in.Put(new ProxyMsg(ProxyMsg.Type.DISCONNECT));
        m_is_connected = false;
    }

    //--------------------------------------------------------------------------
    @Override public void OnRecv(Msg msg) {
        m_queue_in.Put(new ProxyMsg(ProxyMsg.Type.RECV, msg));
    }

    //--------------------------------------------------------------------------
    @Override public void OnError() {
        m_queue_in.Put(new ProxyMsg(ProxyMsg.Type.ERROR));
    }
}
