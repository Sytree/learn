package com.example.huii.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;

import com.example.huii.annotation_hui.SomethingOne;

/**
 * Created by huii on 17/11/19.
 */

public class MyJActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doSomething();
    }

    @SomethingOne
    public void doSomething() {

    }
}
