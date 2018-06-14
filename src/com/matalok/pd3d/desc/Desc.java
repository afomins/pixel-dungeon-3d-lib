//------------------------------------------------------------------------------
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
import java.util.Iterator;
import com.matalok.pd3d.msg.IMsg;
import com.matalok.pd3d.shared.GsonUtils;
import com.matalok.pd3d.shared.Logger;

//------------------------------------------------------------------------------
public abstract class Desc 
  implements IMsg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static boolean ValidateIt(Iterator<? extends Desc> it, String type, 
      String fmt, Object... args) {
        if(!it.hasNext()) {
            Logger.e("Failed to validate desc, empty iterator :: type=%s %s",
              type, (fmt != null) ? String.format(fmt, args) : "");
            return false;
        }

        while(it.hasNext()) {
            Desc desc = (Desc)it.next();
            if(!desc.Validate()) {
                return desc.OnValidateError(type + " error", fmt, args);
            }
        }
        return true;
    }

    //--------------------------------------------------------------------------
    public static boolean ValidateArray(Object[] desc_array, String type, 
      String fmt, Object... args) {
        for(int i = 0; i < desc_array.length; i++) {
            Desc desc = (Desc)desc_array[i];
            if(desc == null) {
                continue;
            }
            if(!desc.Validate()) {
                return desc.OnValidateError(type + " error", fmt, args);
            }
        }
        return true;
    }

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean OnValidateError(String desc, String fmt, 
      Object...args) {
        Logger.e("Failed to validate desc, %s :: class=%s %s",
          desc, getClass().getSimpleName(), 
          (fmt != null) ? String.format(fmt, args) : "");
        return false;
    }

    //--------------------------------------------------------------------------
    @Override public String ToJsonString() {
        return GsonUtils.Serialize(this, false);
    }

    //--------------------------------------------------------------------------
    @Override public String ToShortString() {
        return getClass().getSimpleName();
    }
}
