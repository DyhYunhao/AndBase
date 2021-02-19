package com.dyh.andbase.base;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dyh.base.BaseApplication;
import com.tencent.mmkv.MMKV;

/**
 * describe: 示例框架的application
 * create by daiyh on 2020-12-24
 */
public class AndApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        //打开日志必须写到init之前，否则这些配置在init过程中将无效
//        ARouter.openLog(); //打印日志
        //开启调试模式，线上版本需要关闭，否则有安全风险
//        ARouter.openDebug();

        //官方推荐在Application中初始化
        ARouter.init(AndApplication.this);

        //mmkv初始化
        MMKV.initialize(this);
    }
}
