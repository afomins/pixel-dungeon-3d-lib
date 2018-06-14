//------------------------------------------------------------------------------
package com.matalok.pd3d.proxy;

//------------------------------------------------------------------------------
import com.matalok.pd3d.msg.Msg;
import com.matalok.pd3d.proxy.Interfaces.IProxy;
import com.matalok.pd3d.proxy.Interfaces.IProxyListener;
import com.matalok.pd3d.shared.Logger;

//------------------------------------------------------------------------------
public class LocalBase
  implements IProxy {
    //**************************************************************************
    // ENUM
    //**************************************************************************
    public enum State {
        //......................................................................
        DISCONNECTED,
        CONNECTING,
        CONNECTED,
        DISCONNECTING
    };

    //**************************************************************************
    // LocalBase
    //**************************************************************************
    protected State m_state;
    protected LocalQueue m_queue_recv;
    protected LocalQueue m_queue_send;
    protected IProxyListener m_listener;
    protected MsgLogger m_msg_handler;

    //--------------------------------------------------------------------------
    public LocalBase(MsgLogger msg_handler, LocalQueue queue_recv, 
      LocalQueue queue_send) {
        m_state = State.DISCONNECTED;
        m_queue_recv = queue_recv;
        m_queue_send = queue_send;
        m_msg_handler = msg_handler;
    }

    //--------------------------------------------------------------------------
    public void OnRecvConnecting(Msg msg) {
        m_state = State.CONNECTED;
        m_listener.OnConnected();
    }

    //--------------------------------------------------------------------------
    public void OnRecvConnected(Msg msg) {
        m_listener.OnRecv(msg);
    }

    //**************************************************************************
    // IProxyClient
    //**************************************************************************
    @Override public void SetListener(IProxyListener listener) {
        m_listener = listener;
    }

    //--------------------------------------------------------------------------
    @Override public void Start() {
        Logger.d("Starting local %s", m_msg_handler.GetName());
        m_state = State.CONNECTING;
    }

    //--------------------------------------------------------------------------
    @Override public void Stop() {
        Logger.d("Stopping local %s", m_msg_handler.GetName());
        m_state = State.DISCONNECTED;
        m_listener.OnDisconnected();
    }

    //--------------------------------------------------------------------------
    @Override public void Send(Msg msg) {
        m_msg_handler.OnSend(msg);
        m_queue_send.Write(msg);
    }

    //--------------------------------------------------------------------------
    @Override public void Process() {
        while(m_queue_recv.GetSize() > 0) {
            Msg msg = m_queue_recv.Read();
            State old_state = m_state;

            m_msg_handler.OnRecv(msg);
            switch(m_state) {
            case CONNECTING: {
                OnRecvConnecting(msg);
            } break;

            case CONNECTED: {
                OnRecvConnected(msg);
            } break;

            default: {
                Logger.e("Failed to recv message, wrong %s state :: state=%s", 
                  m_msg_handler.GetName(), m_state);
            } break;
            }

            if(old_state != m_state) {
                Logger.d("Switching %s state :: %s -> %s", 
                  m_msg_handler.GetName(), old_state, m_state);
            }
        }
    }
}
