package me.alex.baseimageloaderdemo;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/11 0011
 * <p>
 * 页面内容介绍: DataBinding JavaBean
 * <p>
 * ================================================
 */
public class ImageData extends BaseObservable {
    private String content;
    private String imageUrl;

    @Bindable
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        notifyPropertyChanged(BR.content);
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }
}
