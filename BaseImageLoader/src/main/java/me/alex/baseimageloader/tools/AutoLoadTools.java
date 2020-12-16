package me.alex.baseimageloader.tools;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import me.alex.baseimageloader.BaseImageLoader;
import me.alex.baseimageloader.config.BaseImageSetting;

/**
 * ================================================
 * Description:
 * <p>
 * Created by Alex on 2020/12/16
 * <p>
 * 页面内容介绍:
 * <p>
 * ================================================
 */
public class AutoLoadTools {
    private int cacheSize = BaseImageSetting.getInstance().getCacheSize();

    private static AutoLoadTools instance = new AutoLoadTools();

    public static AutoLoadTools getInstance() {
        if (instance == null) {
            instance = new AutoLoadTools();
        }
        return instance;
    }

    public AutoLoadTools() {

    }

    private Map<String, Bitmap> cache = new LinkedHashMap<String, Bitmap>() {
        @Override
        protected boolean removeEldestEntry(Entry<String, Bitmap> pEldest) {
            return size() > cacheSize;
        }
    };

    private String cacheZipFilePath = "";
    private File zipFileCache;
    private ZipFile zfCache;

    /**
     * 读取zip包里的文件（不需要解压zip）
     *
     * @param zipFilePath      zip包路径
     * @param readFileName 需要读取的文件名
     * @return 读取结果
     */
    public Bitmap readZipFile(String zipFilePath, String readFileName) {
        File zipFile = null;
        ZipFile zf = null;
        try {
            if (cacheZipFilePath.isEmpty()) {
                zipFile = new File(zipFilePath);
                zipFileCache = zipFile;
                cacheZipFilePath = zipFilePath;
                zf = new ZipFile(zipFileCache);
                zfCache = zf;
            }
            if (zipFilePath.endsWith(cacheZipFilePath)) {
                zipFile = zipFileCache;
                zf = zfCache;
            }
            if (zipFile == null) {
                zipFile = new File(zipFilePath);
            }
            if (zf == null) {
                zf = new ZipFile(zipFile);
            }
            if (!cache.containsKey(readFileName)) {
                InputStream in = new BufferedInputStream(new FileInputStream(zipFile));
                ZipInputStream zin = new ZipInputStream(in);
                ZipEntry ze;
                while ((ze = zin.getNextEntry()) != null) {
                    if (!ze.isDirectory()) {
                        if (ze.getName().contains(readFileName)) {
                            cache.put(ze.getName(), new BitmapDrawable(null, zf.getInputStream(ze)).getBitmap());
                        }
                    }
                }
                zin.closeEntry();
                in.close();
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
//        Log.e(">>>cacheSize:", cache.size() + "," + cache);
        return cache.get(readFileName);
    }

    public int getCacheSize() {
        return cache.size();
    }

}
