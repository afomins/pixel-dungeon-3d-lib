//------------------------------------------------------------------------------
package com.matalok.pd3d.proxy;

//------------------------------------------------------------------------------
import java.net.URI;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.matalok.pd3d.msg.Msg;
import com.matalok.pd3d.shared.Logger;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class WsClient
  extends WsBase {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static URI CreateUri(String addr, int port) {
        try {
            return new URI(String.format("ws://%s:%d", addr, port));
        } catch (Exception ex) {
            Utils.LogException(ex, 
              "Failed to create URI :: add=%s port=%d", addr, port);
        }
        return null;
    }

    //**************************************************************************
    // WsClient
    //**************************************************************************
    private WebSocketClient m_client;

    //--------------------------------------------------------------------------
    public WsClient(int log_level, URI server_uri) {
        super(new MsgLogger(log_level, "client"));
        m_client = new WebSocketClient(server_uri) {
            //..................................................................
            @Override public void onOpen(ServerHandshake handshakedata) {
                Logger.d("Creating WS client connection :: local=%s remote=%s", 
                  getLocalSocketAddress().toString(),
                  getRemoteSocketAddress().toString());
                m_listener.OnConnected();
            }

            //..................................................................
            @Override public void onClose(int code, String reason, boolean remote) {
                Logger.d("Closing WS client conenction :: code=%s reason=%s", 
                  code, reason);
                m_listener.OnDisconnected();
            }

            //..................................................................
            @Override public void onMessage(String message) {
                Msg msg = Msg.Deserialize(message);
                if(msg == null) {
                    return;
                }
                m_msg_handler.OnRecv(msg);
                m_listener.OnRecv(msg);
            }

            //..................................................................
            @Override public void onError(Exception ex) {
                Utils.LogException(ex, "Error in WS client");
                m_listener.OnError();
            }
        };
    }

    //**************************************************************************
    // IProxy
    //**************************************************************************
    @Override public synchronized void Start() {
        super.Start();
        m_client.connect();
    }

    //--------------------------------------------------------------------------
    @Override public synchronized void Stop() {
        super.Stop();
        m_client.close();
    }

    //--------------------------------------------------------------------------
    @Override public synchronized void Send(Msg msg) {
        super.Send(msg);
        m_client.send(msg.ToJsonStringEx());
    }
}
