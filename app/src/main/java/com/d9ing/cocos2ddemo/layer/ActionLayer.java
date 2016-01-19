package com.d9ing.cocos2ddemo.layer;

import android.support.annotation.NonNull;

<<<<<<< HEAD
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.ease.CCEaseIn;
import org.cocos2d.actions.ease.CCEaseOut;
import org.cocos2d.actions.interval.CCBezierBy;
import org.cocos2d.actions.interval.CCBlink;
import org.cocos2d.actions.interval.CCFadeIn;
import org.cocos2d.actions.interval.CCIntervalAction;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCRotateBy;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.actions.interval.CCTintBy;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CCBezierConfig;
import org.cocos2d.types.ccColor3B;
=======
import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
>>>>>>> origin/master

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
<<<<<<< HEAD
//        moveTo();
//        moveBy();
//        jumpBy();
        jumpByRote();
//        scaleBy();
//        roteBy();
//        bezierBy();
//        fadeIn();
//          tint();
//        blink();
    }

    /**
     * 组合动作
     */
    private void jumpByRote() {
        //1.时间 单位秒 2.目的地 3.最高点距落地点 4.次数
        CCJumpBy ccJumpBy = CCJumpBy.action(2, ccp(200, 200), 50, 5);
        CCRotateBy ccRotateBy = CCRotateBy.action(2, 360);
        //并行动作 将动作并行起来
        CCSpawn ccSpawn = CCSpawn.actions(ccJumpBy, ccRotateBy);
        CCIntervalAction reverse = ccSpawn.reverse();
        CCSequence sequence = CCSequence.actions(ccSpawn, reverse);
        CCRepeatForever forever = CCRepeatForever.action(sequence);
        CCSprite ccSprite = getCcSprite();
        //设置锚点，改为自身位置
        ccSprite.setAnchorPoint(0.5f,0.5f);
        ccSprite.setPosition(50,50);
        ccSprite.runAction(forever);
    }

    /**
     * 闪烁的动作
     */
    private void blink() {
        //参数1 时间 参数2 次数
        CCBlink blink = CCBlink.action(10, 3);
        CCBlink reverse = blink.reverse();
        CCSequence sequence = CCSequence.actions(blink, reverse);
        CCRepeatForever forever = CCRepeatForever.action(sequence);
        CCSprite ccSprite = getCcSprite();
        ccSprite.setPosition(100,200);
        ccSprite.runAction(forever);
    }

    /**
     * 颜色渐变
     */
    private void tint() {
        //创建显示的精灵
        //参数1 显示的内容 参数2 字体的样式 参数3 字体大小
        CCLabel label = CCLabel.labelWithString("DDDDD9","hkbd.ttf",24);
        //设置初始颜色
        label.setColor(ccc3(50,0,255));
        label.setPosition(200, 200);
        this.addChild(label);
        //创建颜色类
        ccColor3B color3B = ccc3(100,255,-100);
        //参数1 时间 参数2 变化后的颜色
        CCTintBy ccTintBy = CCTintBy.action(2, color3B);
        CCTintBy reverse = ccTintBy.reverse();
        CCSequence sequence = CCSequence.actions(ccTintBy, reverse);
        CCRepeatForever forever = CCRepeatForever.action(sequence);
        label.runAction(forever);
    }

    /**
     * 淡入动作
     */
    private void fadeIn() {
        CCFadeIn fadeIn = CCFadeIn.action(10);
        CCSprite sprite = getCcSprite();
        sprite.setPosition(20,50);
        sprite.runAction(fadeIn);

    }

    /**
     * 贝塞尔曲线动作
     */
    private void bezierBy() {
        CCBezierConfig ccBezierConfig = new CCBezierConfig();
        ccBezierConfig.controlPoint_1 = ccp(0,0);
        ccBezierConfig.controlPoint_2 = ccp(100,100);
        ccBezierConfig.endPosition = ccp(120, 120);
        CCBezierBy ccBezierBy = CCBezierBy.action(3, ccBezierConfig);
        CCBezierBy reverse = ccBezierBy.reverse();
        CCSequence ccSequence = CCSequence.actions(ccBezierBy, reverse);
        CCRepeatForever forever = CCRepeatForever.action(ccSequence);
        getCcSprite().runAction(forever);
    }

    /**
     * 旋转动作
     */
    private void roteBy() {
        //参数 1 时间 2 旋转角度
        CCRotateBy ccRotateBy = CCRotateBy.action(2, 30);
        CCRotateBy reverse = ccRotateBy.reverse();
        CCSequence ccSequence = CCSequence.actions(ccRotateBy, reverse);
        CCRepeatForever forever = CCRepeatForever.action(ccSequence);
        getCcSprite().runAction(forever);
    }

    /**
     * 缩放的动作
     */
    private void scaleBy() {
        //参数 1.时间 2.缩放比例
        CCScaleBy ccScaleBy = CCScaleBy.action(1, 0.5f);
        CCScaleBy reverse = ccScaleBy.reverse();
        CCSequence ccSequence = CCSequence.actions(ccScaleBy, reverse);
        CCRepeatForever forever = CCRepeatForever.action(ccSequence);
        getCcSprite().runAction(forever);

=======
        moveTo();
        moveBy();
        jumpBy();
>>>>>>> origin/master
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
<<<<<<< HEAD
        CCMoveTo ccMoveTo = CCMoveTo.action(5, CCNode.ccp(200, 0));
        //减速度 参数 加速度数值
//        CCEaseOut easeOut = CCEaseOut.action(ccMoveTo, 5);
        //加速度
        CCEaseIn easeIn = CCEaseIn.action(ccMoveTo, 5);
        sprite.runAction(easeIn);
=======
        CCMoveTo ccMoveTo = CCMoveTo.action(2, CCNode.ccp(200, 0));
        sprite.runAction(ccMoveTo);
>>>>>>> origin/master
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
