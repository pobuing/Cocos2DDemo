package com.d9ing.cocos2ddemo.layer;


import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;

/**
 *
 * Created by wx on 2016/1/15.
 */
public class FirstLayer extends CCLayer {
    public FirstLayer() {
        init();
    }

    private void init() {
        CCSprite ccSprite = CCSprite.sprite("z_1_attack_01.png");
        //设置锚点
        ccSprite.setAnchorPoint(0,0);
        //添加到图层
        this.addChild(ccSprite);
    }
}
