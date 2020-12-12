package me.alex.baseimageloader.listener;

import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.Target;

/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/8 0008
 * <p>
 * 页面内容介绍: 默认加载结果监听,参数文档参考GlideV4
 * <p>
 * ================================================
 */
public interface OnLoadListener {
    /**
     * 加载成功结果回调监听
     *
     * @param resource
     * @param model
     * @param target
     * @param dataSource
     * @param isFirstResource
     */
    void onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource);

    void onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource);
}
