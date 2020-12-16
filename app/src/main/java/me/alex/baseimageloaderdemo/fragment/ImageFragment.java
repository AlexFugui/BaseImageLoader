package me.alex.baseimageloaderdemo.fragment;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.target.Target;
import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.Layout;

import me.alex.baseimageloader.BaseImageLoader;
import me.alex.baseimageloader.config.BaseImageConfig;
import me.alex.baseimageloader.entity.ImageResult;
import me.alex.baseimageloader.listener.OnBitmapResult;
import me.alex.baseimageloader.listener.OnFileResult;
import me.alex.baseimageloader.listener.OnGifResult;
import me.alex.baseimageloader.listener.OnLoadListener;
import me.alex.baseimageloader.srtategy.CacheStrategy;
import me.alex.baseimageloader.view.BaseImageView;
import me.alex.baseimageloaderdemo.ImageConfig;
import me.alex.baseimageloaderdemo.MainActivity;
import me.alex.baseimageloaderdemo.R;

/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/15
 * <p>
 * 页面内容介绍: ImageView
 * <p>
 * ================================================
 */
@Layout(R.layout.fragment_image)
public class ImageFragment extends BaseFragment<MainActivity> {
    ImageView img1, img2, img3, img4, img5, img6, img7, img10, img11, img12, img13, img14;
    BaseImageLoader mImageLoader;

    private final String imageUrl = "https://images.xiaozhuanlan.com/photo/2020/4919e1e317209facf63c83d0686398bb.png";
    private final String imageUrlHeight = "https://images.xiaozhuanlan.com/photo/2020/b5a8f22bb93491d3b31ec2d60c709cf9.png";
    private final String patchImageUrl = "http://kongzue.com/test/img_notification_ios.9.png";
    private final String imageUrlTest = "https://images.xiaozhuanlan.com/photo/2020/de67120589fd1b314c7a2e75a2233b06.png";
    private final String gifUrl = "https://images.xiaozhuanlan.com/photo/2020/e09ab1c37ca4fe242e63bc6ef2f34551.gif";

    @Override
    public void initViews() {
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img10 = findViewById(R.id.img10);
        img11 = findViewById(R.id.img11);
        img12 = findViewById(R.id.img12);
        img13 = findViewById(R.id.img13);
        img14 = findViewById(R.id.img14);

        mImageLoader = new BaseImageLoader();
        //全用法示例
        mImageLoader.loadImage(me, ImageConfig.builder()
                .url(Uri.parse(imageUrl))//url
                .imageView(img1)//imageView
                .placeholder(R.drawable.ic_baseline_adb_24)//占位图
                .errorPic(R.mipmap.ic_launcher)//加载错误图片
                .cacheStrategy(CacheStrategy.ALL)//缓存策略
                .centerCrop()//centerCrop
                .crossFade(true)//淡出淡入
                .isCircle()//是否圆形显示
                .setAsBitmap(true)//是否以bitmap加载图片,默认为drawable格式
                .setRadius(30)//设置通用圆角,单位dp
                .setTopRightRadius(10)//左上圆角,单位dp
                .setTopLeftRadius(20)//右上圆角,单位dp
                .setBottomRightRadius(30)//左下圆角,单位dp
                .setBottomLeftRadius(40)//右下圆角,单位dp
                .show());

        //使用自定义ImageConfig的建造者模式加载

        //普通图片加载
        mImageLoader.loadImage(me, ImageConfig.builder().url(Uri.parse(imageUrl)).imageView(img1).show());

        //圆形图片加载
        mImageLoader.loadImage(me, ImageConfig.builder().url(Uri.parse(imageUrl)).imageView(img2).isCircle().show());

        //圆角图片加载 圆角30dp
        mImageLoader.loadImage(me, ImageConfig.builder().url(imageUrl).imageView(img3).setRadius(30).show());

        //分别控制4个圆角  如设置通用圆角setRadius(int) 则单独控制圆角失效
        mImageLoader.loadImage(me, ImageConfig.builder().url(imageUrl).imageView(img4)
                .setTopRightRadius(10)
                .setTopLeftRadius(20)
                .setBottomRightRadius(30)
                .setBottomLeftRadius(0)
//                .setRadius(30)
                .show());

        //长方形图片 普通模式加载
        mImageLoader.loadImage(me, ImageConfig.builder().url(imageUrlHeight).imageView(img5).show());

        //长方形图片 centerCrop模式加载
        mImageLoader.loadImage(me, ImageConfig.builder().url(imageUrlHeight).imageView(img6).centerCrop().show());

        //长方形图片 centerCrop模式加载 + 分别控制4个圆角  如设置通用圆角setRadius(int) 则单独控制圆角失效
        mImageLoader.loadImage(me, ImageConfig.builder().url(imageUrlHeight).imageView(img7).centerCrop()
                .setTopRightRadius(10)
                .setTopLeftRadius(20)
                .setBottomRightRadius(30)
                .setBottomLeftRadius(0)
//                .setRadius(30)
                .show());

        //加载mipmap图片
        mImageLoader.loadImage(me, BaseImageConfig.builder().url(R.mipmap.ic_launcher).imageView(img10).show());

        //单例加载drawable文件 (SVG格式)
        BaseImageLoader.getInstance().loadImage(me, ImageConfig.builder().url(R.drawable.ic_baseline_adb_24).imageView(img11).show());

        //网络.9图片
        mImageLoader.loadImage(me, ImageConfig.builder().url(Uri.parse(patchImageUrl)).imageView(img12).show());

        //网络.9图片 + 加载结果回调
        mImageLoader.loadImage(me, ImageConfig.builder().url(patchImageUrl).imageView(img13).setListener(new OnLoadListener() {
            @Override
            public void onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Log.e("OnResourceReady", "resource:" + resource.toString() + ",model:" + model.toString() + ",target:" + target.toString() + ",isFirstResource:" + isFirstResource);
            }

            @Override
            public void onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                assert e != null;
                Log.e("OnLoadFailed", "GlideException:" + e.toString() + ",model:" + model.toString() + ",target:" + target.toString() + ",isFirstResource:" + isFirstResource);
            }
        }).show());


        //根据图片类型直出对象
        //需要根据参数类型判断获取的字段,比如使用OnBitmapResult,就只有getBitmap方法不为null
        //根据是否传入imageView是否直接显示图片,如果想自己处理过资源再加载则不传入imageView

        //example:
        //mImageLoader.loadImageAs(me, imageUrlTest, new OnBitmapResult() {
        //    @Override
        //    public void OnResult(ImageResult result) {
        //        Log.e("result", result.getBitmap() + "");
        //    }
        //});


        //加载图片且获得bitmap格式图片 且以 imageView.setImageBitmap(bitmap) 模式加载图片
        mImageLoader.loadImageAs(me, imageUrlTest, img14, new OnBitmapResult() {
            @Override
            public void OnResult(ImageResult result) {
                Log.e("result", result.getBitmap() + "");
            }
        });


        //使用File类型获取result时,默认result.getFile()是在设置的cache目录中
        //加载图片且获得File文件 但是以Glide默认方式加载图片(drawable格式) imageView.setImageDrawable(drawable);
        mImageLoader.loadImageAs(me, imageUrlTest, img14, new OnFileResult() {
            @Override
            public void OnResult(ImageResult result) {
                Log.e("result", result.getFile() + "");
            }
        });

        //加载gif且获得gif文件 以 imageView.setImageDrawable(GifDrawable); 模式加载图片
        mImageLoader.loadImageAs(me, gifUrl, img14, new OnGifResult() {
            @Override
            public void OnResult(ImageResult result) {
                Log.e("result", result.getGif() + "");
            }
        });


        //加载图片且获得drawable格式图片 以Glide默认方式加载图片(drawable格式) imageView.setImageDrawable(drawable);
        //mImageLoader.loadImageAs(me, imageUrlTest, img14, new OnDrawableResult() {
        //    @Override
        //    public void OnResult(ImageResult result) {
        //        Log.e("result", result.getDrawable() + "");
        //    }
        //});

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }
}
