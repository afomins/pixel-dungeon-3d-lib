//------------------------------------------------------------------------------
package com.matalok.pd3d.proxy;

//------------------------------------------------------------------------------
import com.matalok.pd3d.msg.Msg;
import com.matalok.pd3d.shared.Logger;

//------------------------------------------------------------------------------
public class MsgLogger {
    //**************************************************************************
    // MsgLogger
    //**************************************************************************
    private int m_log_level;
    private String m_name;
    private String m_recv_prefix; 
    private String m_send_prefix;

    //--------------------------------------------------------------------------
    public MsgLogger(int log_level, String name) {
        m_log_level = log_level;
        m_name = name;
        m_recv_prefix = name.equals("client") ? "C <<< S" : "C >>> S";
        m_send_prefix = name.equals("client") ? "C >>> S" : "C <<< S";
    }

    //--------------------------------------------------------------------------
    public String GetName() {
        return m_name;
    }

    //--------------------------------------------------------------------------
    public void OnSend(Msg msg) {
        if(m_log_level > 0) {
            Logger.d("%s :: msg=%s", m_send_prefix, msg.ToShortString());
        }
    }

    //--------------------------------------------------------------------------
    public void OnRecv(Msg msg) {
        if(m_log_level == 1) {
            Logger.d("%s :: msg=%s", m_recv_prefix, 
              msg.ToShortString());
        } else if(m_log_level == 2) {
            Logger.d("%s :: msg=%s json=%s", 
              m_recv_prefix, msg.ToShortString(), msg.ToJsonString());
        }
    }
}
