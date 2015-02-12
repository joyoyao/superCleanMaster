package com.yzy.supercleanmaster.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.adapter.AutoStartAdapter;
import com.yzy.supercleanmaster.base.BaseFragment;
import com.yzy.supercleanmaster.model.AutoStartInfo;
import com.yzy.supercleanmaster.utils.BootStartUtils;
import com.yzy.supercleanmaster.utils.RootUtil;
import com.yzy.supercleanmaster.utils.ShellUtils;
import com.yzy.supercleanmaster.utils.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class AutoStartFragment extends BaseFragment {


    Context mContext;
    public static final int REFRESH_BT = 111;
    private static final String ARG_POSITION = "position";
    private int position; // 0:普通软件，2 系统软件
    AutoStartAdapter mAutoStartAdapter;

    @InjectView(R.id.listview)
    ListView listview;

    @InjectView(R.id.bottom_lin)
    LinearLayout bottom_lin;

    @InjectView(R.id.disable_button)
    Button disableButton;
    @InjectView(R.id.topText)
    TextView topText;
    List<AutoStartInfo> isSystemAuto = null;
    List<AutoStartInfo> noSystemAuto = null;
    private int canDisableCom;


    private Handler mHandler = new Handler() {


        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_BT:
                    refeshButoom();

                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt(ARG_POSITION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.fragment_auto_start, container, false);
        ButterKnife.inject(this, view);
        mContext = getActivity();

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fillData();
    }

    @OnClick(R.id.disable_button)
    public void onClickDisable() {
      RootUtil.preparezlsu(mContext);
       disableAPP();

    }

    private void disableAPP() {
        List<String> mSring = new ArrayList<>();
        for (AutoStartInfo auto : noSystemAuto) {
            if (auto.isEnable()) {
                String packageReceiverList[] = auto.getPackageReceiver().toString().split(";");
                for (int j = 0; j < packageReceiverList.length; j++) {
                    String cmd = "pm disable " + packageReceiverList[j];
                    //部分receiver包含$符号，需要做进一步处理，用"$"替换掉$
                    cmd = cmd.replace("$", "\"" + "$" + "\"");
                    //执行命令
                    mSring.add(cmd);
                }
            }
        }

        ShellUtils.CommandResult mCommandResult = ShellUtils.execCommand(mSring, true, true);
        if (mCommandResult.result == 0) {
            T.showLong(mContext, "应用已经全部禁止");
            for (AutoStartInfo auto : noSystemAuto) {
                if (auto.isEnable()) {
                    auto.setEnable(false);
                }
            }
            mAutoStartAdapter.notifyDataSetChanged();
            refeshButoom();
        } else {
            T.showLong(mContext, "该功能需要获取系统root权限，请允许获取root权限");
        }
    }


    private void fillData() {

        if (position == 0) {
            topText.setText("禁止下列软件自启,可提升运行速度");

        } else {
            topText.setText("禁止系统核心软件自启,将会影响手机的正常使用,请谨慎操作");

        }

        List<AutoStartInfo> mAutoStartInfo = BootStartUtils.fetchAutoApps(mContext);

        //   List<AutoStartInfo> mAutoStartInfo = BootStartUtils.fetchInstalledApps(mContext);
        noSystemAuto = new ArrayList<>();
        isSystemAuto = new ArrayList<>();

        for (AutoStartInfo a : mAutoStartInfo) {
            if (a.isSystem()) {

                isSystemAuto.add(a);
            } else {
                noSystemAuto.add(a);
            }
        }

        if (position == 0) {
            mAutoStartAdapter = new AutoStartAdapter(mContext, noSystemAuto, mHandler);
            listview.setAdapter(mAutoStartAdapter);
            refeshButoom();
        } else {

            mAutoStartAdapter = new AutoStartAdapter(mContext, isSystemAuto, null);
            listview.setAdapter(mAutoStartAdapter);

        }


    }

    private void refeshButoom() {
        if (position == 0) {
            canDisableCom = 0;
            for (AutoStartInfo autoS : noSystemAuto) {
                if (autoS.isEnable()) {
                    canDisableCom++;
                }
            }
            if (canDisableCom > 0) {
                bottom_lin.setVisibility(View.VISIBLE);
                disableButton.setText("可优化" + canDisableCom + "款");
            } else {
                bottom_lin.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
