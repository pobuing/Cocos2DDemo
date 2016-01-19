package com.d9ing.cocos2ddemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.d9ing.cocos2ddemo.layer.ActionLayer;
import com.d9ing.cocos2ddemo.layer.FirstLayer;
import com.d9ing.cocos2ddemo.layer.MapLayer;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

public class MainActivity extends Activity {

    private CCGLSurfaceView ccglSurfaceView;
    private CCDirector ccDirector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ccglSurfaceView = new CCGLSurfaceView(this);
        setContentView(ccglSurfaceView);
        ccDirector = CCDirector.sharedDirector();
        //开启一个线程
        ccDirector.attachInView(ccglSurfaceView);
        //设置横屏
        ccDirector.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
        //设置展示帧率
        ccDirector.setDisplayFPS(true);
        //锁定帧率，向下锁定
        //设置屏幕大小,可以自动屏幕适配
        ccDirector.setScreenSize(480,320);
        //创建一个场景
        CCScene ccScene = CCScene.node();
        //场景添加图层
//        ccScene.addChild(new FirstLayer());
//        ccScene.addChild(new ActionLayer());
        ccScene.addChild(new MapLayer());
        //加载场景
        ccDirector.runWithScene(ccScene);
    }
    /**
     * 控制生命周期
     */
    @Override
    protected void onPause() {
        super.onPause();
        ccDirector.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        ccDirector.onResume();
    }
}
