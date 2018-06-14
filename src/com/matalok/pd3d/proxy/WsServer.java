//------------------------------------------------------------------------------
package com.matalok.pd3d.proxy;

//------------------------------------------------------------------------------
import java.net.InetSocketAddress;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import com.matalok.pd3d.msg.Msg;
import com.matalok.pd3d.proxy.Interfaces.IProxyListener;
import com.matalok.pd3d.shared.Logger;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class WsServer 
  extends WsBase {
    //**************************************************************************
    // ServerWs
    //**************************************************************************
    private WebSocket m_client;
    private WebSocketServer m_server;

    //--------------------------------------------------------------------------
    public WsServer(int log_level, InetSocketAddress address) {
        super(new MsgLogger(log_level, "server"));
        m_server = new WebSocketServer(address) {
            //..................................................................
            @Override public void onOpen(WebSocket conn, 
              ClientHandshake handshake) {
                Logger.d("Creating WS server connection :: client=%s", 
                  conn.getRemoteSocketAddress());

                if(m_client != null) {
                    Logger.e(
                      "Failed to accept WS client, old client still active :: new=%s old=%s",
                      conn, m_client);
                    conn.close();
                    return;
                }
                m_client = conn;
                m_listener.OnConnected();
            }

            //..................................................................
            @Override public void onClose(WebSocket conn, int code, String reason, 
              boolean remote) {
                Logger.d("Closing WS server :: client=%s code=%d reason=%s", 
                  conn.getRemoteSocketAddress(), code, reason);
                m_listener.OnDisconnected();
                m_client = null;
            }

            //..................................................................
            @Override public void onMessage(WebSocket conn, String message) {
                Msg msg = Msg.Deserialize(message);
                if(msg == null) {
                    return;
                }
                m_msg_handler.OnRecv(msg);
                m_listener.OnRecv(msg);
            }

            //.................................................................
            @Override public void onError(WebSocket conn, Exception ex) {
                Utils.LogException(ex, "Error in WS server");
                m_listener.OnError();
            }

            //.................................................................
            @Override public void onStart() {
            }
        };
    }

    //**************************************************************************
    // IProxyServer
    //**************************************************************************
    @Override public synchronized void SetListener(IProxyListener listener) {
        m_listener = listener;
    }

    //--------------------------------------------------------------------------
    @Override public synchronized void Start() {
        super.Start();
        m_server.start();
    }

    //--------------------------------------------------------------------------
    @Override public synchronized void Stop() {
        super.Stop();
        try {
            m_server.stop();
        } catch (Exception ex) {
            Utils.LogException(ex, "Failed to stop WS server :: addr=%s", 
              m_server.getAddress());
        }
    }

    //--------------------------------------------------------------------------
    @Override public synchronized void Send(Msg msg) {
        super.Send(msg);
        if(m_client == null) {
            Logger.e("Failed to send WS message, no client");
            return;
        }
        m_client.send(msg.ToJsonStringEx());
    }
}
