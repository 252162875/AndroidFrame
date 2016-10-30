package xy.com.mysoul.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * 自定义的Application 1、运行的过程中只有一个实例 2、一个应用程序最先执行的方法 运行在主线程中
 * 注意：在AndroidManifest文件中进行注册
 */
public class BaseApplication extends Application {


    private static Context context;
    private static Handler handler;
    private static int mainThreadId;
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化context对象，context对象使用的非常多
        context = getApplicationContext();
        // 获取主线程的handler 相当于获取主线程的消息队列
        handler = new Handler();
        // 获取主线程的id 哪一个方法调用了myTid myTid就返回那个方法所在线程的id
        mainThreadId = android.os.Process.myTid();
    }

    public static Context getContext() {
        return context;
    }

    public static Handler getHandler() {
        return handler;
    }

    public static int getMainThreadId() {
        return mainThreadId;
    }
}