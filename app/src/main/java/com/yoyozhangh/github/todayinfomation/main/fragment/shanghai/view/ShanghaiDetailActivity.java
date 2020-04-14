package com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import com.yoyozhangh.github.ipc.CallBack;
import com.yoyozhangh.github.ipc.IpcManager;
import com.yoyozhangh.github.ipc.request.IpcRequest;
import com.yoyozhangh.github.ipc.result.IResult;
import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.base.BaseActivity;
import com.yoyozhangh.github.todayinfomation.base.ViewInject;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.If.IShanghaiDetailContract;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.dto.ShanghaiDetailBean;
import com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.presenter.ShanghaiDetailPresenter;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@ViewInject(mainlayoutid = R.layout.activity_shanghai_detail)
public class ShanghaiDetailActivity extends BaseActivity implements IShanghaiDetailContract.Iview {

    IShanghaiDetailContract.IPresenter mPresenter = new ShanghaiDetailPresenter(this);


    @BindView(R.id.iv_shanghai_detail)
    ImageView ivShanghaiDetail;

    public static String mActivityOptionsCompat = "ShanghaiDetailActivity";
    @BindView(R.id.tv_crash)
    TextView tvCrash;
    @BindView(R.id.GLSurfaceView)
    android.opengl.GLSurfaceView glSurfaceView;
//    GetProcessReceiver getProcessReceiver;
//
//    private Messenger messenger;
//
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Bundle data = msg.getData();
//            String processDec = data.getString("processDec");
//            Log.e("ShanghaiDetailActivity", "handleMessage: processDec=" + processDec);
//        }
//    };
//    private Messenger messengerClient = new Messenger(handler);

//    private ServiceConnection mConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            messenger = new Messenger(service);
//            Message message = new Message();
//            message.what = MainProcessService.SHANGHAIDETAIL;
//            message.replyTo = messengerClient;
//            try {
//                messenger.send(message);
//            } catch (RemoteException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//
//        }
//    };

    @Override
    public void afterBindView() {

//        glSurfaceView.setRenderer(new GLSurfaceView.Renderer() {
//            @Override
//            public void onSurfaceCreated(GL10 gl, EGLConfig config) {
//                //都是子线程
//            }
//
//            @Override
//            public void onSurfaceChanged(GL10 gl, int width, int height) {
//
//            }
//
//            @Override
//            public void onDrawFrame(GL10 gl) {
//                // 循环调用 进行渲染
////                gl.glClearColor();
//            }
//        });

        initAnima();
//        initReceiver();
//        initProcessData();
        initGetNetData();
//        initPostNetData();
//        initProviderData();

//        initProcessService();

        initIPC();
//        ivShanghaiDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String s = null;
//                s.toString();
//            }
//        });
//
//        tvCrash.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String s = null;
//                        s.toString();
//                    }
//                }).start();
//            }
//        });
    }

    private void initIPC() {
        IpcRequest request = new IpcRequest("shanghaiDetail");
        IpcManager.getInstance(this).excuteAsync(request, new CallBack() {
            @Override
            public void callBack(IResult result) {
                String data = result.data();
                Log.d("yyz", "数据请求 initIPC : data=" + data);
            }
        });
//        IResult result = IpcManager.getInstance(this).excuteSync(request);
//        Log.e("yyz", "数据请求 initIPC: data=" + result.data());
    }

//    private void initProcessService() {
//        Intent intent = new Intent(this, MainProcessService.class);
//        bindService(intent, mConnection, Service.BIND_AUTO_CREATE);
//    }

//    private void initProviderData() {
//        Uri insert = getContentResolver().insert(Uri.parse("content://com.yoyozhangh.github.todayinfomation.process.data"), new ContentValues());
//        Log.e("ShanghaiDetailActivity", "initProviderData:processDec " + insert.toString());
//    }

//    private void initReceiver() {
//        getProcessReceiver = new GetProcessReceiver();
//
//        registerReceiver(getProcessReceiver, new IntentFilter("beijing_post_process_data"));
//    }

    private void initProcessData() {
//        String processDec = ProcessDataTest.getInstance().getProcessDec();
//        Log.e("ShanghaiDetailActivity", "initProcessData: processDec=" + processDec);
//        Intent intent = new Intent("shanghai_get_process_data");
//        sendBroadcast(intent);

////         应用内广播
//        LocalBroadcastManager.getInstance().registerReceiver();
//        LocalBroadcastManager.getInstance().unregisterReceiver();
    }

    private void initPostNetData() {
        OkHttpClient client = new OkHttpClient();// okhttp 配置一些默认

        FormBody.Builder builder = new FormBody.Builder();

        // 创建之后  上传文件
//        RequestBody mediaType = MultipartBody.create();
        builder.add("key", "d2d1bf028b2e0a775442ad39b12e9b13");

        Request request = new Request.Builder()
                .url("http://apis.juhe.cn/lottery/types")
                .post(builder.build())
                .build();// 建造者设计模式
        Call call = client.newCall(request);

//        //同步请求
//        try {
//            Response response = call.execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("initPostNetData", "onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("initPostNetData", "onResponse: " + response.body().string());
            }
        });
    }

    /**
     * 发送网络请求数据
     */
    private void initGetNetData() {

        ivShanghaiDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getNetData(1);
            }
        });


//        GetXiaohuaTask task = new GetXiaohuaTask();
//        task.execute("desc", "1", "2");

//        Object desc = new ShanghaiDetailHttpTask().getXiaohuaList("desc", "1", "2");
//        if (desc instanceof Response){
//            Response response = (Response) desc;
//            Log.d("initGetNetData :", response.body().toString());
//        }


//        //1, 可以隔离
//        OkHttpClient client = new OkHttpClient();// okhttp 配置一些默认
//
//        //2,构建请求 (1)url (2) 参数
//        HttpUrl.Builder builder = HttpUrl.parse("https://www.baidu.com").newBuilder();
////        builder.addQueryParameter("sort", "desc");
////        builder.addQueryParameter("page", "1");
////        builder.addQueryParameter("pagesize", "2");
////        builder.addQueryParameter("time", "" + System.currentTimeMillis() / 1000);
////        builder.addQueryParameter("key", "d56906ce69cedeb9ba50f39509315db4");
//
//        String requestUrl = builder.toString();
//        Log.e("initGetNetData", "requestUrl: " + requestUrl);
//
//        //3,构建 request
//        Request request = new Request.Builder()
//                .url(builder.build())
//                .get()
//                .build();// 建造者设计模式
//
//        //4,构建Call
//        Call call = client.newCall(request);
//
////        //同步请求
////        try {
////            Response response = call.execute();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//
//        // 5,执行网络请求
//        //异步请求
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.e("initGetNetData", "onFailure: " + e);
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Log.e("initGetNetData", "onResponse: " + response.body().string());
//            }
//        });


    }

    private void initAnima() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            ViewCompat.setTransitionName(ivShanghaiDetail, mActivityOptionsCompat);

//            // 延时加载
//            postponeEnterTransition();
            // 开启转场动画
            startPostponedEnterTransition();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 用于Android5.0系统的界面转场动画：共享元素动画
     */
    public static void start_5_0(Activity activity, View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(activity, ShanghaiDetailActivity.class);
            Pair pair = new Pair(view, mActivityOptionsCompat);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pair);
            ActivityCompat.startActivity(activity, intent, optionsCompat.toBundle());
        }

    }

    @Override
    public void showData(ShanghaiDetailBean detailBean) {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(getProcessReceiver);
    }

//    private class GetProcessReceiver extends BroadcastReceiver {
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String processDec = intent.getStringExtra("processDec");
//            Log.e("GetProcessReceiver", "onReceive: processDec=" + processDec);
//        }
//    }
}
