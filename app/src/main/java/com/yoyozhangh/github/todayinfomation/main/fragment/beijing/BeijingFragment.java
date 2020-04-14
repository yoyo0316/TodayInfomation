package com.yoyozhangh.github.todayinfomation.main.fragment.beijing;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.base.BaseFragment;
import com.yoyozhangh.github.todayinfomation.base.ViewInject;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.view.ShanghaiDetailActivity;

import butterknife.BindView;

@ViewInject(mainlayoutid = R.layout.fragment_beijing)
public class BeijingFragment extends BaseFragment {

    @BindView(R.id.bt_play)
    Button tvClick;
    //    ProcessDataReceiver processDataReceiver;
    Intent service;

    @BindView(R.id.bt_permision)
    Button tvPermisionClick;

    @Override
    public void afterBindView() {
        service = new Intent(mContext, MainProcessService.class);

        // android 8.0 平台适配
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            mContext.startForegroundService(service);
        }else {
            mContext.startService(service);
        }

        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 去上海
                ProcessDataTest.getInstance().setProcessDec("你好，我来自北京");
                ShanghaiDetailActivity.start_5_0(getActivity(), tvClick);
            }
        });


        tvPermisionClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int state = getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
                    if (state == PackageManager.PERMISSION_GRANTED) {
                        Log.d("BeijingFragment", "权限已经申请");
                    } else {
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        Log.d("BeijingFragment", "权限没有申请");
                    }
                }
            }
        });

//        processDataReceiver = new ProcessDataReceiver();
//        getActivity().registerReceiver(processDataReceiver, new IntentFilter("shanghai_get_process_data"));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("BeijingFragment", "onRequestPermissionsResult: grantResults =" + grantResults[0]);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mContext.stopService(service);
//        getActivity().unregisterReceiver(processDataReceiver);
    }

//    private class ProcessDataReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String processDec = ProcessDataTest.getInstance().getProcessDec();
//            Intent postIntent = new Intent("beijing_post_process_data");
//            postIntent.putExtra("processDec", processDec);
//            getActivity().sendBroadcast(postIntent);
//        }
//    }
}
