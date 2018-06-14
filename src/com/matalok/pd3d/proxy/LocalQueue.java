//------------------------------------------------------------------------------
package com.matalok.pd3d.proxy;

//------------------------------------------------------------------------------
import java.util.LinkedList;
import com.matalok.pd3d.msg.Msg;

//------------------------------------------------------------------------------
public class LocalQueue {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static LocalQueue server = new LocalQueue();
    public static LocalQueue client = new LocalQueue();
    public static void Reset() {
        server.m_queue.clear();
        client.m_queue.clear();
    }

    //**************************************************************************
    // LocalQueue
    //**************************************************************************
    private LinkedList<Msg> m_queue;

    //--------------------------------------------------------------------------
    public LocalQueue() {
        m_queue = new LinkedList<Msg>();
    }

    //--------------------------------------------------------------------------
    public void Write(Msg msg) {
        m_queue.add(msg);
    }

    //--------------------------------------------------------------------------
    public Msg Read() {
        return m_queue.pop();
    }

    //--------------------------------------------------------------------------
    public int GetSize() {
        return m_queue.size();
    }
}
