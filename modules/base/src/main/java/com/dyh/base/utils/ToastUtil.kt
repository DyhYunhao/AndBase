package com.dyh.base.utils

import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.StringRes

/*********************************************
 * @author daiyh
 * 创建日期：2020-11-5
 * 描述： Toast统一封装类
 *********************************************
 */
object ToastUtil {
    private var application: Context? = null

    @JvmStatic
    fun setApplicationContext(application: Application?) {
        ToastUtil.application = application
    }

    @JvmStatic
    fun toast(message: String) {
        toast(message, Toast.LENGTH_SHORT)
    }

    @JvmOverloads
    @JvmStatic
    fun toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
        if (application == null) {
            throw KotlinNullPointerException("Application不能为空，请在Application中初始化ToastUtil，在ToastUtil的setApplicationContext方法中，传入Application")
        }
        val content = application!!.getString(resId)
        toast(content, duration)
    }

    @JvmStatic
    fun toast(content: String?, duration: Int) {
        if (application == null) {
            throw KotlinNullPointerException("Application不能为空，请在Application中初始化ToastUtil，在ToastUtil的setApplicationContext方法中，传入Application")
        }
        if (TextUtils.isEmpty(content)) {
            return
        }
        Handler(Looper.getMainLooper()).postDelayed(
            Runnable {
                val toast = Toast.makeText(application, content, duration)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }, 0)
    }
}