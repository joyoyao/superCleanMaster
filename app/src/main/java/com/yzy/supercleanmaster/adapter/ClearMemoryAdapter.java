package com.yzy.supercleanmaster.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.bean.AppProcessInfo;
import com.yzy.supercleanmaster.utils.StorageUtil;

import java.util.ArrayList;
import java.util.List;

public class ClearMemoryAdapter extends BaseAdapter {

    public List<AppProcessInfo> mlistAppInfo;
    LayoutInflater infater = null;
    private Context mContext;
    public static List<Integer> clearIds;

    public ClearMemoryAdapter(Context context, List<AppProcessInfo> apps) {
        infater = LayoutInflater.from(context);
        mContext = context;
        clearIds = new ArrayList<Integer>();
        this.mlistAppInfo = apps;
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
            convertView = infater.inflate(R.layout.listview_memory_clean,
                    null);
            holder = new ViewHolder();
            holder.appIcon = (ImageView) convertView
                    .findViewById(R.id.image);
            holder.appName = (TextView) convertView
                    .findViewById(R.id.name);
            holder.memory = (TextView) convertView
                    .findViewById(R.id.memory);

            holder.cb = (RadioButton) convertView
                    .findViewById(R.id.choice_radio);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final AppProcessInfo appInfo = (AppProcessInfo) getItem(position);
        holder.appIcon.setImageDrawable(appInfo.icon);
        holder.appName.setText(appInfo.appName);
        holder.memory.setText(StorageUtil.convertStorage(appInfo.memory));
        if (appInfo.checked) {
            holder.cb.setChecked(true);
        } else {
            holder.cb.setChecked(false);
        }
        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appInfo.checked) {
                    appInfo.checked = false;
                } else {
                    appInfo.checked = true;
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    class ViewHolder {
        ImageView appIcon;
        TextView appName;
        TextView memory;
        TextView tvProcessMemSize;
        RelativeLayout cb_rl;
        RadioButton cb;

        public RadioButton getCb() {
            return cb;
        }

        public void setCb(RadioButton cb) {
            this.cb = cb;
        }
    }

}
