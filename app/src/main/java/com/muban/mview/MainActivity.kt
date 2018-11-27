package com.muban.mview

import android.os.Bundle
import android.widget.FrameLayout
import com.muban.base.BaseActivity
import com.muban.view.DrawXXX
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun setContentViewId(): Int = R.layout.activity_main

    override fun init(savedInstanceState: Bundle?) {
        fl_home_root.addView(
            DrawXXX(this),
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        )
    }
}
