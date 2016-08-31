package cn.andoop.android.andfixtest;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;

/**
 * Created by 黄栋 on 2016/8/31.
 */
public class MApplication extends Application{

    public PatchManager patchManager;
    @Override
    public void onCreate() {
        super.onCreate();
        patchManager=new PatchManager(this);
        //初始化patch版本，以后更新补丁包的时候，也要更新版本
        patchManager.init("1.0");
        //加载已经添加的patch
        patchManager.loadPatch();
    }
}
