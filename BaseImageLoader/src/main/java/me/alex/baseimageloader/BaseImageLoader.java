package me.alex.baseimageloader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;

import java.io.File;

import me.alex.baseimageloader.config.BaseImageConfig;
import me.alex.baseimageloader.entity.ImageResult;
import me.alex.baseimageloader.listener.OnAsListener;
import me.alex.baseimageloader.listener.OnBitmapResult;
import me.alex.baseimageloader.listener.OnDrawableResult;
import me.alex.baseimageloader.listener.OnFileResult;
import me.alex.baseimageloader.listener.OnGifResult;
import me.alex.baseimageloader.listener.OnLoadListener;
import me.alex.baseimageloader.module.GlideAlex;
import me.alex.baseimageloader.module.GlideRequest;
import me.alex.baseimageloader.module.GlideRequests;
import me.alex.baseimageloader.srtategy.BaseImageLoaderStrategy;
import me.alex.baseimageloader.srtategy.CacheStrategy;
import me.alex.baseimageloader.tools.Tools;
import me.alex.baseimageloader.transform.RoundAndCenterCropTransform;


/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/4 0004
 * <p>
 * 页面内容介绍: 可自行实现 {@link BaseImageLoaderStrategy} 和 {@link BaseImageConfig} 替换现有策略
 * <p>
 * ================================================
 */
public class BaseImageLoader implements BaseImageLoaderStrategy<BaseImageConfig, OnAsListener> {

    public static BaseImageLoader instance = new BaseImageLoader();

    public static BaseImageLoader getInstance() {
        return instance;
    }

    public BaseImageLoader() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadImage(@NonNull Context context, @NonNull BaseImageConfig config) {
        GlideRequests requests;
        requests = GlideAlex.with(context);//如果context是activity/Fragment则自动使用V层的生命周期
        if (config.getAsBitmap()) {
            requests.asBitmap();
        }
        GlideRequest<Drawable> glideRequest = requests.load(config.getUrl());
        //缓存类型
        //如果BaseImageConfig缓存策略为默认的ALL 则使用默认缓存策略
        if (config.getCacheStrategy() == CacheStrategy.ALL) {
            glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL);
        } else {
            //如果不是ALL则使用config中的配置
            switch (config.getCacheStrategy()) {
                //缓存策略
                case CacheStrategy.NONE:
                    glideRequest.diskCacheStrategy(DiskCacheStrategy.NONE);
                    break;
                case CacheStrategy.RESOURCE:
                    glideRequest.diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                    break;
                case CacheStrategy.DATA:
                    glideRequest.diskCacheStrategy(DiskCacheStrategy.DATA);
                    break;
                case CacheStrategy.AUTOMATIC:
                    glideRequest.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                    break;
                case CacheStrategy.ALL:
                    glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL);
                default:
                    glideRequest.diskCacheStrategy(DiskCacheStrategy.ALL);
                    break;
            }
        }


        //占位图
//        if (config.getPlaceholder() != -1) {
        glideRequest.placeholder(config.getPlaceholder());
//        }
        //错误图
//        if (config.getErrorPic() != -1) {
        glideRequest.error(config.getErrorPic());
//        }
        //是否淡出淡入
        if (config.isCrossFade()) {
            glideRequest.transition(DrawableTransitionOptions.withCrossFade());
        }
        //是否将图片剪切为圆形
        if (config.isCircle()) {
            //如果剪裁成圆形就忽略圆角
            if (config.isCenterCrop()) {
                glideRequest.centerCrop();
            }
            glideRequest.circleCrop();
        } else {
            //圆形和圆角同时只能设置一种 同时设置只生效圆形
            if (config.getRadius() > 0) {
                if (config.isCenterCrop()) {//设置裁剪+4个圆角相同
                    glideRequest.transform(new RoundAndCenterCropTransform(
                            config.isCenterCrop(),
                            Tools.dp2px(context, config.getRadius()),
                            Tools.dp2px(context, config.getRadius()),
                            Tools.dp2px(context, config.getRadius()),
                            Tools.dp2px(context, config.getRadius()))
                    );
                } else {//单独设置4个圆角
                    glideRequest.transform(new RoundedCorners((int) config.getRadius()));
                }
            } else {
                //如果没设置通用圆角,设置了单独圆角+裁剪
                if (config.getTopRightRadius() > 0 || config.getTopLeftRadius() > 0 || config.getBottomRightRadius() > 0 || config.getBottomLeftRadius() > 0) {
                    glideRequest.transform(new RoundAndCenterCropTransform(
                            config.isCenterCrop(),
                            Tools.dp2px(context, config.getTopRightRadius()),
                            Tools.dp2px(context, config.getTopLeftRadius()),
                            Tools.dp2px(context, config.getBottomRightRadius()),
                            Tools.dp2px(context, config.getBottomLeftRadius()))
                    );
                } else {
                    //没有设置圆角
                    if (config.isCenterCrop()) {
                        //裁减
                        glideRequest.centerCrop();
                    }
                }
            }
        }

        final OnLoadListener listener = config.getListener();
        if (listener != null) {
            glideRequest.listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    listener.onLoadFailed(e, model, target, isFirstResource);
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                    listener.onResourceReady(resource, model, target, dataSource, isFirstResource);
                    return false;
                }
            });
        }

        glideRequest.into(config.getImageView());
    }

    @Override
    public void loadImageAs(@NonNull Context context, @NonNull Object url, @NonNull OnAsListener listener) {
        loadImageAs(context, url, null, listener);
    }

    @Override
    public void loadImageAs(@NonNull final Context context, @NonNull final Object url, @Nullable final ImageView imageView, @NonNull final OnAsListener listener) {
        GlideRequests requests;
        requests = GlideAlex.with(context);//如果context是activity/Fragment则自动使用V层的生命周期
        if (listener instanceof OnBitmapResult) {
            requests.asBitmap()
                    .load(url)
                    .into(new ImageViewTarget<Bitmap>(imageView != null ? imageView : new ImageView(context)) {
                        @Override
                        protected void setResource(@Nullable Bitmap resource) {
                            if (resource != null) {
                                if (imageView != null) {
                                    imageView.setImageBitmap(resource);
                                }
                                listener.OnResult(new ImageResult(resource));
                            }
                        }
                    });
        } else if (listener instanceof OnFileResult) {
            requests.asFile()
                    .load(url)
//                    .error(GlideAlex.with(context).asFile().load(url))
                    .listener(new RequestListener<File>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<File> target, boolean isFirstResource) {
                            if (imageView != null) {
                                new Handler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        GlideAlex.with(context).load(url).into(imageView);
                                    }
                                });
                            }
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(File resource, Object model, Target<File> target, DataSource dataSource, boolean isFirstResource) {
                            if (imageView != null) {
                                new Handler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        GlideAlex.with(context).load(url).into(imageView);
                                    }
                                });
                            }
                            return false;
                        }
                    })
                    .into(new ImageViewTarget<File>(imageView != null ? imageView : new ImageView(context)) {
                        @Override
                        protected void setResource(@Nullable File resource) {
                            if (resource != null) {
                                listener.OnResult(new ImageResult(resource));
                            }
                        }
                    });
        } else if (listener instanceof OnGifResult) {
            requests.asGif()
                    .load(url)
                    .into(new ImageViewTarget<GifDrawable>(imageView != null ? imageView : new ImageView(context)) {
                        @Override
                        protected void setResource(@Nullable GifDrawable resource) {
                            if (resource != null) {
                                if (imageView != null) {
                                    imageView.setImageDrawable(resource);
                                }
                                listener.OnResult(new ImageResult(resource));
                            }
                        }
                    });
        } else if (listener instanceof OnDrawableResult) {
            requests.asDrawable()
                    .load(url)
                    .into(new ImageViewTarget<Drawable>(imageView != null ? imageView : new ImageView(context)) {
                        @Override
                        protected void setResource(@Nullable Drawable resource) {
                            if (resource != null) {
                                if (imageView != null) {
                                    imageView.setImageDrawable(resource);
                                }
                                listener.OnResult(new ImageResult(resource));
                            }
                        }
                    });
        } else {
            requests.asBitmap()
                    .load(url)
                    .into(new ImageViewTarget<Bitmap>(imageView != null ? imageView : new ImageView(context)) {
                        @Override
                        protected void setResource(@Nullable Bitmap resource) {
                            if (resource != null) {
                                if (imageView != null) {
                                    imageView.setImageBitmap(resource);
                                }
                                listener.OnResult(new ImageResult(resource));
                            }
                        }
                    });
        }
//        GlideRequest<Drawable> glideRequest = requests.load(url);
    }


    @Override
    public void clear(@Nullable Context context, @Nullable BaseImageConfig config) {
        if (context == null) {
            throw new NullPointerException("Context is required");
        }
        if (config == null) {
            throw new NullPointerException("BaseImageConfig is required");
        }

        if (config.getImageView() != null) {//取消在执行的任务并且释放资源
            GlideAlex.get(context).getRequestManagerRetriever().get(context).clear(config.getImageView());
        }

        if (config.isClearMemory()) {//清除内存缓存
            GlideAlex.get(context).clearMemory();
        }

        if (config.isClearDiskCache()) {//清除本地缓存
            GlideAlex.get(context).clearDiskCache();
        }

    }
}
