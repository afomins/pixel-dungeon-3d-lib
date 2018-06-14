//------------------------------------------------------------------------------
package com.matalok.pd3d.map;

//------------------------------------------------------------------------------
import java.io.IOException;
import org.java_websocket.util.Base64;
import com.matalok.pd3d.shared.Logger;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class Map {
    //**************************************************************************
    // INTERFACES
    //**************************************************************************
    public interface ICell {
        int GetRawSize();
        void Serialize(byte[] dest, int ptr);
        void Deserialize(byte src[], int ptr);
    }

    //**************************************************************************
    // Map
    //**************************************************************************
    private int m_width, m_height;
    private int m_cell_size, m_raw_size;
    private ICell[] m_cells;
    private byte[] m_raw;

    //--------------------------------------------------------------------------
    public Map(ICell[] cells, int width, int height) {
        // Size of map
        m_width = width;
        m_height = height;
        m_cell_size = cells[0].GetRawSize();
        Logger.d("Creating map :: width=%d height=%d raw-cell-size=%d", 
          width, height, m_cell_size);

        // Inherit data from caller
        m_cells = cells;
        Utils.Assert(cells.length == width * height, 
          "Failed to create map, wrong size");

        // Create raw array
        m_raw_size = m_width * m_height * m_cell_size;
        m_raw = new byte[m_raw_size];
    }

    //--------------------------------------------------------------------------
    public int GetWidth() {
        return m_width;
    }

    //--------------------------------------------------------------------------
    public int GetHeight() {
        return m_height;
    }

    //--------------------------------------------------------------------------
    public ICell[] GetCells() {
        return m_cells;
    }

    //--------------------------------------------------------------------------
    public String SerializeMap() {
        // Serialize cells
        int offset = 0;
        for(ICell cell : m_cells) {
            cell.Serialize(m_raw, offset);
            offset += m_cell_size;
        }

        // Serialize byte array to string
        return Base64.encodeBytes(m_raw);
    }

    //--------------------------------------------------------------------------
    public boolean DeserializeMap(String data) {
        // Deserialize byte array
        try {
            m_raw = Base64.decode(data);
        } catch (IOException ex) {
            Utils.LogException(ex, "Failed to deserialize map");
            return false;
        }

        // Validate size
        if(m_raw.length != m_raw_size) { 
            Logger.d("Failed to deserialize map, wrong size :: size=%s expected=%d", 
              m_raw.length, m_raw_size);
            return false;
        }

        // Deserialize cells
        int offset = 0;
        for(ICell cell : m_cells) {
            cell.Deserialize(m_raw, offset);
            offset += m_cell_size;
        }
        Logger.d("Deserialization ok :: cell-num=%d data-size=%d", 
          offset / m_cell_size, data.length());
        return true;
    }
}
