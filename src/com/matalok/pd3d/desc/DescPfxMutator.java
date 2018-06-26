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
package com.matalok.pd3d.desc;

//------------------------------------------------------------------------------
public class DescPfxMutator 
  extends Desc {
    //**************************************************************************
    // Fields
    //**************************************************************************
    public enum Field {
        COLORS, OFFSET, SPAWN_SHAPE, PARTICLE_NUM, 
        BROW_STRENGTH, SCALE, IMAGE_ID;
    };

    //**************************************************************************
    // DescPfxMutator
    //**************************************************************************
    public Integer[] colors;
    public DescVector3f offset;
    public DescVector3f spawn_shape;
    public DescRange2i particle_num;
    public DescRange2f brow_strength;
    public DescRange2f scale;
    public Integer image_id;

    //----------------------------------------------------------------------
    public DescPfxMutator() {
    }

    //----------------------------------------------------------------------
    public DescPfxMutator(DescPfxMutator m) {
        Copy(m);
    }

    //----------------------------------------------------------------------
    public DescPfxMutator Copy(DescPfxMutator m) {
        if(m.colors != null) Set(Field.COLORS, (Object[])m.colors);
        if(m.offset != null) Set(Field.OFFSET, m.offset);
        if(m.spawn_shape != null) Set(Field.SPAWN_SHAPE, m.spawn_shape);
        if(m.particle_num != null) Set(Field.PARTICLE_NUM, m.particle_num);
        if(m.brow_strength != null) Set(Field.BROW_STRENGTH, m.brow_strength);
        if(m.scale != null) Set(Field.SCALE, m.scale);
        if(m.image_id != null) Set(Field.IMAGE_ID, m.image_id);
        return this;
    }

    //----------------------------------------------------------------------
    public DescPfxMutator Reset() {
        colors = null;
        offset = spawn_shape = null;
        particle_num = null;
        brow_strength = scale = null;
        image_id = null;
        return this;
    }

    //----------------------------------------------------------------------
    public DescPfxMutator Set(Field field, Object... args) {
        Object value = null;
        switch(field) {
        // Integer array
        case COLORS: {
            if(colors == null || colors.length != args.length) {
                colors = new Integer[args.length];
            }
            System.arraycopy(args, 0, colors, 0, colors.length);
        } break;

        // DescVector3f
        case OFFSET:
        case SPAWN_SHAPE: {
            value = (args[0] instanceof DescVector3f) ? 
              new DescVector3f((DescVector3f)args[0]) :
              new DescVector3f((Float)args[0], (Float)args[1], (Float)args[2]);
        } break;

        // DescRange2i
        case PARTICLE_NUM: {
            value = (args[0] instanceof DescRange2i) ?
              new DescRange2i((DescRange2i)args[0]) :
              new DescRange2i((Integer)args[0], (Integer)args[1]);
        } break;

        // DescRange2f
        case BROW_STRENGTH: 
        case SCALE: {
            value = (args[0] instanceof DescRange2f) ?
              new DescRange2f((DescRange2f)args[0]) :
              new DescRange2f((Float)args[0], (Float)args[1]);
        } break;

        // Integer
        case IMAGE_ID: {
            image_id = (args[0] instanceof Enum<?>) ? 
              ((Enum<?>)args[0]).ordinal() : (Integer)args[0];
        } break;
        }

        if(value != null) {
            Finalize(field, value);
        }
        return this;
    }

    //----------------------------------------------------------------------
    private DescPfxMutator Finalize(Field field, Object value) {
             if(field == Field.COLORS) colors = (Integer[])value;
        else if(field == Field.OFFSET) offset = (DescVector3f)value;
        else if(field == Field.SPAWN_SHAPE) spawn_shape = (DescVector3f)value;
        else if(field == Field.PARTICLE_NUM) particle_num = (DescRange2i)value;
        else if(field == Field.BROW_STRENGTH) brow_strength = (DescRange2f)value;
        else if(field == Field.SCALE) scale = (DescRange2f)value;
        else if(field == Field.IMAGE_ID) image_id = (Integer)value;
        return this;
    }

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean Validate() {
        return true;
    }
}
