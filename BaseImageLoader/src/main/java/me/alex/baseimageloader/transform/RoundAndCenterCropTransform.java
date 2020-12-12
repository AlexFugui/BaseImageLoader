package me.alex.baseimageloader.transform;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/4
 * <p>
 * 页面内容介绍: 同时实现CenterCrop和自定义4个圆角效果
 * <p>
 * ================================================
 */
public class RoundAndCenterCropTransform extends BitmapTransformation {
    private static final String ID = RoundAndCenterCropTransform.class.getName();
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);
    private boolean isCenterCrop = false;
    private Float[] radiusList = new Float[4];

    public RoundAndCenterCropTransform(boolean isCenterCrop, float topRight, float topLeft, float bottomRight, float bottomLeft) {
        this.isCenterCrop = isCenterCrop;
        radiusList[0] = topRight;
        radiusList[1] = topLeft;
        radiusList[2] = bottomRight;
        radiusList[3] = bottomLeft;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        if (isCenterCrop) {
            Bitmap bitmap = TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight);
            return roundCrop(pool, bitmap);
        } else {
            return roundCrop(pool, toTransform);
        }
    }

    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
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
        return result;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID.getBytes(CHARSET));
        byte[] radiusData = ByteBuffer.allocate(4).putInt(Arrays.hashCode(radiusList)).array();
        messageDigest.update(radiusData);
    }
}
