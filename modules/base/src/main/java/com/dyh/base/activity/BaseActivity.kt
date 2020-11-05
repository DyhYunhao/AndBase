package com.dyh.base.activity

import android.app.Dialog
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.dyh.base.manager.ActivityTaskManager
import com.dyh.base.manager.ScreenManager
import com.dyh.base.utils.DialogLoadingUtil

/*********************************************
 * @author daiyh
 * 创建日期：2020-10-29
 * 描述：有的Activity可能不需要ViewDataBinding
 *    所以BaseActivity中，只加入了最基本的代码。
 *    BaseViewDataBindingActivity里有ViewDataBinding，ViewModel，
 *   继承于BaseActivity
 *********************************************
 */
abstract class BaseActivity: AppCompatActivity() {
    private var mLoadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityTaskManager.instance.put(this)
        ScreenManager.SCREEN(this)
    }

    @get:LayoutRes
    protected abstract val layoutId: Int

    /** 显示用户等待框  */
    protected fun showLoadingDialog(msg: String? = "") {
        mLoadingDialog = DialogLoadingUtil.createDialog(this, msg)
        mLoadingDialog!!.show()
    }

    /** 隐藏等待框  */
    protected fun dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog!!.isShowing) {
            mLoadingDialog!!.dismiss()
            mLoadingDialog = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityTaskManager.instance.remove(this)
    }
}