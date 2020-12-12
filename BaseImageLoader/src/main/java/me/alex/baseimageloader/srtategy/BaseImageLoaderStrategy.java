/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.alex.baseimageloader.srtategy;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.alex.baseimageloader.config.BaseImageConfig;
import me.alex.baseimageloader.listener.OnAsListener;


/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/2 0002
 * <p>
 * 页面内容介绍:
 * 图片加载策略,实现 {@link BaseImageLoaderStrategy}
 * 并通过 {@link BaseImageLoaderStrategy} 配置后,才可进行图片请求
 * <p>
 * ================================================
 */

public interface BaseImageLoaderStrategy<T extends BaseImageConfig, L extends OnAsListener> {

    /**
     * 加载图片 使用继承自BaseImageConfig的配置
     *
     * @param context {@link Context}
     * @param config  {@link BaseImageConfig}  图片加载配置信息
     */
    void loadImage(@NonNull Context context, @NonNull T config);

    /**
     * 加载图片同时获取不同格式的资源
     * @param context {@link Context}
     * @param url 资源url或资源文件
     * @param listener 获取的资源回调结果
     */
    void loadImageAs(@NonNull Context context, @NonNull Object url, @NonNull L listener);

    /**
     *
     * @param context {@link Context}
     * @param url 资源url或资源文件
     * @param imageView 显示的imageView
     * @param listener 获取的资源回调结果
     */
    void loadImageAs(@NonNull Context context, @NonNull Object url, @Nullable ImageView imageView, @NonNull L listener);

    /**
     * 停止加载
     *
     * @param context {@link Context}
     * @param config  {@link BaseImageConfig}  图片加载配置信息
     */
    void clear(@NonNull Context context, @NonNull T config);

}
