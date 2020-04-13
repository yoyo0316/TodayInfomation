package com.yoyozhangh.github.todayinfomation.main.fragment.beijing;


import android.content.Intent;
import android.view.View;
import android.widget.Button;

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

    @Override
    public void afterBindView() {
        service =new Intent(mContext, MainProcessService.class);
        mContext.startService(service);

        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 去上海
                ProcessDataTest.getInstance().setProcessDec("你好，我来自北京");
                ShanghaiDetailActivity.start_5_0(getActivity(), tvClick);
            }
        });

//        processDataReceiver = new ProcessDataReceiver();
//        getActivity().registerReceiver(processDataReceiver, new IntentFilter("shanghai_get_process_data"));
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
