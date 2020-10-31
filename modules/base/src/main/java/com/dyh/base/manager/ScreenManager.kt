package com.dyh.base.manager

import android.app.Activity
import android.content.ComponentCallbacks
import android.content.res.Configuration

/*********************************************************************************
 * @author daiyh
 * 创建日期：2020-10-29
 * 描述：基于字节跳动的设备屏幕适配方案
 *   简单来说通过修改density值，强行把所有不同尺寸分辨率的手机的宽度dp值改成一个统一的值
 *   根据当前设备屏幕总宽度（单位为像素）/ 设计图总宽度（单位为 dp) = density
 *   计算出 density (density 的意思就是 1 dp 占当前设备多少像素)
 *   屏幕总宽度就是 屏幕宽与高相比较最小的那个 例如: 屏幕像素为 1920 * 1080 则屏幕总宽度为 1080
 ***********************************************************************************
 */

//将class关键字替换为object关键字，来声明一个类，与此同时也声明它的一个对象。只要编写这么多代码，这个类就已经是单例的了。
//这种单例模式的实现是饿汉式的
object ScreenManager {

    private var sNonCompatDensity = 0f
    private var sNonCompatScaleDensity = 0f

    @JvmStatic
    fun SCREEN(activity: Activity?) {
        if (activity == null) {
            return
        }
        val application = activity.application
        val appDisplayMetrics = application.resources.displayMetrics
        if (sNonCompatDensity == 0f) {
            sNonCompatDensity = appDisplayMetrics.density
            sNonCompatScaleDensity = appDisplayMetrics.scaledDensity
            application.registerComponentCallbacks(object: ComponentCallbacks{
                override fun onLowMemory() {
                }

                override fun onConfigurationChanged(p0: Configuration) {
                    if (p0.fontScale > 0) {
                        sNonCompatScaleDensity = application.resources.displayMetrics.scaledDensity
                    }
                }
            })
            //基于开发切图为360dp进行计算的，可以根据切图标准进行更改
            val targetDensity = appDisplayMetrics.widthPixels / 360f
            val targetScaleDensity = targetDensity * (sNonCompatScaleDensity / sNonCompatDensity)
            val targetDensityDpi = (160 * targetDensity).toInt()
            appDisplayMetrics.density = targetDensity
            appDisplayMetrics.scaledDensity =  targetScaleDensity
            appDisplayMetrics.densityDpi = targetDensityDpi
        }
    }

}