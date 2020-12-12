package me.alex.baseimageloader.transform;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/4
 * <p>
 * 页面内容介绍: 单独控制4个圆角效果 与 centerCrop不兼容
 * <p>
 * ================================================
 */
public class RoundedCornersTransform extends BitmapTransformation {

    private static final String ID = RoundedCornersTransform.class.getName();
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    private Float[] radiusList = new Float[4];
    private boolean isCenterCrop = false;

    /**
     * 默认构造方法
     *
     * @param topRight    左上圆角
     * @param topLeft     右上圆角
     * @param bottomRight 左下圆角
     * @param bottomLeft  右下圆角
     */
    public RoundedCornersTransform(float topRight, float topLeft, float bottomRight, float bottomLeft) {
        radiusList[0] = topRight;
        radiusList[1] = topLeft;
        radiusList[2] = bottomRight;
        radiusList[3] = bottomLeft;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        //获取加载的图片
        Bitmap bitmap = pool.get(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        //关联画笔绘制的原图bitmap
        BitmapShader shader = new BitmapShader(toTransform, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());
        // 左上
        canvas.save();
        canvas.clipRect(0, 0, canvas.getWidth() / 2, canvas.getHeight() / 2);
        canvas.drawRoundRect(rectF, radiusList[0], radiusList[0], paint);
        canvas.restore();
        // 右上
        canvas.save();
        canvas.clipRect(canvas.getWidth() / 2, 0, canvas.getWidth(), canvas.getHeight() / 2);
        canvas.drawRoundRect(rectF, radiusList[1], radiusList[1], paint);
        canvas.restore();
        // 右下
        canvas.save();
        canvas.clipRect(canvas.getWidth() / 2, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight());
        canvas.drawRoundRect(rectF, radiusList[2], radiusList[2], paint);
        canvas.restore();
        // 左下
        canvas.save();
        canvas.clipRect(0, canvas.getHeight() / 2, canvas.getWidth() / 2, canvas.getHeight());
        canvas.drawRoundRect(rectF, radiusList[3], radiusList[3], paint);
        canvas.restore();
        return bitmap;
    }


    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID.getBytes(CHARSET));
        byte[] radiusData = ByteBuffer.allocate(4).putInt(Arrays.hashCode(radiusList)).array();
        messageDigest.update(radiusData);
    }
}
