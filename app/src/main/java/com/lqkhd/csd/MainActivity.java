package com.lqkhd.csd;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 安卓开发由一个APP拉起另一个APP的方法总结
 * 转载请标明出处：http://blog.csdn.net/zang_chen/article/details/76677846
 * 本文出自陈小二来巡山的博客：https://blog.csdn.net/zang_chen
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isApkInstalled(MainActivity.this, "com.pull.csd")) {
                    //包名拉起 第一种方法
//                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.pull.csd");
//                    if (intent != null) {
//                        intent.putExtra("type", "110");
//                        intent.putExtra("token", "120");
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                    }

                    //包名拉起 第二种方法
//                    ComponentName componentName = new ComponentName("com.pull.csd", "com.pull.csd.SplashActivity");
//                    Intent intent = new Intent();
//                    intent.setComponent(componentName);
//                    intent.putExtra("type", "110");
//                    intent.putExtra("token", "120");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);


                    //ACTION拉起 第一种方法
//                    Intent intent = new Intent();
//                    intent.setAction("CSD");
//                    intent.putExtra("type", "110");
//                    intent.putExtra("token", "120");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);

                    //ACTION拉起 第二种方法
//                    ComponentName componentName = new ComponentName("com.pull.csd", "com.pull.csd.SplashActivity");
//                    Intent intent = new Intent();
//                    intent.setAction("CSD");
//                    intent.setComponent(componentName);
//                    intent.putExtra("type", "110");
//                    intent.putExtra("token", "120");
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);


                    //URL拉起方法
                    Intent intent = new Intent();
                    intent.setData(Uri.parse("csd://pull.csd.demo/cyn?type=110&token=120"));
                    intent.putExtra("", "");//这里Intent当然也可传递参数,但是一般情况下都会放到上面的URL中进行传递
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "未安装目标应用", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public boolean isApkInstalled(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName)) {
            return false;
        }
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
