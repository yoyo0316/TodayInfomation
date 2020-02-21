package com.yoyozhangh.github.todayinfomation;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.OnClick;


@ViewInject(mainlayoutid = R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @BindView(R.id.fac_main_bottom)
    FloatingActionButton facMainBottom;
    @BindView(R.id.rb_main_shanghai)
    RadioButton rbMainShanghai;
    @BindView(R.id.rb_main_hangzhou)
    RadioButton rbMainHangzhou;
    @BindView(R.id.rg_main_group)
    RadioGroup rgMainGroup;
    @BindView(R.id.fl_main_bottom)
    FrameLayout flMainBottom;
    @BindView(R.id.fl_main_top)
    FrameLayout flMainTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.fac_main_bottom)
    public void onClick() {

    }
}
