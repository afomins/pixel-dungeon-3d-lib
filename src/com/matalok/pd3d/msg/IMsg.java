//------------------------------------------------------------------------------
package com.matalok.pd3d.msg;

//------------------------------------------------------------------------------
public interface IMsg {
    //**************************************************************************
    // ENUMS
    //**************************************************************************
    public enum Type {
        REQUEST, RESPONSE
    }

    //**************************************************************************
    // IMsg
    //**************************************************************************
    public boolean Validate();
    public boolean ValidateRequest();
    public boolean ValidateResponse();
    public boolean OnValidateError(String desc, String fmt, Object...args);
    public String ToJsonString();
    public String ToShortString();
}
