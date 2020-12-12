package me.alex.baseimageloaderdemo;

import me.alex.baseimageloader.config.BaseImageConfig;

/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/4 0004
 * <p>
 * 页面内容介绍:
 * 继承自 {@link BaseImageConfig} 实现了基本功能需求字段
 * 如需添加一个自定义功能
 * 自行实现构造方法并且在自定义的{@link BaseImageLoaderStrategy}中重写loadImage接口实现方法(暂时不建议重写BaseImageLoaderStrategy,自定义类继承BaseImageConfig是建议的方式)
 * 参照{@link BaseImageLoader}
 * <p>
 * ================================================
 */
public class ImageConfig extends BaseImageConfig {

    private boolean isVisibility;

    public void setVisibility(boolean visibility) {
        isVisibility = visibility;
    }

    public ImageConfig() {

    }

    public ImageConfig(Builder builder) {
        super(builder);
    }

}
