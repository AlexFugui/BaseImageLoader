#说明
![话不多说先放图](https://images.xiaozhuanlan.com/photo/2020/c36eb5e1aa850f01e02d2aef9a619618.jpg)
支持加载网络图片(String格式url)/本地资源(miamap和drawable)/网络.9图片/gif加载/自定义样式(圆形/圆角/centerCrop)/dataBinding
更多使用方法和示例代码请下载demo源码查看

#设计说明
根据`BaseImageLoader`持有图片View层的`context`和`BaseImageConfig`类实现Glide原生的生命周期感知和多样化的自定义配置加载
`BaseImageConfig`使用建造者模式,使用更灵活更方便,也可自行继承`BaseImageConfig`减少类名长度和实现自定义功能
##主要功能
- loadImage
动态配置congif加载你需求的资源图片
- loadImageAs
获取网络url返回的资源,可获取`drawable`/`bitmap`/`file`/`gif`四种文件格式,可控知否获取资源的同时加载到imageView上
- clear
取消加载或清除内存/储存中的缓存
- BaseImageView
与动态config完全相同功能的自定义ImageView,支持xml中自定义属性配置各种加载需求

#添加依赖
``implementation 'com.alex:BaseImageLoader:1.0.1'``

#使用说明
##1.添加权限
需要添加访问网络和内存读写权限

##2.项目通用配置
功能配置全部可选,如不配置则:
默认内存缓存大小20mb
默认bitmap池缓存30mb
默认硬盘缓存250mb
默认缓存文件夹名称`BaseImageLoaderCache`
默认缓存策略为`AUTOMATIC`,自动模式
```java
BaseImageSetting.getInstance()
                .setMemoryCacheSize(30)//设置内存缓存大小 单位mb
                .setBitmapPoolSize(50)//设置bitmap池缓存大小 单位mb
                .setDiskCacheSize(80)//设置储存储存缓存大小 单位mb
                .setLogLevel(Log.ERROR)//设置log等级
                .setPlaceholder(R.drawable.ic_baseline_adb_24)//设置通用占位图片,全项目生效
                .setErrorPic(R.mipmap.ic_launcher)//设置通用加载错误图片,全项目生效
                .setCacheFileName("BaseImageLoaderDemo")//设置储存缓存文件夹名称,api基于Glide v4
                .setCacheStrategy(CacheStrategy.AUTOMATIC);//设置缓存策略
```

##3.使用
**1.**获取`BaseImageLoader`对象
根据开发者项目设计模式,MVC/MVP/MVVM自行获取`BaseImageLoader`类对象,并自行管理生命周期.
`BaseImageLoader`自行提供单例,`BaseImageLoader.getInstance();`

**2.**加载至ImageView(目前只支持加载至ImageVIew,待更新)
```java
BaseImageLoader mImageLoader = new BaseImageLoader();
mImageLoader.loadImage(this, ImageConfig.builder()
                .url(Uri.parse(imageUrl))//url
                .imageView(img1)//imageView
                .placeholder(R.drawable.ic_baseline_adb_24)//占位图
                .errorPic(R.mipmap.ic_launcher)//加载错误图片
                .cacheStrategy(CacheStrategy.ALL)//缓存策略
                .centerCrop(true)//centerCrop
                .crossFade(true)//淡出淡入
                .isCircle(true)//是否圆形显示
                .setAsBitmap(true)//是否以bitmap加载图片,默认为drawable格式
                .setRadius(30)//设置通用圆角,单位dp
                .setTopRightRadius(10)//左上圆角,单位dp
                .setTopLeftRadius(20)//右上圆角,单位dp
                .setBottomRightRadius(30)//左下圆角,单位dp
                .setBottomLeftRadius(40)//右下圆角,单位dp
                .show());
```
###**注意:**
避免过度绘制和二次绘制,其中优先级
isCircle(true)>setRadius()>setTopRightRadius()/setTopLeftRadius()/setBottomRightRadius/setBottomLeftRadius
1.设置isCircle(true)会使通用圆角设置不生效,减少绘制次数
2.设置setRadius()会使分别控制单独圆角不生效,减少绘制次数

**3.**资源文件直出
```java
/**
     * 加载图片同时获取不同格式的资源
     * @param context {@link Context}
     * @param url 资源url或资源文件
     * @param listener 获取的资源回调结果
     */
    void loadImageAs(@NonNull Context context, @NonNull Object url, @NonNull L listener);

/**
         * 根据图片类型直出对象
         * 需要根据参数类型判断获取的字段,比如使用OnBitmapResult,就只有getBitmap方法不为null
         * 根据是否传入imageView是否直接显示图片,如果想自己处理过资源再加载则不传入imageView
         *
         */
        mImageLoader.loadImageAs(this, imageUrlTest, new OnBitmapResult() {
            @Override
            public void OnResult(ImageResult result) {
                Log.e("result", result.getBitmap() + "");
            }
        });
```

```java
/**
     *
     * @param context {@link Context}
     * @param url 资源url或资源文件
     * @param imageView 显示的imageView
     * @param listener 获取的资源回调结果
     */
    void loadImageAs(@NonNull Context context, @NonNull Object url, @Nullable ImageView imageView, @NonNull L listener);

        /**
         * 加载图片且获得bitmap格式图片 且以 imageView.setImageBitmap(bitmap) 模式加载图片
         */
        mImageLoader.loadImageAs(this, imageUrlTest, img14, new OnBitmapResult() {
            @Override
            public void OnResult(ImageResult result) {
                Log.e("result", result.getBitmap() + "");
            }
        });

        /**
         * 使用File类型获取result时,默认result.getFile()是在设置的cache目录中
         * 加载图片且获得File文件 但是以Glide默认方式加载图片(drawable格式) imageView.setImageDrawable(drawable);
         */
        mImageLoader.loadImageAs(this, imageUrlTest, img14, new OnFileResult() {
            @Override
            public void OnResult(ImageResult result) {
                Log.e("result", result.getFile() + "");
            }
        });

        /**
         * 加载gif且获得gif文件 以 imageView.setImageDrawable(GifDrawable); 模式加载图片
         */
        mImageLoader.loadImageAs(this, gifUrl, img14, new OnGifResult() {
            @Override
            public void OnResult(ImageResult result) {
                Log.e("result", result.getGif() + "");
            }
        });

        /**
         * 加载图片且获得drawable格式图片 以Glide默认方式加载图片(drawable格式) imageView.setImageDrawable(drawable);
         */
        mImageLoader.loadImageAs(this, imageUrlTest, img14, new OnDrawableResult() {
            @Override
            public void OnResult(ImageResult result) {
                Log.e("result", result.getDrawable() + "");
            }
        });
```
**4.**自定义BaseImageView
xml中:
```xml
<me.alex.baseimageloader.view.BaseImageView
    android:layout_width="100dp"
    android:layout_height="100dp"
    app:asBitmap="true"
    app:bottomLeftRadius="40dp"
    app:bottomRightRadius="30dp"
    app:cacheStrategy="ALL"
    app:errorPic="@mipmap/ic_launcher"
    app:isCenterCrop="true"
    app:isCircle="true"
    app:isCrossFade="true"
    app:placeholder="@drawable/ic_baseline_adb_24"
    app:radius="30dp"
    app:topLeftRadius="20dp"
    app:topRightRadius="10dp"
    app:url="https://xxx.xxx.com/photo/xxxxxx.png" />
```
api与代码设置相同

支持DataBinding:
```xml
<me.alex.baseimageloader.view.BaseImageView
    android:layout_width="100dp"
    android:layout_height="100dp"
    app:url="@{data.imageUrl}" />
```
详见demo中dataBinding简单使用
优先级规则同上

**5.**参数说明
**1.BaseImageSetting:**
| 函数名 | 入参类型 |参数说明|
| -------- | -------- | -------- |
| setMemoryCacheSize() | int | 设置内存缓存大小 单位mb |
| setBitmapPoolSize() | int | 设置bitmap池缓存大小 单位mb |
| setDiskCacheSize() | int | 设置储存储存缓存大小 单位mb |
| setLogLevel() | int | 设置框架日志打印等级,详看android.util.Log类与Glide v4文档 |
| placeholder() | int | v1.0.0版本仅支持resource类型int参数 |
| errorPic() | int | v1.0.0版本仅支持resource类型int参数 |
| setCacheFileName() | String | 设置储存缓存文件夹名称,api基于Glide v4 |

**2.BaseImageLoader:**

```java
/**
     * 加载图片 使用继承自BaseImageConfig的配置
     *
     * @param context {@link Context}
     * @param config  {@link BaseImageConfig}  图片加载配置信息
     */
    void loadImage(@NonNull Context context, @NonNull T config);
```
```java
/**
     * 加载图片同时获取不同格式的资源
     * @param context {@link Context}
     * @param url 资源url或资源文件
     * @param listener 获取的资源回调结果
     */
    void loadImageAs(@NonNull Context context, @NonNull Object url, @NonNull L listener);
```
```java
/**
     *
     * @param context {@link Context}
     * @param url 资源url或资源文件
     * @param imageView 显示的imageView
     * @param listener 获取的资源回调结果
     */
    void loadImageAs(@NonNull Context context, @NonNull Object url, @Nullable ImageView imageView, @NonNull L listener);
```
```java
/**
     * 停止加载 或 清除缓存
     *
     * @param context {@link Context}
     * @param config  {@link BaseImageConfig}  图片加载配置信息
     */
    void clear(@NonNull Context context, @NonNull T config);
```

**3.BaseImageConfig:**
| 函数名 | 入参类型类型 |参数说明|
| -------- | -------- | -------- |
| builder() |   无 | BaseImageConfig使用建造者模式,BaseImageConfig默认空构造方法 |
| url() (代码使用)              | Object | 支持原生Glide中所有类型 |
| url() (xml中)              | Object | 支持原生Glide中所有类型,同时支持resource类型 |
| imageView() | ImageView | v1.0.0版本仅支持继承自ImageView的类型 |
| placeholder() | int | v1.0.0版本仅支持resource类型int参数 |
| errorPic() | int | v1.0.0版本仅支持resource类型int参数 |
| cacheStrategy() | int | 设置缓存策略,详看CacheStrategy类与Glide v4文档 |
| setRadius() | int | 设置通用圆角 单位dp |
| setTopRightRadius() | int | 设置左上圆角 单位dp |
| setTopLeftRadius() | int | 设置右上圆角 单位dp |
| setBottomRightRadius() | int | 设置左下圆角 单位dp |
| setBottomLeftRadius() | int | 设置右下圆角 单位dp |
| centerCrop() | Boolean | true为居中裁剪 |
| crossFade() | Boolean | true为开启淡出淡入 |
| isCircle() | Boolean | true为开启圆形裁剪 |
| setAsBitmap() | Boolean | true为以bitmap格式加载图片 |
| clearMemory() | Boolean | true为清除内存中缓存,仅在BaseImageLoader.clear()中生效 |
| clearDiskCache() | Boolean | true为清除储存中缓存,仅在BaseImageLoader.clear()中生效 |
| show() |   无 | BaseImageConfig使用建造者模式,用于new BaseImageConfig对象 |

![](https://images.xiaozhuanlan.com/photo/2020/6ac26499784a44871d0f8d988b53a6cc.png)

