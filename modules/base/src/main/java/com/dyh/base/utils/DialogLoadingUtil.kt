package com.dyh.base.utils

import android.app.Dialog
import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import com.dyh.base.R

/*********************************************
 * @author daiyh
 * 创建日期：2020-11-5
 * 描述： 加载loading弹窗的统一样式工具类
 *********************************************
 */
object DialogLoadingUtil {
    @JvmStatic
    fun createDialog(context: Context, msg: String? = ""): Dialog {
        val inflater = LayoutInflater.from(context)
        val loadingView = inflater.inflate(R.layout.base_dialog_loading, null)
        //加载布局
        val llLoadngContent = loadingView.findViewById<LinearLayout>(R.id.ll_dialog_loading)
        //提示文字
        val tvShowMsg = loadingView.findViewById<TextView>(R.id.tv_loading)
        if (!TextUtils.isEmpty(msg)) {
            tvShowMsg.text = msg
        }
        //创建dialog样式
        val loadingDialog = Dialog(context, R.style.LoadingDialog)
        //是否可以按返回键消失
        loadingDialog.setCancelable(true)
        //点击加载框之外的区域
        loadingDialog.setCanceledOnTouchOutside(false)
        //设置布局
        loadingDialog.setContentView(
            llLoadngContent, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
            )
        )
        // 将显示Dialog的方法封装在这里面
        val window = loadingDialog.window
        val lp: WindowManager.LayoutParams
        if (window != null) {
            lp = window.attributes
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT
            window.setGravity(Gravity.CENTER)
            window.attributes = lp
        }
        loadingDialog.show()
        return loadingDialog
    }

    //关闭dialog
    @JvmStatic
    fun closeDialog(dialog: Dialog) {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }
}