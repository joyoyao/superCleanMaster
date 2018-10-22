package com.balaganovrocks.yourmasterclean.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.balaganovrocks.yourmasterclean.fragment.AutoStartFragment;
import com.balaganovrocks.yourmasterclean.R;
import com.balaganovrocks.yourmasterclean.model.AutoStartInfo;
import com.balaganovrocks.yourmasterclean.utils.ShellUtils;
import com.balaganovrocks.yourmasterclean.utils.T;

import java.util.ArrayList;
import java.util.List;

public class AutoStartAdapter extends BaseAdapter {

    public List<AutoStartInfo> mlistAppInfo;
    LayoutInflater infater = null;
    private Context mContext;
    public static List<Integer> clearIds;
    private Handler mHandler;

    public AutoStartAdapter(Context context, List<AutoStartInfo> apps, Handler mHandler) {
        infater = LayoutInflater.from(context);
        mContext = context;
        clearIds = new ArrayList<Integer>();
        this.mlistAppInfo = apps;
        this.mHandler = mHandler;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mlistAppInfo.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mlistAppInfo.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = infater.inflate(R.layout.listview_auto_start,
                    null);
            holder = new ViewHolder();
            holder.appIcon = (ImageView) convertView
                    .findViewById(R.id.app_icon);
            holder.appName = (TextView) convertView
                    .findViewById(R.id.app_name);
            holder.size = (TextView) convertView
                    .findViewById(R.id.app_size);
            holder.disable_switch = (TextView) convertView
                    .findViewById(R.id.disable_switch);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AutoStartInfo item = (AutoStartInfo) getItem(position);
        if (item != null) {
            holder.appIcon.setImageDrawable(item.getIcon());
            holder.appName.setText(item.getLabel());
            if (item.isEnable()) {
                holder.disable_switch.setBackgroundResource(R.drawable.switch_on);
                holder.disable_switch.setText("Оказалось");
            } else {
                holder.disable_switch.setBackgroundResource(R.drawable.switch_off);
                holder.disable_switch.setText("Это было запрещено");
            }
            // holder.size.setText(Formatter.formatShortFileSize(mContext, item.getCacheSize()));

            holder.disable_switch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (ShellUtils.checkRootPermission()) {

                        if (item.isEnable()) {


                            diasableApp(item);
                        } else {

                            enableApp(item);
                        }

                    } else {

                        T.showLong(mContext, "Эта функция должна получить разрешение системного корня, щелкнуть, чтобы разрешить доступ к привилегии root");

                    }

                }
            });
            holder.packageName = item.getPackageName();
        }


        return convertView;
    }

    private void diasableApp(AutoStartInfo item) {
        String packageReceiverList[] = item.getPackageReceiver().toString().split(";");

        List<String> mSring = new ArrayList<>();
        for (int j = 0; j < packageReceiverList.length; j++) {
            String cmd = "pm disable " + packageReceiverList[j];
            //部分receiver包含$符号，需要做进一步处理，用"$"替换掉$
            cmd = cmd.replace("$", "\"" + "$" + "\"");
            //执行命令
            mSring.add(cmd);

        }
        ShellUtils.CommandResult mCommandResult = ShellUtils.execCommand(mSring, true, true);

        if (mCommandResult.result == 0) {
            T.showLong(mContext, item.getLabel() + "Это было запрещено");
            item.setEnable(false);
            notifyDataSetChanged();
            if (mHandler != null) {
                mHandler.sendEmptyMessage(AutoStartFragment.REFRESH_BT);
            }
        } else {
            T.showLong(mContext, item.getLabel() + "Запрет отказа");
        }

        // T.showLong(mContext, mCommandResult.result + "" + mCommandResult.errorMsg + mCommandResult.successMsg);
    }

    private void enableApp(AutoStartInfo item) {
        String packageReceiverList[] = item.getPackageReceiver().toString().split(";");

        List<String> mSring = new ArrayList<>();
        for (int j = 0; j < packageReceiverList.length; j++) {
            String cmd = "pm enable " + packageReceiverList[j];
            //部分receiver包含$符号，需要做进一步处理，用"$"替换掉$
            cmd = cmd.replace("$", "\"" + "$" + "\"");
            //执行命令
            mSring.add(cmd);

        }
        ShellUtils.CommandResult mCommandResult = ShellUtils.execCommand(mSring, true, true);

        if (mCommandResult.result == 0) {
            T.showLong(mContext, item.getLabel() + "Оказалось");
            item.setEnable(true);
            notifyDataSetChanged();
            if (mHandler != null) {
                mHandler.sendEmptyMessage(AutoStartFragment.REFRESH_BT);
            }
        } else {
            T.showLong(mContext, item.getLabel() + "Не удалось открыть");
        }
        //   T.showLong(mContext, mCommandResult.result + "" + mCommandResult.errorMsg + mCommandResult.successMsg);
    }

    class ViewHolder {
        ImageView appIcon;
        TextView appName;
        TextView size;
        TextView disable_switch;

        String packageName;
    }

}
