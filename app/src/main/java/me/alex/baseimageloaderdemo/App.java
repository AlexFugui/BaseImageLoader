package me.alex.baseimageloaderdemo;

import android.app.Application;
import android.util.Log;

import me.alex.baseimageloader.config.BaseImageSetting;
import me.alex.baseimageloader.srtategy.CacheStrategy;


/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/2 0002
 * <p>
 * 页面内容介绍:
 * 设置缓存大小,单位为MB 不设置默认为MemoryCache是20mb BitmapPool是30mb DiskCache是250mb
 * setCacheFileName();设置缓存文件夹使用Glide原生api 文档说明: Creates an {@link com.bumptech.glide.disklrucache.DiskLruCache} based disk cache in the internal disk cache directory.
 * setCacheStrategy(); 缓存类型详看{@link com.bumptech.glide.load.engine.DiskCacheStrategy}
 *
 * <p>
 * ================================================
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        BaseImageSetting.getInstance()
                .setMemoryCacheSize(30)
                .setBitmapPoolSize(50)
                .setDiskCacheSize(80)
                .setLogLevel(Log.ERROR)
                .setCacheFileName("BaseImageDemo")
                .setCacheStrategy(CacheStrategy.AUTOMATIC);
    }
}
