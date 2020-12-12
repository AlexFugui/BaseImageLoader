package me.alex.baseimageloader.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.alex.baseimageloader.BaseImageLoader;
import me.alex.baseimageloader.R;
import me.alex.baseimageloader.config.BaseImageConfig;
import me.alex.baseimageloader.config.BaseImageSetting;
import me.alex.baseimageloader.srtategy.CacheStrategy;
import me.alex.baseimageloader.tools.Tools;


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
@SuppressLint("AppCompatCustomView")
public class BaseImageView extends ImageView {
    private Context context;
    private BaseImageLoader loader;
    private BaseImageConfig config = new BaseImageConfig();

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
    protected int topRightRadius;

    /**
     * 右上 圆角
     */
    protected int topLeftRadius;

    /**
     * 左下 圆角
     */
    protected int bottomRightRadius;

    /**
     * 右下 圆角
     */
    protected int bottomLeftRadius;

    /**
     * 通用圆角
     */
    protected int radius;

    /**
     * 缓存类型 默认为通用配置中的模式
     */
    private @CacheStrategy.Strategy
    int cacheStrategy;

    public BaseImageView(@NonNull Context context) {
        super(context);
    }

    public BaseImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BaseImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        this.context = context;
        loader = new BaseImageLoader();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BaseImageView, 0, 0);
        //占位图
        placeholder = typedArray.getResourceId(R.styleable.BaseImageView_placeholder, 0);
        //错误图
        errorPic = typedArray.getResourceId(R.styleable.BaseImageView_errorPic, 0);
        //是否淡出淡入
        isCrossFade = typedArray.getBoolean(R.styleable.BaseImageView_isCrossFade, false);
        //centerCrop
        isCenterCrop = typedArray.getBoolean(R.styleable.BaseImageView_isCenterCrop, false);
        //圆形裁剪
        isCircle = typedArray.getBoolean(R.styleable.BaseImageView_isCircle, false);

        //左上 圆角
        topRightRadius = Tools.px2dp(context, typedArray.getDimensionPixelSize(R.styleable.BaseImageView_topRightRadius, 0));

        //右上 圆角
        topLeftRadius = Tools.px2dp(context, typedArray.getDimensionPixelSize(R.styleable.BaseImageView_topLeftRadius, 0));

        //左下 圆角
        bottomRightRadius = Tools.px2dp(context, typedArray.getDimensionPixelSize(R.styleable.BaseImageView_bottomRightRadius, 0));

        //右下 圆角
        bottomLeftRadius = Tools.px2dp(context, typedArray.getDimensionPixelSize(R.styleable.BaseImageView_bottomLeftRadius, 0));

        //通用圆角
        radius = Tools.px2dp(context, typedArray.getDimensionPixelSize(R.styleable.BaseImageView_radius, 0));

        //缓存策略
        cacheStrategy = typedArray.getInt(R.styleable.BaseImageView_cacheStrategy, BaseImageSetting.getInstance().getCacheStrategy());

        //url
        String url = typedArray.getString(R.styleable.BaseImageView_url);
        if (url != null && !url.equals("")) {
            load(url);
        }

        typedArray.recycle();
    }

    @SuppressLint("CheckResult")
    public void load(Object url) {
        config.setUrl(url);
        config.setImageView(BaseImageView.this);
//        getLifecycle().addObserver(new BaseLifeObserver(context));
        //缓存类型
        //如果BaseImageConfig缓存策略为默认的ALL 则使用默认缓存策略
        config.setCacheStrategy(cacheStrategy);

        //占位图
        if (placeholder != 0) {
            config.setPlaceholder(placeholder);
        }

        //错误图
        if (errorPic != 0) {
            config.setErrorPic(errorPic);
        }

        //是否淡出淡入
        config.setCrossFade(isCrossFade);

        //centerCrop
        config.setCenterCrop(isCenterCrop);
        //是否将图片剪切为圆形
        config.setCircle(isCircle);
        config.setTopRightRadius(topRightRadius);
        config.setTopLeftRadius(topLeftRadius);
        config.setBottomRightRadius(bottomRightRadius);
        config.setBottomLeftRadius(bottomLeftRadius);
        config.setRadius(radius);

        loader.loadImage(context, config);
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(int placeholder) {
        this.placeholder = placeholder;
    }

    public int getErrorPic() {
        return errorPic;
    }

    public void setErrorPic(int errorPic) {
        this.errorPic = errorPic;
    }

    public boolean isCrossFade() {
        return isCrossFade;
    }

    public void setCrossFade(boolean crossFade) {
        isCrossFade = crossFade;
    }

    public boolean isCenterCrop() {
        return isCenterCrop;
    }

    public void setCenterCrop(boolean centerCrop) {
        isCenterCrop = centerCrop;
    }

    public boolean isCircle() {
        return isCircle;
    }

    public void setCircle(boolean circle) {
        isCircle = circle;
    }

    public int getTopRightRadius() {
        return topRightRadius;
    }

    public void setTopRightRadius(int topRightRadius) {
        this.topRightRadius = topRightRadius;
    }

    public int getTopLeftRadius() {
        return topLeftRadius;
    }

    public void setTopLeftRadius(int topLeftRadius) {
        this.topLeftRadius = topLeftRadius;
    }

    public int getBottomRightRadius() {
        return bottomRightRadius;
    }

    public void setBottomRightRadius(int bottomRightRadius) {
        this.bottomRightRadius = bottomRightRadius;
    }

    public int getBottomLeftRadius() {
        return bottomLeftRadius;
    }

    public void setBottomLeftRadius(int bottomLeftRadius) {
        this.bottomLeftRadius = bottomLeftRadius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getCacheStrategy() {
        return cacheStrategy;
    }

    public void setCacheStrategy(int cacheStrategy) {
        this.cacheStrategy = cacheStrategy;
    }

    public void setUrl(String url) {
        load(url);
    }
}
