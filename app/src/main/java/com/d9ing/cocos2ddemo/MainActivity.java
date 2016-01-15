package com.d9ing.cocos2ddemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.d9ing.cocos2ddemo.layer.FirstLayer;

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
        //创建一个场景
        CCScene ccScene = CCScene.node();
        //场景添加图层
        ccScene.addChild(new FirstLayer());
        //加载场景
        ccDirector.runWithScene(ccScene);
    }
}
