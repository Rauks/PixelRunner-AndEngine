/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gametest.base;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.gametest.GameActivity;
import org.gametest.manager.ResourcesManager;
import org.gametest.manager.SceneManager.SceneType;

/**
 *
 * @author Karl
 */
public abstract class BaseScene extends Scene{
    protected Engine engine;
    protected GameActivity activity;
    protected ResourcesManager resourcesManager;
    protected VertexBufferObjectManager vbom;
    protected Camera camera;
    
    public BaseScene(){
        this.resourcesManager = ResourcesManager.getInstance();
        this.engine = resourcesManager.engine;
        this.activity = resourcesManager.activity;
        this.vbom = resourcesManager.vbom;
        this.camera = resourcesManager.camera;
        createScene();
    }
    
    public abstract void createScene();
    
    public abstract void onBackKeyPressed();
    
    public abstract SceneType getSceneType();
    
    public abstract void disposeScene();
}
