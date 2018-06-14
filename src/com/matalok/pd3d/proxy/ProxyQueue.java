//------------------------------------------------------------------------------
package com.matalok.pd3d.proxy;

//------------------------------------------------------------------------------
import java.util.LinkedList;
import com.matalok.pd3d.proxy.Interfaces.IProxyQueueHandler;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class ProxyQueue {
    //**************************************************************************
    // MsgHandler
    //**************************************************************************
    public static abstract class MsgHandler 
      implements IProxyQueueHandler { };

    //**************************************************************************
    // ProxyQueue
    //**************************************************************************
    private String m_name;
    private LinkedList<Object> m_queue;

    //--------------------------------------------------------------------------
    public ProxyQueue(String name) {
        m_name = name;
        m_queue = new LinkedList<Object>();
    }

    //--------------------------------------------------------------------------
    public synchronized void Put(Object msg) {
        m_queue.add(msg);
    }

    //--------------------------------------------------------------------------
    public synchronized Object PeekLast() {
        return (m_queue.size() > 0) ? m_queue.getLast() : null;
    }

    //--------------------------------------------------------------------------
    public synchronized void Process(IProxyQueueHandler handler) {
        if(m_queue.isEmpty()) {
            return;
        }

//        while(!m_queue.isEmpty()) {
        try {
            handler.Process(m_queue.pop());
        } catch(Exception ex) {
            Utils.LogException(ex, "Failed to process queue :: name=%s", m_name);
        }
//        }
    }
}
