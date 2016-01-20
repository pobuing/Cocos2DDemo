package com.d9ing.cocos2ddemo.layer;

import android.view.MotionEvent;

import com.d9ing.cocos2ddemo.R;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCRotateBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCTMXObjectGroup;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.particlesystem.CCParticleSnow;
import org.cocos2d.particlesystem.CCParticleSystem;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.util.CGPointUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 地图Layer
 * Created by wx on 2016/1/19.
 */
public class MapLayer extends CCLayer {
    private CCTMXTiledMap map;
    private ArrayList<CGPoint> roadPoints;
    //当前位置
    private int position = 0;
    private CCSprite sprite;
    private CCParticleSystem particle;

    public MapLayer() {
        init();
        //开启点击事件开关
        this.setIsTouchEnabled(true);
    }

    /**
     * 初始化方法
     */
    private void init() {
        //加载地图
        loadMap();
        loadParticle();
        parserMap();
        loadZombies();
    }

    /**
     * 加载粒子系统——飘雪场景
     */
    private void loadParticle() {
        //创建粒子系统 飘雪
        particle = CCParticleSnow.node();
        //设置雪花样式
        particle.setTexture(CCTextureCache.sharedTextureCache().addImage("f.png"));
        //添加到图层中
        this.addChild(particle, 1);
    }

    /**
     * 加载僵尸元素
     */
    private void loadZombies() {
        sprite = CCSprite.sprite("z_1_01.png");
        //设置锚点在两腿之间
        sprite.setAnchorPoint(0.5f, 0);
        sprite.setPosition(roadPoints.get(position));
        //设置缩放
        sprite.setScale(0.5f);
        //设置翻转
        sprite.setFlipX(true);
        //将僵尸添加到地图上，可以随着触摸移动，不要添加到图层上
        map.addChild(sprite);
        //创建帧集合
        ArrayList<CCSpriteFrame> frames = new ArrayList<CCSpriteFrame>();
        //格式化文件名
        String format = "z_1_%02d.png";//02d 占位符 表示两位的数字 如果没有两位 前面补0
        //创建帧
        for (int i = 1; i <= 7; i++) {
            CCSpriteFrame displayedFrame = CCSprite.sprite(String.format(format, i)).displayedFrame();
            frames.add(displayedFrame);
        }
        //配置序列帧的信息
        //参数1 动作名字 参数2 每一帧播放的时间 参数3 所有用到的帧
        CCAnimation animation = CCAnimation.animation("走路", 0.2f, frames);
        //播放序列帧
        CCAnimate ccAnimate = CCAnimate.action(animation);
        //TODO 注意播放序列帧 应该是默认的永不停止的循环
        CCRepeatForever forever = CCRepeatForever.action(ccAnimate);
        sprite.runAction(forever);
        //移动到下一个点
        moveToNext();
    }

    /**
     * 移动到下一个的位置点
     */
    //僵尸移动的速度
    int speed = 40;

    public void moveToNext() {
        position++;
        if (position < roadPoints.size()) {
            CGPoint cgPoint = roadPoints.get(position);
            //计算需要移动的距离
            float time = CGPointUtil.distance(roadPoints.get(position - 1), cgPoint) / speed;
            CCMoveTo ccMoveTo = CCMoveTo.action(time, cgPoint);
            //调用一个对象的某一个方法，由于实现是反射所以要求调用的方法必须是公有的
            CCSequence sequence = CCSequence.actions(ccMoveTo, CCCallFunc.action(this, "moveToNext"));
            sprite.runAction(sequence);
        } else {
            //移动完成了
            particle.stopSystem();
            sprite.stopAllActions();
            //跳舞
            dance();
            SoundEngine engine = SoundEngine.sharedEngine();
            //1.上下文参数必须传递Activity 2.资源ID 3.循环标识
            engine.playSound(CCDirector.theApp, R.raw.psy,true);
        }

    }

    /**
     * 复杂动作——跳舞
     */
    private void dance() {
        //更改锚点
        sprite.setAnchorPoint(0.5f,0.5f);
        CCJumpBy ccJumpBy = CCJumpBy.action(2, ccp(-20, 10), 10, 2);
        CCRotateBy by = CCRotateBy.action(1, 360);
        //并行动作
        CCSpawn ccSpawn = CCSpawn.actions(ccJumpBy, by);
        ccSpawn.reverse();
        CCSequence sequence = CCSequence.actions(ccSpawn, ccSpawn.reverse());
        CCRepeatForever forever = CCRepeatForever.action(sequence);
        sprite.runAction(forever);
    }


    /**
     * @param event
     * @return
     */
    @Override
    public boolean ccTouchesMoved(MotionEvent event) {
        //地图随着手指移动
        map.touchMove(event, map);
        return super.ccTouchesMoved(event);
    }

    /**
     * 加载地图方法
     */
    private void loadMap() {
        //加载地图
        map = CCTMXTiledMap.tiledMap("map.tmx");
//      地图移动必须锚点在中间位置
        map.setAnchorPoint(0.5f, 0.5f);
        //由于修改了锚点所以需要修改坐标
        map.setPosition(map.getContentSize().width / 2, map.getContentSize().height / 2);
        this.addChild(map);
    }

    /**
     * 解析地图中的路径点
     */
    private void parserMap() {
        roadPoints = new ArrayList<CGPoint>();
        CCTMXObjectGroup objectGroupNamed = map.objectGroupNamed("road");
        ArrayList<HashMap<String, String>> objects = objectGroupNamed.objects;
        for (HashMap<String, String> hashMap : objects) {
            int x = Integer.valueOf(hashMap.get("x"));
            int y = Integer.valueOf(hashMap.get("y"));
            CGPoint cgPoint = ccp(x, y);
            roadPoints.add(cgPoint);
        }
    }
}
