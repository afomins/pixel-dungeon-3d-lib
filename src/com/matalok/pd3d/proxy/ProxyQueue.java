/*
 * Pixel Dungeon 3D
 * Copyright (C) 2016-2018 Alex Fomins
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

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
