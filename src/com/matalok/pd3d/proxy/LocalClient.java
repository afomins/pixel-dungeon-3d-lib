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
