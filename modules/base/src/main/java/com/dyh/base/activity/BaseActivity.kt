package com.dyh.base.activity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity

/*********************************************
 * @author daiyh
 * 创建日期：2020-10-29
 * 描述：有的Activity可能不需要ViewDataBinding
 *    所以BaseActivity中，只加入了最基本的代码。
 *    BaseViewDataBindingActivity里有ViewDataBinding，ViewModel，
 *   继承于BaseActivity
 *********************************************
 */
class BaseActivity: AppCompatActivity() {
    private var mLoadingDialog: Dialog? = null
}