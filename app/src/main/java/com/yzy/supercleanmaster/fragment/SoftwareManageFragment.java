package com.yzy.supercleanmaster.fragment;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.adapter.SoftwareAdapter;
import com.yzy.supercleanmaster.base.BaseFragment;
import com.yzy.supercleanmaster.model.AppInfo;
import com.yzy.supercleanmaster.utils.StorageUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class SoftwareManageFragment extends BaseFragment {


    Context mContext;
    public static final int REFRESH_BT = 111;
    private static final String ARG_POSITION = "position";
    private int position; // 0:应用软件，2 系统软件
    SoftwareAdapter mAutoStartAdapter;

    @InjectView(R.id.listview)
    ListView listview;


    @InjectView(R.id.topText)
    TextView topText;
    List<AppInfo> userAppInfos = null;
    List<AppInfo> systemAppInfos = null;
    @InjectView(R.id.progressBar)
    View mProgressBar;
    @InjectView(R.id.progressBarText)
    TextView mProgressBarText;

    private Method mGetPackageSizeInfoMethod;

    AsyncTask<Void, Integer, List<AppInfo>> task;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(ARG_POSITION);


    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_software, container, false);
        ButterKnife.inject(this, view);
        mContext = getActivity();
        try {
            mGetPackageSizeInfoMethod = mContext.getPackageManager().getClass().getMethod(
                    "getPackageSizeInfo", String.class, IPackageStatsObserver.class);


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        fillData();
    }

    @Override
    public void onPause() {
        super.onPause();
        task.cancel(true);
    }

    private void fillData() {

        if (position == 0) {
            topText.setText("");

        } else {
            topText.setText("卸载下列软件，会影响正常使用");

        }


        task = new AsyncTask<Void, Integer, List<AppInfo>>() {
            private int mAppCount = 0;

            @Override
            protected List<AppInfo> doInBackground(Void... params) {
                PackageManager pm = mContext.getPackageManager();
                List<PackageInfo> packInfos = pm.getInstalledPackages(0);
                publishProgress(0, packInfos.size());
                List<AppInfo> appinfos = new ArrayList<AppInfo>();
                for (PackageInfo packInfo : packInfos) {
                    publishProgress(++mAppCount, packInfos.size());
                    final AppInfo appInfo = new AppInfo();
                    Drawable appIcon = packInfo.applicationInfo.loadIcon(pm);
                    appInfo.setAppIcon(appIcon);

                    int flags = packInfo.applicationInfo.flags;

                    int uid = packInfo.applicationInfo.uid;

                    appInfo.setUid(uid);

                    if ((flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        appInfo.setUserApp(false);//系统应用
                    } else {
                        appInfo.setUserApp(true);//用户应用
                    }
                    if ((flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                        appInfo.setInRom(false);
                    } else {
                        appInfo.setInRom(true);
                    }
                    String appName = packInfo.applicationInfo.loadLabel(pm).toString();
                    appInfo.setAppName(appName);
                    String packname = packInfo.packageName;
                    appInfo.setPackname(packname);
                    String version = packInfo.versionName;
                    appInfo.setVersion(version);
                    try {
                        mGetPackageSizeInfoMethod.invoke(mContext.getPackageManager(), new Object[]{
                                packname,
                                new IPackageStatsObserver.Stub() {
                                    @Override
                                    public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
                                        synchronized (appInfo) {
                                            appInfo.setPkgSize(pStats.cacheSize + pStats.codeSize + pStats.dataSize);

                                        }
                                    }
                                }
                        });
                    } catch (Exception e) {
                    }

                    appinfos.add(appInfo);
                }
                return appinfos;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                try {
                    mProgressBarText.setText(getString(R.string.scanning_m_of_n, values[0], values[1]));
                } catch (Exception e) {

                }

            }

            @Override
            protected void onPreExecute() {
                try {
                    showProgressBar(true);
                    mProgressBarText.setText(R.string.scanning);
                } catch (Exception e) {

                }

                //    loading.setVisibility(View.VISIBLE);
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(List<AppInfo> result) {

                super.onPostExecute(result);


                try {

                    showProgressBar(false);

                    userAppInfos = new ArrayList<>();
                    systemAppInfos = new ArrayList<>();
                    long allSize = 0;
                    for (AppInfo a : result) {
                        if (a.isUserApp()) {
                            allSize += a.getPkgSize();
                            userAppInfos.add(a);
                        } else {
                            systemAppInfos.add(a);
                        }
                    }
                    if (position == 0) {
                        topText.setText(getString(R.string.software_top_text, userAppInfos.size(), StorageUtil.convertStorage(allSize)));
                        mAutoStartAdapter = new SoftwareAdapter(mContext, userAppInfos);
                        listview.setAdapter(mAutoStartAdapter);

                    } else {

                        mAutoStartAdapter = new SoftwareAdapter(mContext, systemAppInfos);
                        listview.setAdapter(mAutoStartAdapter);

                    }
                } catch (Exception e) {

                }
            }

        };
        task.execute();


    }


    private boolean isProgressBarVisible() {
        return mProgressBar.getVisibility() == View.VISIBLE;
    }

    private void showProgressBar(boolean show) {
        if (show) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mProgressBar.startAnimation(AnimationUtils.loadAnimation(
                    mContext, android.R.anim.fade_out));
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
