package app.ifox.com.eopcandroid;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by 13118467271 on 2017/9/26.
 */

public class MyApplication extends Application{
    public void onCreate(){
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
