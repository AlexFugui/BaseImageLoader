package me.alex.baseimageloader.config;

import android.view.View;
import android.widget.ImageView;

import me.alex.baseimageloader.listener.OnLoadListener;
import me.alex.baseimageloader.srtategy.CacheStrategy;

/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/15
 * <p>
 * 页面内容介绍:
 * <p>
 * ================================================
 */
public class Builder {

    public Builder() {

    }

    protected Object url;
    protected View imageView;
    protected int placeholder = BaseImageSetting.getInstance().getPlaceholder();
    protected int errorPic = BaseImageSetting.getInstance().getErrorPic();
    protected boolean isCrossFade = false;
    protected boolean isCenterCrop = false;
    protected boolean isCircle = false;
    protected int topRightRadius = 0;
    protected int topLeftRadius = 0;
    protected int bottomRightRadius = 0;
    protected int bottomLeftRadius = 0;
    protected int radius = 0;
    protected boolean asBitmap;
    protected @CacheStrategy.Strategy
    int cacheStrategy;//0对应DiskCacheStrategy.all,1对应DiskCacheStrategy.NONE,2对应DiskCacheStrategy.SOURCE,3对应DiskCacheStrategy.RESULT
    protected OnLoadListener listener;
    protected boolean clearMemory;
    protected boolean clearDiskCache;

    public Builder url(Object url) {
        this.url = url;
        return this;
    }

    public Builder imageView(View imageView) {
        this.imageView = imageView;
        return this;
    }

    public Builder placeholder(int placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public Builder errorPic(int errorPic) {
        this.errorPic = errorPic;
        return this;
    }

    public Builder crossFade(boolean crossFade) {
        isCrossFade = crossFade;
        return this;
    }

    public Builder centerCrop() {
        isCenterCrop = true;
        return this;
    }

    public Builder isCircle() {
        isCircle = true;
        return this;
    }

    public Builder setTopRightRadius(int topRightRadius) {
        this.topRightRadius = topRightRadius;
        return this;
    }

    public Builder setTopLeftRadius(int topLeftRadius) {
        this.topLeftRadius = topLeftRadius;
        return this;
    }

    public Builder setBottomRightRadius(int bottomRightRadius) {
        this.bottomRightRadius = bottomRightRadius;
        return this;
    }

    public Builder setBottomLeftRadius(int bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
        return this;
    }

    public Builder setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    public OnLoadListener getListener() {
        return listener;
    }

    public Builder setListener(OnLoadListener listener) {
        this.listener = listener;
        return this;
    }

    public boolean isAsBitmap() {
        return asBitmap;
    }

    public Builder setAsBitmap(boolean asBitmap) {
        this.asBitmap = asBitmap;
        return this;
    }


    public Builder cacheStrategy(@CacheStrategy.Strategy int cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
        return this;
    }

    public Builder clearMemory() {
        this.clearMemory = true;
        return this;
    }

    public Builder clearDiskCache() {
        this.clearDiskCache = true;
        return this;
    }

    public BaseImageConfig show() {
        return new BaseImageConfig(this);
    }
}

