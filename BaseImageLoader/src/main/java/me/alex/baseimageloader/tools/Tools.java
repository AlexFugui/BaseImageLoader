package me.alex.baseimageloader.tools;

import android.content.Context;

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
public  class Tools {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
