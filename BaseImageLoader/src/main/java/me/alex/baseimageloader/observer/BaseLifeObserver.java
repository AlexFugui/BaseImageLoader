package me.alex.baseimageloader.observer;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/10
 * <p>
 * 页面内容介绍:
 * <p>
 * ================================================
 */
public class BaseLifeObserver implements LifecycleObserver {

    private Context context;

    public BaseLifeObserver(Context context) {
        this.context = context;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        showLog("onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        showLog("onDestroy");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
        showLog("onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop() {
        showLog("onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume() {
        showLog("onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause() {
        showLog("onPause");
    }

    private void showLog(String msg) {
        Log.e("===BaseLifeObserver", msg);
    }
}
