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
