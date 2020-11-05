package com.dyh.base.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.databinding.ViewDataBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

/*********************************************
 * @author daiyh
 * 创建日期：2020-11-5
 * 描述：带有ViewDataBinding与ViewModel的BaseActivity
 *********************************************
 */
abstract class BaseViewDataBindingActivity<T : ViewModel, M : ViewDataBinding> : BaseActivity() {
    lateinit var mViewModel: T
    lateinit var mViewDataBinding: M

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewDataBinding = DataBindingUtil.setContentView(this, layoutId)

        initViewModel()
        initDate()
        initView()
    }

    abstract fun initDate()

    abstract fun initView()

    @SuppressLint("NewApi")
    private fun initViewModel() {

//        val types = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
//        mViewModel = ViewModelProvider(this).get<T>(types[0] as Class<T>)

        val clazz =
            this.javaClass.kotlin.supertypes[0].arguments[0].type!!.classifier!! as KClass<T>
        mViewModel = getViewModel<T>(clazz) //koin 注入
    }
}