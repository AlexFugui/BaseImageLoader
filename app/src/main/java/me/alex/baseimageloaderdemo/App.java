package me.alex.baseimageloaderdemo;

import android.app.Application;
import android.util.Log;

import com.kongzue.baseframework.BaseApp;

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
public class App extends BaseApp<App> {
    @Override
    public void init() {
        BaseImageSetting.getInstance()
                .setMemoryCacheSize(30)//设置内存缓存大小 单位mb
                .setBitmapPoolSize(50)//设置bitmap池缓存大小 单位mb
                .setDiskCacheSize(80)//设置储存储存缓存大小 单位mb
                .setLogLevel(Log.ERROR)//设置log等级
                .setPlaceholder(R.drawable.ic_baseline_adb_24)//设置通用占位图片,全项目生效
                .setErrorPic(R.mipmap.ic_launcher)//设置通用加载错误图片,全项目生效
                .setCacheFileName("BaseImageLoaderDemo")//设置储存缓存文件夹名称,api基于Glide v4
                .setCacheStrategy(CacheStrategy.AUTOMATIC)//设置缓存策略
                .setCacheSize(50)//设置自动加载图片缓存数量,默认50
        ;

    }
}
