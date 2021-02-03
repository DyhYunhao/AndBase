package com.dyh.andbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dyh.base.arouter.ConstantUrl

@Route(path = ConstantUrl.APP_ACTIVITY_MAIN)
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        ARouter.getInstance().build(ConstantUrl.APP_ACTIVITY_MAIN).navigation()
        val mBtnMessageBoard: Button = findViewById(R.id.btn_to_message_board)
        mBtnMessageBoard.setOnClickListener {
            ARouter.getInstance().build(ConstantUrl.MESSAGEBOARD_ACTIVITY_MAIN).navigation()
        }
    }
}