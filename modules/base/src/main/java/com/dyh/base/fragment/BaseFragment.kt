package com.dyh.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

/*********************************************
 * @author daiyh
 * 创建日期：2020-11-5
 * 描述： 自定义封装的fragment，带有viewModel和viewDataBinding
 *********************************************
 */
abstract class BaseFragment<T : ViewModel, M : ViewDataBinding> : Fragment() {
    lateinit var mViewModel: T
    lateinit var mViewDataBinding: M

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return mViewDataBinding.root
    }

    abstract fun initView()

    abstract fun initData()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
        initData()
    }

    private fun initViewModel() {
        //        val types = (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
//        mViewModel = ViewModelProvider(this).get(types[0] as Class<T>)

        val clazz =
            this.javaClass.kotlin.supertypes[0].arguments[0].type!!.classifier!! as KClass<T>
        mViewModel = getViewModel<T>(clazz) //koin 注入
    }
}