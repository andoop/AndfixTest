package cn.andoop.android.andfixtest;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.tv_result);
    }
    public void caculate(View view){
        tv.setText(new Caculator().add(1,1)+"");
    }
    /**
     * 修复问题
     * @param view
     */
    public void fix(View view){
        MApplication application = (MApplication) getApplication();
        try {
            application.patchManager.addPatch(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"andfix/out.apatch");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 移除所有apatch
     * @param view
     */
    public void remove(View view){
        MApplication application = (MApplication) getApplication();
        application.patchManager.removeAllPatch();
    }
}
