package me.alex.baseimageloader.config;

import android.widget.ImageView;

import me.alex.baseimageloader.listener.OnLoadListener;
import me.alex.baseimageloader.srtategy.BaseImageLoaderStrategy;
import me.alex.baseimageloader.srtategy.CacheStrategy;


/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/4 0004
 * <p>
 * 页面内容介绍:
 * 这里是图片加载配置信息的基类,定义一些所有图片加载框架都可以用的通用参数
 * 每个 {@link BaseImageLoaderStrategy} 应该对应一个 {@link BaseImageConfig} 实现类
 * <p>
 * ================================================
 */
public class BaseImageConfig {
    /**
     * 网络图片url
     */
    protected Object url;

    /**
     * 承载的imageView
     */
    protected ImageView imageView;

    /**
     * 加载网络图片之前占位图片
     */
    protected int placeholder;

    /**
     * 错误占位图片
     */
    protected int errorPic;

    /**
     * 是否使用淡入淡出过渡动画
     */
    protected boolean isCrossFade;

    /**
     * 是否将图片剪切为 CenterCrop
     */
    protected boolean isCenterCrop;

    /**
     * 是否将图片剪切为圆形
     */
    protected boolean isCircle;

    /**
     * 左上 圆角
     */
    protected float topRightRadius;

    /**
     * 右上 圆角
     */
    protected float topLeftRadius;

    /**
     * 左下 圆角
     */
    protected float bottomRightRadius;

    /**
     * 右下 圆角
     */
    protected float bottomLeftRadius;

    /**
     * 通用圆角
     */
    protected float radius;

    /**
     * 是否以bitmap加载
     */
    protected boolean asBitmap;

    /**
     * 缓存类型 默认为通用配置中的模式
     */
    private @CacheStrategy.Strategy
    int cacheStrategy = BaseImageSetting.getInstance().getCacheStrategy();

    /**
     * 加载监听
     */
    protected OnLoadListener listener;

    /**
     * 是否清除内存中缓存
     */
    protected boolean clearMemory;

    /**
     * 是否清除储存中缓存
     */
    protected boolean clearDiskCache;

    public BaseImageConfig() {

    }

    public BaseImageConfig(Builder builder) {
        this.url = builder.url;
        this.imageView = builder.imageView;
        this.placeholder = builder.placeholder;
        this.errorPic = builder.errorPic;
        this.isCrossFade = builder.isCrossFade;
        this.isCenterCrop = builder.isCenterCrop;
        this.isCircle = builder.isCircle;
        this.topRightRadius = builder.topRightRadius;
        this.topLeftRadius = builder.topLeftRadius;
        this.bottomRightRadius = builder.bottomRightRadius;
        this.bottomLeftRadius = builder.bottomLeftRadius;
        this.radius = builder.radius;
        this.asBitmap = builder.asBitmap;
        this.listener = builder.listener;
        this.clearMemory = builder.clearMemory;
        this.clearDiskCache = builder.clearDiskCache;
    }


    public Object getUrl() {
        return url;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public int getErrorPic() {
        return errorPic;
    }

    public boolean isCrossFade() {
        return isCrossFade;
    }

    public boolean isCenterCrop() {
        return isCenterCrop;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public float getTopRightRadius() {
        return topRightRadius;
    }

    public float getTopLeftRadius() {
        return topLeftRadius;
    }

    public float getBottomRightRadius() {
        return bottomRightRadius;
    }

    public float getBottomLeftRadius() {
        return bottomLeftRadius;
    }

    public float getRadius() {
        return radius;
    }

    public void setUrl(Object url) {
        this.url = url;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setPlaceholder(int placeholder) {
        this.placeholder = placeholder;
    }

    public void setErrorPic(int errorPic) {
        this.errorPic = errorPic;
    }

    public void setCrossFade(boolean crossFade) {
        isCrossFade = crossFade;
    }

    public void setCenterCrop(boolean centerCrop) {
        isCenterCrop = centerCrop;
    }

    public void setCircle(boolean circle) {
        isCircle = circle;
    }

    public void setTopRightRadius(float topRightRadius) {
        this.topRightRadius = topRightRadius;
    }

    public void setTopLeftRadius(float topLeftRadius) {
        this.topLeftRadius = topLeftRadius;
    }

    public void setBottomRightRadius(float bottomRightRadius) {
        this.bottomRightRadius = bottomRightRadius;
    }

    public void setBottomLeftRadius(float bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public OnLoadListener getListener() {
        return listener;
    }

    public void setListener(OnLoadListener listener) {
        this.listener = listener;
    }

    public boolean getAsBitmap() {
        return asBitmap;
    }

    public void asBitmap(boolean asBitmap) {
        this.asBitmap = asBitmap;
    }

    public @CacheStrategy.Strategy
    int getCacheStrategy() {
        return cacheStrategy;
    }

    public void setCacheStrategy(int cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
    }

    public boolean isClearMemory() {
        return clearMemory;
    }

    public boolean isClearDiskCache() {
        return clearDiskCache;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        protected Object url;
        protected ImageView imageView = null;
        protected int placeholder = -1;
        protected int errorPic = -1;
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

        private Builder() {
        }

        public Builder url(Object url) {
            this.url = url;
            return this;
        }

        public Builder imageView(ImageView imageView) {
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

        public Builder centerCrop(boolean centerCrop) {
            isCenterCrop = centerCrop;
            return this;
        }

        public Builder isCircle(boolean circle) {
            isCircle = circle;
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

        public void setAsBitmap(boolean asBitmap) {
            this.asBitmap = asBitmap;
        }


        public Builder cacheStrategy(@CacheStrategy.Strategy int cacheStrategy) {
            this.cacheStrategy = cacheStrategy;
            return this;
        }

        public boolean isClearMemory() {
            return clearMemory;
        }

        public boolean isClearDiskCache() {
            return clearDiskCache;
        }

        public BaseImageConfig show() {
            return new BaseImageConfig(this);
        }
    }
}
