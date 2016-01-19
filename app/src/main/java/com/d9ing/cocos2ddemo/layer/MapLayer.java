package com.d9ing.cocos2ddemo.layer;

import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCTMXObjectGroup;
import org.cocos2d.layers.CCTMXTiledMap;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.types.CGPoint;

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

    public MapLayer() {
        init();
    }

    /**
     * 初始化方法
     */
    private void init() {
        //加载地图
        loadMap();
        parserMap();
        loadZombies();
    }

    /**
     * 加载僵尸元素
     */
    private void loadZombies() {
        CCSprite sprite = CCSprite.sprite("z_1_01.png");
        //设置锚点在两腿之间
        sprite.setAnchorPoint(0.5f,0);
        sprite.setPosition(roadPoints.get(position));
        //设置缩放
        sprite.setScale(0.5f);
        //设置翻转
        sprite.setFlipX(true);
        this.addChild(sprite);
        //创建帧集合
        ArrayList<CCSpriteFrame> frames = new ArrayList<CCSpriteFrame>();
        //格式化文件名
        String format = "z_1_%02d.png";//02d 占位符 表示两位的数字 如果没有两位 前面补0
        //创建帧
        for (int i = 1; i <=7 ; i++) {
        CCSpriteFrame displayedFrame = CCSprite.sprite(String.format(format,i)).displayedFrame();
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
    }

    /**
     * 加载地图方法
     */
    private void loadMap() {
        //加载地图
        map = CCTMXTiledMap.tiledMap("map.tmx");
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
            CGPoint cgPoint = ccp(x,y);
            roadPoints.add(cgPoint);
        }
    }

}
