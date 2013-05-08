/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gametest.scene;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;
import org.andengine.util.FileUtils;
import org.andengine.util.call.Callable;
import org.andengine.util.call.Callback;
import org.gametest.R;
import org.gametest.base.BaseScene;
import org.gametest.manager.SceneManager;
import org.gametest.manager.SceneManager.SceneType;
import org.helllabs.android.xmp.ModPlayer;

/**
 *
 * @author Karl
 */
public class SplashScene extends BaseScene{
    private Sprite splash;
    
    private static final String SAMPLE_MOD_DIRECTORY = "mfx/";
    private static final String SAMPLE_MOD_FILENAME = "test.xm";
        
    @Override
    public void createScene() {
        splash = new Sprite(0, 0, resourcesManager.splash_region, vbom);
        splash.setPosition(400, 240);
        attachChild(splash);
        this.activity.playModFile(SAMPLE_MOD_DIRECTORY, SAMPLE_MOD_FILENAME);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_SPLASH;
    }

    @Override
    public void disposeScene() {
        splash.detachSelf();
        splash.dispose();
        this.detachSelf();
        this.dispose();
    }

    @Override
    public void onBackKeyPressed() {
        
    }
    
}
