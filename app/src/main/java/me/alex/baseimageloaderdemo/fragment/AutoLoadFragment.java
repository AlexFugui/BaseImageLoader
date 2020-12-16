package me.alex.baseimageloaderdemo.fragment;

import android.os.Environment;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.kongzue.baseframework.BaseFragment;
import com.kongzue.baseframework.interfaces.Layout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import me.alex.baseimageloader.BaseImageLoader;
import me.alex.baseimageloaderdemo.MainActivity;
import me.alex.baseimageloaderdemo.R;

/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/16
 * <p>
 * 页面内容介绍: 自动加载
 * <p>
 * ================================================
 */
@Layout(R.layout.fragment_autoload)
public class AutoLoadFragment extends BaseFragment<MainActivity> {
    ScrollView linearLayout;
    private String ASSETS_NAME = "imgs.zip";
    private String SDCARD_PATH;
    private String LOAD_PATH;


    @Override
    public void initViews() {
        //这里是讲assets文件夹中的imgs.zip释放至sd卡做demo演示
        SDCARD_PATH = me.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath();
        LOAD_PATH = SDCARD_PATH + File.separator + ASSETS_NAME;
        // LOAD_PATH = /storage/emulated/0/Android/data/me.alex.baseimageloaderdemo/files/Documents/imgs.zip;
        log(LOAD_PATH);

        //初始化view
        linearLayout = findViewById(R.id.autoLoadViewGroup);
    }

    @Override
    public void initDatas() {
        try {
            copyDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        log(me.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        log(System.currentTimeMillis());//1608123847926
        BaseImageLoader.getInstance()
                .autoLoadImage(me, linearLayout, LOAD_PATH);
        log(System.currentTimeMillis());//1608123847994
    }

    @Override
    public void setEvents() {

    }

    private void copyDataBase() throws IOException {
        // Path to the just created empty db
        String outFileName = SDCARD_PATH + File.separator + ASSETS_NAME;
        // 判断目录是否存在。如不存在则创建一个目录
        File file = new File(SDCARD_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(outFileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        // Open your local db as the input stream
        InputStream myInput = me.getAssets().open(ASSETS_NAME);
        // Open the empty db as the output stream128
        OutputStream myOutput = new FileOutputStream(outFileName);
        // transfer bytes from the inputfile to the outputfile130
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        // Close the streams136
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }
}
