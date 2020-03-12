package com.yoyozhangh.github.http.okhttp;

import com.yoyozhangh.github.http.HttpScheduler;
import com.yoyozhangh.github.http.annotation.RequestMethod;
import com.yoyozhangh.github.http.https.Https;
import com.yoyozhangh.github.http.request.IRequest;
import com.yoyozhangh.github.http.request.call.ICall;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Dns;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpHttpScheduler extends HttpScheduler {
    private OkHttpClient client;

    @Override
    public ICall newCall(IRequest request) {
        Map<String, Object> params = request.getParams();
        int requestMethod = request.getRequestMethod();
        Request.Builder requestBuilder = new Request.Builder();
        //OKhttp 添加请求头， 不支持 null ，\n 和中文这样的特殊字符
//        requestBuilder.addHeader()
        Request okHttpRequest = null;
        switch (requestMethod) {
            case RequestMethod.Get:
                // 拼接Getq请求的Url Host + path
                StringBuilder urlStrBuilder = new StringBuilder(request.getHost().getHost());
                urlStrBuilder.append(request.getPath());
                HttpUrl.Builder urlBuilder = HttpUrl.parse(urlStrBuilder.toString()).newBuilder();


                if (params != null && params.size() > 0) {
                    Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, Object> next = iterator.next();

                        // TODO: 2020/3/9/009  这里涉及对象如何转成 String 字符串
                        urlBuilder.addQueryParameter(next.getKey(), String.valueOf(next.getValue()));
                    }
                }

                okHttpRequest = requestBuilder.get().url(urlBuilder.build()).build();

                break;

            case RequestMethod.Post:
                // TODO: 2020/3/9/009  
                break;
        }

        Call call = getClient().newCall(okHttpRequest);
        OkHttpCall okHttpCall = new OkHttpCall(request, call);
        return okHttpCall;
    }

    private OkHttpClient getClient() {
        if (client == null) {
//            client = new OkHttpClient();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(Https.getSSLSocketFactory());
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });


            // 拦截器 添加公共参数 添加日志拦截器，添加
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    String method = request.method();
//                    if (get){
//                        request.
//                    }

//                    if (post){
//                        RequestBody body = request.body();
//                        if (body instanceof FormBody){
//                            FormBody formBody = (FormBody) body;
//
//                        }
//
//                    }
                    Response response = chain.proceed(request);
//                    response.body().string()
//                    if (response == null || response.body().string() == null){
//
//                    }
                    return response;
                }
            });

//OKHTTP之缓存配置详解
//https://www.jianshu.com/p/647dfd41194e?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
            // 配置网络缓存
//            builder.cache(new Cache())
            client = builder.build();

//            // dns 解析
//            builder.dns(new Dns() {
//                @Override
//                public List<InetAddress> lookup(String hostname) throws UnknownHostException {
//                   String host = "www.juhe.com";
//                   List<String> iplist = Dns.getIds(host);
//                    for (int i = 0; i < iplist.size(); i++) {
//                        String ip = iplist.get(i);
//                        InetAddress byname = InetAddress.getByName(ip);
//                    }
//                    return null;
//                }
//            });
        }
        return client;
    }
}
