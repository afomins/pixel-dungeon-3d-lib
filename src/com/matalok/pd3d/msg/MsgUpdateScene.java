//------------------------------------------------------------------------------
package com.matalok.pd3d.msg;

//------------------------------------------------------------------------------
import java.util.LinkedList;
import com.matalok.pd3d.desc.DescSceneGame;
import com.matalok.pd3d.desc.DescSceneInterlevel;
import com.matalok.pd3d.desc.DescSceneIntro;
import com.matalok.pd3d.desc.DescSceneStart;
import com.matalok.pd3d.desc.DescSceneTitle;
import com.matalok.pd3d.shared.Utils;

//------------------------------------------------------------------------------
public class MsgUpdateScene
  extends Msg {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static MsgUpdateScene CreateRequest() {
        MsgUpdateScene msg = (MsgUpdateScene)Msg.CreateRequest(
          new MsgUpdateScene(), "update-scene");
        return msg;
    }

    //**************************************************************************
    // MsgUpdateScene
    //**************************************************************************
    public String scene_name;
    public LinkedList<String> log_lines;
    public LinkedList<String> info_lines;

    // Scenes
    public DescSceneTitle title_scene;
    public DescSceneGame game_scene;
    public DescSceneInterlevel interlevel_scene;
    public DescSceneIntro intro_scene;
    public DescSceneStart start_scene;

    //**************************************************************************
    // IMsg
    //**************************************************************************
    @Override public boolean ValidateRequest() {
        return true;
    }

    //--------------------------------------------------------------------------
    @Override public boolean ValidateResponse() {
        // Mandatory params
        if(!Utils.NotNull(scene_name)) {
            return OnValidateError("parsing error", null);
        }

        // Scene
        IMsg scene = 
          scene_name.equals("scene-game") ? game_scene :
          scene_name.equals("scene-title") ? title_scene :
          scene_name.equals("scene-inter-level") ? interlevel_scene :
          scene_name.equals("scene-start") ? start_scene :
          scene_name.equals("scene-intro") ? intro_scene : null;
        if(scene == null || !scene.Validate()) {
            return OnValidateError("scene error", "scene-name=%s", 
              (scene == null) ? "null" : scene_name);
        }
        return true;
    }
}
