package com.dyh.base

import android.app.Application

/******************************************************************************************
 * @author daiyh
 * 创建日期：2020-10-29
 * 描述：自定义封装Application基类,基础库的application
 *      所有的模块都会provided基础库，那么所有模块都可以引用基础库中的Application基类了。
 *      App的Application继承基础库中的Application即可，这么简单就能实现Appliction的全局引用
 *      简单来说只要各个业务组件和app壳工程中声明的Application类继承了 BaseApplication，
 *      当应用启动时 BaseApplication 就会被动实例化，这样从 BaseApplication 获取的 Context 就会生效，
 *      也就从根本上解决了我们不能直接从各个组件获取全局 Context 的问题
 ******************************************************************************************
 */
class BaseApplication: Application() {
    companion object {
        //kotlin静态变量，底层实现与java的静态变量一致
        @JvmField
        val mInstance: Application? = null

        //kotlin静态方法
        @JvmStatic
        fun getInstance(): BaseApplication {
            return mInstance as BaseApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
    }

}