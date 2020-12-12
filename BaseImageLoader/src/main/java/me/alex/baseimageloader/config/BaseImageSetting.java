package me.alex.baseimageloader.config;


import android.util.Log;

import me.alex.baseimageloader.srtategy.CacheStrategy;

/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/5
 * <p>
 * 页面内容介绍: BaseImage通用设置
 * <p>
 * ================================================
 */
public class BaseImageSetting {
    private static BaseImageSetting baseImageSetting = new BaseImageSetting();

    /**
     * 默认内存缓存大小 20mb
     */
    private int memoryCacheSize = 20;
    /**
     * 默认bitmap池缓存大小 30mb
     */
    private int bitmapPoolSize = 30;
    /**
     * 默认硬盘缓存大小250mb
     */
    private int diskCacheSize = 250;
    /**
     * 默认的缓存文件夹名称
     */
    private String cacheFileName = "GlideAlexCache";
    /**
     * 通用缓存类型设置
     */
    private @CacheStrategy.Strategy
    int cacheStrategy = CacheStrategy.AUTOMATIC;

    /**
     * log级别设置,默认为ERROR级别
     */
    private int logLevel = Log.ERROR;


    public static BaseImageSetting getInstance() {
        return baseImageSetting;
    }

    public int getMemoryCacheSize() {
        return memoryCacheSize;
    }

    /**
     * 设置内存缓存大小 默认20mb
     *
     * @param memoryCacheSize 单位mb
     * @return 返回SImageConfig类可以连续设置
     */
    public BaseImageSetting setMemoryCacheSize(int memoryCacheSize) {
        this.memoryCacheSize = memoryCacheSize;
        return this;
    }

    public int getBitmapPoolSize() {
        return bitmapPoolSize;
    }

    /**
     * 设置bitMap池缓存大小 默认30mb
     *
     * @param bitmapPoolSize 单位mb
     * @return 返回SImageConfig类可以连续设置
     */
    public BaseImageSetting setBitmapPoolSize(int bitmapPoolSize) {
        this.bitmapPoolSize = bitmapPoolSize;
        return this;
    }

    public int getDiskCacheSize() {
        return diskCacheSize;
    }

    /**
     * 设置硬盘缓存大小 默认250mb
     *
     * @param diskCacheSize 单位mb
     * @return 返回SImageConfig类可以连续设置
     */
    public BaseImageSetting setDiskCacheSize(int diskCacheSize) {
        this.diskCacheSize = diskCacheSize;
        return this;
    }

    public String getCacheFileName() {
        return cacheFileName;
    }

    public BaseImageSetting setCacheFileName(String cacheFileName) {
        this.cacheFileName = cacheFileName;
        return this;
    }

    public void setCacheStrategy(@CacheStrategy.Strategy int cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
    }

    public @CacheStrategy.Strategy
    int getCacheStrategy() {
        return cacheStrategy;
    }

    public int getLogLevel() {
        return logLevel;
    }

    /**
     * 如果你拥有设备的访问权限，你可以使用 adb logcat 或你的 IDE 查看一些日志。
     * 你可以使用 adb shell setprop log.tag.<tag_name> <VERBOSE|DEBUG> 操作为任何下面提到的标签(tag))开启日志。
     * VERBOSE 级别的日志会显得更加冗余但包含更多有用的信息。根据你要查看的标签的不同，你可以把 VERBOSE 和 DEBUG 级别的信息都尝试一下，以决定哪个级别的信息是你最需要的。
     *
     * @param logLevel 详见 {@link Log} 中Log等级
     */
    public BaseImageSetting setLogLevel(int logLevel) {
        this.logLevel = logLevel;
        return this;
    }
}
