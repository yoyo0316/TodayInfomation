package com.yoyozhangh.github.todayinfomation.base.crash;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashProtectManager {

    private static CrashProtectManager mInstance;

    private static Context mContext;

    private CrashProtectManager() {
    }

    public static CrashProtectManager getInstance(Context context) {
        if (mInstance == null) {
            mContext = context.getApplicationContext();
            mInstance = new CrashProtectManager();
        }
        return mInstance;
    }

    public void init() {
        // crash  防护
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                //退出操作
                Log.e("setDefaultUncaughtEx", "是不是发生崩溃了？");

                handleFileExecption(e);

                if (t == Looper.getMainLooper().getThread()) {
                    handleMainThread(e);
                }
            }
        });
    }

    // 日志文件系统
    private void handleFileExecption(Throwable e) {
        //通过 Throwable 生成字符串
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        printWriter.close();
        String result = writer.toString();

        //定义文件名
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD-HH-mm-ss");
        String time = dateFormat.format(new Date());
        String fileName = "crash-" + time + ".txt";
        try {

            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File cacheDir = mContext.getCacheDir();
                if (!cacheDir.exists()) {
                    cacheDir.mkdirs();
                }
                File cacheFile = new File(cacheDir.getAbsolutePath(), fileName);
                if (!cacheFile.exists()) {

                    cacheFile.createNewFile();

                }

                // 把字符串写入到文件
                FileOutputStream fileOutputStream = new FileOutputStream(cacheFile);
                fileOutputStream.write(result.getBytes());
                fileOutputStream.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void handleMainThread(Throwable e) {

        while (true) {
            try {
                Looper.loop();
            } catch (Throwable t) {
                handleFileExecption(t);
                Log.e("setDefaultUncaughtEx", "handleMainThread: 是不是发生崩溃了？" + t);
            }
        }
    }
}
