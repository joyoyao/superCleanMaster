package com.balaganovrocks.yourmasterclean.fragment;

import android.app.ActivityManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.balaganovrocks.yourmasterclean.base.BaseFragment;
import com.balaganovrocks.yourmasterclean.ui.MainActivity;
import com.balaganovrocks.yourmasterclean.ui.MemoryCleanActivity;
import com.balaganovrocks.yourmasterclean.ui.RubbishCleanActivity;
import com.balaganovrocks.yourmasterclean.ui.SoftwareManageActivity;
import com.umeng.update.UmengUpdateAgent;
import com.balaganovrocks.yourmasterclean.R;
import com.balaganovrocks.yourmasterclean.base.BaseFragment;
import com.balaganovrocks.yourmasterclean.model.SDCardInfo;
import com.balaganovrocks.yourmasterclean.ui.AutoStartManageActivity;
import com.balaganovrocks.yourmasterclean.ui.BatterySavingActivity;
import com.balaganovrocks.yourmasterclean.ui.MainActivity;
import com.balaganovrocks.yourmasterclean.ui.MemoryCleanActivity;
import com.balaganovrocks.yourmasterclean.ui.RubbishCleanActivity;
import com.balaganovrocks.yourmasterclean.ui.ShortCutActivity;
import com.balaganovrocks.yourmasterclean.ui.SoftwareManageActivity;
import com.balaganovrocks.yourmasterclean.ui.settings;
import com.balaganovrocks.yourmasterclean.utils.AppUtil;
import com.balaganovrocks.yourmasterclean.utils.StorageUtil;
import com.balaganovrocks.yourmasterclean.widget.circleprogress.ArcProgress;
import com.balaganovrocks.yourmasterclean.ui.Notifications;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.tencent.open.utils.Global.getPackageName;


public class MainFragment extends BaseFragment {

    @InjectView(R.id.arc_store)
    ArcProgress arcStore;

    @InjectView(R.id.arc_process)
    ArcProgress arcProcess;
    @InjectView(R.id.capacity)
    TextView capacity;
    Button Rubish_clean;
    Context mContext;
    Button BtnSettigs;
    private Timer timer;
    private Timer timer2;
    Button CleanRubbish;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        Rubish_clean = (Button) view.findViewById(R.id.Rubish_clean);
        ButterKnife.inject(this, view);
        mContext = getActivity();
        BtnSettigs = (Button) view.findViewById(R.id.btnSettings);
        BtnSettigs.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), settings.class);
                startActivityForResult(intent, 0); // do something
            }
        });
        Rubish_clean.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), RubbishCleanActivity.class);
                startActivityForResult(intent, 0); // do something
            }
        });
        return view;


    }

//  public void notification(){
//      Intent i = new Intent(getContext(), MainActivity.class);
//      PendingIntent rootPendingIntent = PendingIntent.getBroadcast(getContext(), 0, i, 0);
//      RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification);
//      remoteViews.setTextViewText(R.id.textView, "Custom notification text");
//      remoteViews.setOnClickPendingIntent(R.id.root, rootPendingIntent);

//      NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext())
//              .setSmallIcon(R.drawable.ic_launcher)
//              .setCustomContentView(remoteViews)
//              .setStyle(new NotificationCompat.DecoratedCustomViewStyle());
//  }
    @Override
    public void onResume() {
        super.onResume();
        fillData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        UmengUpdateAgent.update(getActivity());
    }

    private void fillData() {
        // TODO Auto-generated method stub
        timer = null;
        timer2 = null;
        timer = new Timer();
        timer2 = new Timer();


        long l = AppUtil.getAvailMemory(mContext);
        long y = AppUtil.getTotalMemory(mContext);
        final double x = (((y - l) / (double) y) * 100);
        //   arcProcess.setProgress((int) x);

        arcProcess.setProgress(0);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        if (arcProcess.getProgress() >= (int) x) {
                            timer.cancel();
                        } else {
                            arcProcess.setProgress(arcProcess.getProgress() + 1);
                        }

                    }
                });
            }
        }, 50, 20);

        SDCardInfo mSDCardInfo = StorageUtil.getSDCardInfo();
        SDCardInfo mSystemInfo = StorageUtil.getSystemSpaceInfo(mContext);

        long nAvailaBlock;
        long TotalBlocks;
        if (mSDCardInfo != null) {
            nAvailaBlock = mSDCardInfo.free + mSystemInfo.free;
            TotalBlocks = mSDCardInfo.total + mSystemInfo.total;
        } else {
            nAvailaBlock = mSystemInfo.free;
            TotalBlocks = mSystemInfo.total;
        }

        final double percentStore = (((TotalBlocks - nAvailaBlock) / (double) TotalBlocks) * 100);

        capacity.setText(StorageUtil.convertStorage(TotalBlocks - nAvailaBlock) + "/" + StorageUtil.convertStorage(TotalBlocks));
        arcStore.setProgress(0);

        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        if (arcStore.getProgress() >= (int) percentStore) {
                            timer2.cancel();
                        } else {
                            arcStore.setProgress(arcStore.getProgress() + 1);
                        }

                    }
                });
            }
        }, 50, 20);


    }

    @OnClick(R.id.card1)
    void speedUp() {
       // notifications();
        startActivity(MemoryCleanActivity.class);

    }


    @OnClick(R.id.card2)
    void rubbishClean() {
       // notifications();

        startActivity(RubbishCleanActivity.class);

    }

    @OnClick(R.id.card3)
    void batterySaving() {
       // notifications();
        startActivity(BatterySavingActivity.class);
        Log.d("proc", "save buttery init");
        amKillProcess("com.balaganovrocks.yourmasterclean");


    }


    @OnClick(R.id.card4)
    void SoftwareManage() {
       // notifications();
        startActivity(SoftwareManageActivity.class);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public void amKillProcess(String process) {
        ActivityManager am = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        Log.d("proc", "activity init");
        final List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
        Log.d("proc", "" + runningProcesses.size());
        for (ActivityManager.RunningAppProcessInfo runningProcess : runningProcesses) {
            if (!runningProcess.processName.equals(process)) {
                Log.d("proc", runningProcess.processName);
                android.os.Process.sendSignal(runningProcess.pid, android.os.Process.SIGNAL_KILL);
            }
        }
    }

    @Override
    public void onDestroy() {
        timer.cancel();
        timer2.cancel();
        super.onDestroy();
    }
}
