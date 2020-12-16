package me.alex.baseimageloaderdemo.fragment;

import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.Layout;

import me.alex.baseimageloader.view.BaseImageView;
import me.alex.baseimageloaderdemo.MainActivity;
import me.alex.baseimageloaderdemo.R;

/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/15
 * <p>
 * 页面内容介绍: 自定义View
 * <p>
 * ================================================
 */
@Layout(R.layout.fragment_baseimageview)
public class BaseImageViewFragment extends BaseFragment<MainActivity> {
    private final String imageUrlTest = "https://images.xiaozhuanlan.com/photo/2020/de67120589fd1b314c7a2e75a2233b06.png";

    BaseImageView img15;

    @Override
    public void initViews() {
        img15 = findViewById(R.id.img15);

    }

    @Override
    public void initDatas() {
        //自定义VIew简单使用
        img15.setRadius(30);
        img15.load(imageUrlTest);

        //dataBinding使用,该框架不支持dataBinding 只做代码示例演示
//        ImageData data = new ImageData();
//        data.setImageUrl(imageUrl);
//        data.setContent("自定义View dataBinding使用");
//        binding.setData(data);
    }

    @Override
    public void setEvents() {

    }
}
