package com.dyh.messageboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dyh.base.arouter.ConstantUrl;

@Route(path = ConstantUrl.MESSAGEBOARD_ACTIVITY_MAIN)
public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}