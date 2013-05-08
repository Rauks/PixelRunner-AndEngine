/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gametest.manager;

import org.andengine.engine.Engine;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;
import org.gametest.base.BaseScene;
import org.gametest.scene.SplashScene;

/**
 *
 * @author Karl
 */
public class SceneManager{
    private BaseScene splashScene;
    private BaseScene menuScene;
    private BaseScene gameScene;
    private BaseScene loadingScene;
    
    public enum SceneType{
        SCENE_SPLASH,
        SCENE_MENU,
        SCENE_GAME,
        SCENE_LOADING,
    }
    
    private static final SceneManager INSTANCE = new SceneManager();
    private SceneType currentSceneType = SceneType.SCENE_SPLASH;
    private BaseScene currentScene;
    private Engine engine = ResourcesManager.getInstance().engine;
    
    public void setScene(BaseScene scene){
        engine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }
    
    public void setScene(SceneType sceneType){
        switch (sceneType){
            case SCENE_MENU:
                setScene(menuScene);
                break;
            case SCENE_GAME:
                setScene(gameScene);
                break;
            case SCENE_SPLASH:
                setScene(splashScene);
                break;
            case SCENE_LOADING:
                setScene(loadingScene);
                break;
            default:
                break;
        }
    }
    
    public SceneType getCurrentSceneType(){
        return currentSceneType;
    }
    
    public BaseScene getCurrentScene(){
        return currentScene;
    }
    
    public static SceneManager getInstance(){
        return INSTANCE;
    }
    
    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback)
    {
        ResourcesManager.getInstance().loadSplashScreen();
        splashScene = new SplashScene();
        currentScene = splashScene;
        pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
    }
    
    private void disposeSplashScene()
    {
        ResourcesManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }
}
