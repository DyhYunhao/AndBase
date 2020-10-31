package com.dyh.base.manager

import android.app.Activity
import java.util.concurrent.CopyOnWriteArrayList

/*********************************************
 * @author daiyh
 * 创建日期：2020-10-30
 * 描述：Activity栈管理类，被创建时压栈，销毁时出栈
 *********************************************
 */
class ActivityTaskManager private constructor(){

    private val ACTIVITY_ARRAY = CopyOnWriteArrayList<Activity>()

    companion object {
        //lazy是延迟属性，实现双重校验锁式的单例模式
        val instance: ActivityTaskManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ActivityTaskManager()
        }
    }

    //进栈
    fun put(targetActivity: Activity) {
        //判断栈内是否有对应的activity
        var hasActivity = false
        for (activity in ACTIVITY_ARRAY) {
            if (targetActivity == activity) {
                hasActivity = true
                break
            }
        }
        if (!hasActivity) {
            ACTIVITY_ARRAY.add(targetActivity)
        }
    }

    //出栈
    fun remove(targetActivity: Activity?) {
        ACTIVITY_ARRAY.remove(targetActivity)
    }

    //栈顶的Activity
    val topActivity: Activity?
        get() = if (ACTIVITY_ARRAY.isEmpty()) {
            null
        } else ACTIVITY_ARRAY[0]

    //栈底的Activity
    val lastActivity: Activity?
        get() = if (ACTIVITY_ARRAY.isEmpty()) {
            null
        } else ACTIVITY_ARRAY[ACTIVITY_ARRAY.size - 1]
}