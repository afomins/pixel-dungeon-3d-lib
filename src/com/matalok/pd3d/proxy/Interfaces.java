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
