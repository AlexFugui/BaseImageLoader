package me.alex.baseimageloaderdemo.fragment;

import android.net.Uri;

import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.Layout;

import me.alex.baseimageloader.BaseImageLoader;
import me.alex.baseimageloaderdemo.ImageConfig;
import me.alex.baseimageloaderdemo.MainActivity;
import me.alex.baseimageloaderdemo.R;

/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/16
 * <p>
 * 页面内容介绍: 加载至任意View
 * <p>
 * ================================================
 */
@Layout(R.layout.fragment_custom)
public class CustomFragment extends BaseFragment<MainActivity> {
    BaseImageLoader loader;
    private final String imageUrl = "https://images.xiaozhuanlan.com/photo/2020/4919e1e317209facf63c83d0686398bb.png";

    @Override
    public void initViews() {
        loader = new BaseImageLoader();
        //LinearLayout
        loader.loadImage(me, ImageConfig.builder()
                .url(Uri.parse(imageUrl))
                .imageView(findViewById(R.id.LinearLayout))
                .setRadius(20)
                .show());
        //RelativeLayout
        BaseImageLoader.getInstance().loadImage(me, ImageConfig.builder()
                .url(imageUrl)
                .imageView(findViewById(R.id.RelativeLayout))
                .centerCrop()
                .show());
        //ScrollView
        loader.loadImage(me, ImageConfig.builder()
                .url(Uri.parse(imageUrl))
                .isCircle()
                .imageView(findViewById(R.id.ScrollView))
                .show());
        //TextClock
        loader.loadImage(me, ImageConfig.builder()
                .url(Uri.parse(imageUrl))
                .imageView(findViewById(R.id.TextClock))
                .show());
        //Button
        loader.loadImage(me, ImageConfig.builder()
                .url(imageUrl)
                .setTopRightRadius(10)
                .setTopLeftRadius(20)
                .setBottomRightRadius(30)
                .setBottomLeftRadius(0)
                .imageView(findViewById(R.id.Button))
                .show());
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void setEvents() {

    }
}
