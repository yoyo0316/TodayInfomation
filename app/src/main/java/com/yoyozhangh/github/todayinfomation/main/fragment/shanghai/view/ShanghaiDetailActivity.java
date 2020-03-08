package com.yoyozhangh.github.todayinfomation.main.fragment.shanghai.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;

import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.base.BaseActivity;
import com.yoyozhangh.github.todayinfomation.base.ViewInject;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@ViewInject(mainlayoutid = R.layout.activity_shanghai_detail)
public class ShanghaiDetailActivity extends BaseActivity {

    @BindView(R.id.iv_shanghai_detail)
    ImageView ivShanghaiDetail;

    public static String mActivityOptionsCompat = "ShanghaiDetailActivity";

    @Override
    public void afterBindView() {
        initAnima();

        initGetNetData();
        initPostNetData();
    }

    private void initPostNetData() {
        OkHttpClient client = new OkHttpClient();// okhttp 配置一些默认

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("key","d2d1bf028b2e0a775442ad39b12e9b13");

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
        OkHttpClient client = new OkHttpClient();// okhttp 配置一些默认

        HttpUrl.Builder builder = HttpUrl.parse("http://v.juhe.cn/joke/content/list.php").newBuilder();
        builder.addQueryParameter("sort", "desc");
        builder.addQueryParameter("page", "1");
        builder.addQueryParameter("pagesize", "2");
        builder.addQueryParameter("time", "" + System.currentTimeMillis() / 1000);
        builder.addQueryParameter("key", "d56906ce69cedeb9ba50f39509315db4");

        String requestUrl = builder.toString();
        Log.e("initGetNetData", "requestUrl: " + requestUrl);
        Request request = new Request.Builder()
                .url(builder.build())
                .get()
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
                Log.e("initGetNetData", "onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("initGetNetData", "onResponse: " + response.body().string());
            }
        });
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
}
