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
