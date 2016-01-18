package com.d9ing.cocos2ddemo.layer;


import android.view.MotionEvent;
import android.widget.Toast;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

/**
 *
 * Created by wx on 2016/1/15.
 */
public class FirstLayer extends CCLayer {
    private CCSprite ccSprite;
    private CCSprite bgSprite;
    public FirstLayer() {
        //打开触摸事件开关
        setIsTouchEnabled(true);
        init();
    }

    private void init() {

        bgSprite = CCSprite.sprite("bk1.jpg");
        bgSprite.setAnchorPoint(0,0);
        this.addChild(bgSprite);


        ccSprite = CCSprite.sprite("z_1_attack_01.png");

        //设置锚点
        ccSprite.setAnchorPoint(0,0);
        //添加到图层
        this.addChild(ccSprite);
    }
    //按下事件
    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        //坐标系转换 Android--->Cocos2D
        CGPoint point = this.convertTouchToNodeSpace(event);
        //获取模型框体
        CGRect box = ccSprite.getBoundingBox();
        //判断点是否在框体中
        boolean containsPoint = CGRect.containsPoint(box, point);
        if (containsPoint) {
        System.out.println("按下事件");
            ccSprite.setScale(ccSprite.getScale()+0.2);
        }else{
            ccSprite.setScale(ccSprite.getScale()-0.2);
        }
        return super.ccTouchesBegan(event);
    }
}
