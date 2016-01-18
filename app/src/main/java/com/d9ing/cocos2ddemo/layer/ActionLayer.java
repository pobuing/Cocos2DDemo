package com.d9ing.cocos2ddemo.layer;

import android.support.annotation.NonNull;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;

/**
 * 动作的图层
 * Created by wx on 2016/1/18.
 */
public class ActionLayer extends CCLayer {

    public ActionLayer() {
        init();
    }

    /**
     * 初始化方法
     */
    private void init() {
        moveTo();
        moveBy();
        jumpBy();
    }

    /**
     * 跳跃方法
     */
    private void jumpBy() {
        //1.时间 单位秒 2.目的地 3.最高点距落地点 4.次数
        CCJumpBy ccJumpBy = CCJumpBy.action(2, ccp(200, 200), 50, 5);
        CCJumpBy reverse = ccJumpBy.reverse();
        CCSequence sequence = CCSequence.actions(ccJumpBy, reverse);
        CCRepeatForever forever = CCRepeatForever.action(sequence);
        getCcSprite().runAction(forever);
    }

    private void moveBy() {
        CCSprite ccSprite = getCcSprite();
        ccSprite.setPosition(0, 100);
        CCMoveBy ccMoveBy = CCMoveBy.action(2, CCNode.ccp(200, 0));
        CCMoveBy reverse = ccMoveBy.reverse();
        // 动作序列 动作的顺序
        CCSequence sequence = CCSequence.actions(ccMoveBy, reverse);
        ccSprite.runAction(sequence);
    }

    private void moveTo() {
        CCSprite sprite = getCcSprite();
        //参数1 移动的时间 参数2 移动的目的地
        CCMoveTo ccMoveTo = CCMoveTo.action(2, CCNode.ccp(200, 0));
        sprite.runAction(ccMoveTo);
    }

    @NonNull
    private CCSprite getCcSprite() {
        CCSprite sprite = CCSprite.sprite("z_1_attack_01.png");
        //修改锚点
        sprite.setAnchorPoint(0,0);
        this.addChild(sprite);
        return sprite;
    }
}
