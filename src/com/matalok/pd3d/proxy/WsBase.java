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
