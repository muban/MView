package com.muban.base

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * activity基类
 */
@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (setContentViewId() != 0) {
            setContentView(setContentViewId())
        }
        init(savedInstanceState)
    }

    protected abstract fun setContentViewId(): Int

    abstract fun init(savedInstanceState: Bundle?)

}