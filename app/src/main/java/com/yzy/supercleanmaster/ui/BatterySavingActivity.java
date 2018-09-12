package com.yzy.supercleanmaster.ui;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.widget.Toast;

import java.util.List;

public class BatterySavingActivity {

    List<ApplicationInfo> packages;
    PackageManager pm;
    pm = getPackageManager();
    //get a list of installed apps.
    packages = pm.getInstalledApplications(0);

    ActivityManager mActivityManager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);

   for (ApplicationInfo packageInfo : packages) {
        if((packageInfo.flags & ApplicationInfo.FLAG_SYSTEM)==1)continue;
        if(packageInfo.packageName.equals("mypackage")) continue;
        mActivityManager.killBackgroundProcesses(packageInfo.packageName);
    }

  //  ActivityManager actvityManager = (ActivityManager)
  //      getApplicationContext().getSystemService( getApplicationContext().ACTIVITY_SERVICE );
  // List<ActivityManager.RunningAppProcessInfo> procInfos = actvityManager.getRunningAppProcesses();

  // for(int pnum = 0; pnum < procInfos.size(); pnum++)
  // {
  //     if((procInfos.get(pnum)).processName.contains("android")||(procInfos.get(pnum)).processName.contains("system")||(procInfos.get(pnum).processName.contains("xiaomi")||(procInfos.get(pnum)).processName.contains("huawei")||(procInfos.get(pnum)).processName.contains("adil"))
  //     {
  //         //Toast.makeText(getApplicationContext(), "system apps", Toast.LENGTH_SHORT).show();
  //     }
  //     else
  //     {
  //         actvityManager.killBackgroundProcesses(procInfos.get(pnum).processName);
  //         Toast.makeText(getApplicationContext(), "killed "+procInfos.get(pnum).processName, Toast.LENGTH_SHORT).show();

  //     }
  // }
}
