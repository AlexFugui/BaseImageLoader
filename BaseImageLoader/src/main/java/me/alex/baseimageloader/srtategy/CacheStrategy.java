package me.alex.baseimageloader.srtategy;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import me.alex.baseimageloader.config.BaseImageConfig;


/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/2 0002
 * <p>
 * 页面内容介绍:
 * Glide缓存方式  {@link com.bumptech.glide.load.engine.DiskCacheStrategy}
 * 0对应DiskCacheStrategy.ALL,
 * 1对应DiskCacheStrategy.NONE,
 * 2对应DiskCacheStrategy.SOURCE,
 * 3对应DiskCacheStrategy.RESULT,
 * 4对应DiskCacheStrategy.AUTOMATIC
 * 不配置{@link BaseImageConfig}中的缓存策略则默认为 ALL
 * 如配置了项目统一配置,则单独设置{@link BaseImageConfig}有更高的优先级
 * <p>
 * ================================================
 */

public interface CacheStrategy {

    int ALL = 0;

    int NONE = 1;

    int RESOURCE = 2;

    int DATA = 3;

    int AUTOMATIC = 4;

    @IntDef({ALL, NONE, RESOURCE, DATA, AUTOMATIC})
    @Retention(RetentionPolicy.SOURCE)
    @interface Strategy {
    }

}