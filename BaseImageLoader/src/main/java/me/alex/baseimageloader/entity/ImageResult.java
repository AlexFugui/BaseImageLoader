package me.alex.baseimageloader.entity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.load.resource.gif.GifDrawable;

import java.io.File;

/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/8 0008
 * <p>
 * 页面内容介绍:
 * <p>
 * ================================================
 */
public class ImageResult {
    private Bitmap bitmap;
    private File file;
    private Drawable drawable;
    private GifDrawable gif;

    public ImageResult(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public ImageResult(File file) {
        this.file = file;
    }

    public ImageResult(Drawable drawable) {
        this.drawable = drawable;
    }

    public ImageResult(GifDrawable gif) {
        this.gif = gif;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }


    public File getFile() {
        return file;
    }


    public Drawable getDrawable() {
        return drawable;
    }


    public GifDrawable getGif() {
        return gif;
    }
}
