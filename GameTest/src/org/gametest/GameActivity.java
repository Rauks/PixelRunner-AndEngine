package org.gametest;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.andengine.engine.Engine;
import org.andengine.engine.LimitedFPSEngine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.IGameInterface;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.FileUtils;
import org.andengine.util.call.Callable;
import org.andengine.util.call.Callback;
import org.gametest.manager.ResourcesManager;
import org.gametest.manager.SceneManager;
import org.helllabs.android.xmp.ModPlayer;

public class GameActivity extends BaseGameActivity{
    private Camera camera;
    private ResourcesManager resourcesManager;
    private final ModPlayer mModPlayer = ModPlayer.getInstance();
    
    public void playModFile(final String dir, final String file){
        if(FileUtils.isFileExistingOnExternalStorage(this, dir + file)) {
            this.startPlayingModFile(dir, file);
        } 
        else {
            GameActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        FileUtils.ensureDirectoriesExistOnExternalStorage(GameActivity.this, dir);
                        FileUtils.copyToExternalStorage(GameActivity.this, dir + file, dir + file);
                        GameActivity.this.startPlayingModFile(dir, file);
                    } catch (IOException ex) {
                        Logger.getLogger(GameActivity.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }
    }
    private void startPlayingModFile(final String dir, final String file){
        this.mModPlayer.play(FileUtils.getAbsolutePathOnExternalStorage(this, dir + file), true);
    }
    
    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0, 0, 800, 480);
        EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(800, 480), this.camera);
        engineOptions.getAudioOptions().setNeedsMusic(true).setNeedsSound(true);
        engineOptions.setWakeLockOptions(WakeLockOptions.SCREEN_ON);
        return engineOptions;
    }
    
    @Override
    public Engine onCreateEngine(EngineOptions pEngineOptions) 
    {
        return new LimitedFPSEngine(pEngineOptions, 60);
    }

    @Override
    public void onCreateResources(IGameInterface.OnCreateResourcesCallback pOnCreateResourcesCallback) throws IOException {
        ResourcesManager.prepareManager(mEngine, this, camera, getVertexBufferObjectManager());
        resourcesManager = ResourcesManager.getInstance();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    public void onCreateScene(IGameInterface.OnCreateSceneCallback pOnCreateSceneCallback) throws IOException {
        SceneManager.getInstance().createSplashScene(pOnCreateSceneCallback);
    }

    @Override
    public void onPopulateScene(Scene pScene, IGameInterface.OnPopulateSceneCallback pOnPopulateSceneCallback) throws IOException {
        mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() 
        {
                public void onTimePassed(final TimerHandler pTimerHandler) 
                {
                    mEngine.unregisterUpdateHandler(pTimerHandler);
                    // load menu resources, create menu scene
                    // set menu scene using scene manager
                    // disposeSplashScene();
                    // READ NEXT ARTICLE FOR THIS PART.
                }
        }));
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mModPlayer.stop();
    }
}
