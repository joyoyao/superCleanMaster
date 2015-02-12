package com.yzy.supercleanmaster.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
/**
 * © 2012 amsoft.cn
 * 名称：AbAlertDialogFragment.java 
 * 描述：弹出框
 *
 * @author 
 * @version v1.0
 * @date：2014-07-29 上午9:00:52
 */
public class AlertDialogFragment extends DialogFragment {
	
	int mIcon;
	String mTitle;
	String mMessage;
	static View mContentView;
	static DialogOnClickListener mOnClickListener;
	
	/**
	 * Create a new instance of AbDialogFragment.
	 */
	public static AlertDialogFragment newInstance(int icon,String title,String message,View view,DialogOnClickListener onClickListener) {
		AlertDialogFragment f = new AlertDialogFragment();
		mOnClickListener = onClickListener;
		mContentView = view;
		
		Bundle args = new Bundle();
		args.putInt("icon", icon);
		args.putString("title", title);
		args.putString("message", message);
		f.setArguments(args);

		return f;
	}
	

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mIcon = getArguments().getInt("icon");
		mTitle = getArguments().getString("title");
		mMessage = getArguments().getString("message");
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),AlertDialog.THEME_HOLO_LIGHT);
		if(mIcon > 0){
			builder.setIcon(mIcon);
		}
		
		if(mTitle != null){
			builder.setTitle(mTitle);
		}
		
		if(mMessage != null){
			builder.setMessage(mMessage);
			
		}
		if(mContentView!=null){
			builder.setView(mContentView);
		}
		
		if(mOnClickListener != null){
			builder.setPositiveButton("确认",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int whichButton) {
	                	if(mOnClickListener != null){
	                		mOnClickListener.onPositiveClick();
	                	}
	                }
	            }
		     );
		     builder.setNegativeButton("取消",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int whichButton) {
	                	if(mOnClickListener != null){
	                		mOnClickListener.onNegativeClick();
	                	}
	                }
	            }
		    );
		}
		
	    return builder.create();
	}
	
	
	/**
     * Dialog事件的接口.
     */
    public interface DialogOnClickListener {
    	
    	public void onPositiveClick();
   	    
     	public void onNegativeClick();
    }
	
}
