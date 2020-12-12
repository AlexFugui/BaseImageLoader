package me.alex.baseimageloader.module;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

import me.alex.baseimageloader.config.BaseImageSetting;


/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/2 0002
 * <p>
 * 页面内容介绍: 生成GlideAlex的自定义Module
 * <p>
 * ================================================
 */
@GlideModule(glideName = "GlideAlex")
public class MyAppGlideModule extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        builder.setLogLevel(Log.ERROR);

        //内存缓存 20mb
        int memoryCacheSizeBytes = 1024 * 1024 * BaseImageSetting.getInstance().getMemoryCacheSize(); // 默认20mb
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes));

        //bitmap池缓存 30mb
        int bitmapPoolSizeBytes = 1024 * 1024 * BaseImageSetting.getInstance().getBitmapPoolSize(); // 默认30mb
        builder.setBitmapPool(new LruBitmapPool(bitmapPoolSizeBytes));

        //磁盘缓存250mb
        int diskCacheSizeBytes = 1024 * 1024 * BaseImageSetting.getInstance().getDiskCacheSize(); //默认缓存文件夹名 GlideAlexCache 缓存大小默认150mb
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, BaseImageSetting.getInstance().getCacheFileName(), diskCacheSizeBytes));

//        builder.setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_RGB_565));
//        builder.setDefaultRequestOptions(
//                new RequestOptions()
//                        .format(DecodeFormat.PREFER_RGB_565)
//                        .disallowHardwareConfig()
//                        .disallowHardwareBitmaps());
    }
}