package com.vongihealth.network.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import timber.log.Timber;

/**
 * @author GoGo on 2019-01-29.
 */
public abstract class ProgressCallBack {

    private String destFileDir; // 本地文件存放路径
    private String destFileName; // 文件名

    public ProgressCallBack(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
    }

    public abstract void progress(long progress, long total);

    public abstract void onStart();

    public abstract void onCompleted(File file);

    public abstract void onError(Throwable e);


    public String getDestFileDir() {
        return destFileDir;
    }

    public void setDestFileDir(String destFileDir) {
        this.destFileDir = destFileDir;
    }

    public String getDestFileName() {
        return destFileName;
    }

    public void setDestFileName(String destFileName) {
        this.destFileName = destFileName;
    }


    public void saveFile(ResponseBody body) {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            is = body.byteStream();
            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
//            unsubscribe();
            //onCompleted();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                Timber.e(e.getMessage());
            }
        }
    }
}
