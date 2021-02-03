package com.dyh.andbase.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dyh.andbase.R;
import com.dyh.base.arouter.ConstantUrl;

@Route(path = ConstantUrl.APP_ACTIVITY_MAIN2)
public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
}