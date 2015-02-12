/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yzy.supercleanmaster.dialogs;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.yzy.supercleanmaster.utils.ViewUtil;


public class DialogUtil {

	/** dialog tag */
	private static String mDialogTag = "dialog";
	
	
	
	
	
	/**
	 * 描述：对话框dialog （图标，标题，String内容）.
	 * @param context
	 * @param icon
	 * @param title 对话框标题内容
	 *
	 */
	public static AlertDialogFragment showAlertDialog(Context context,int icon,String title,String message) {
		FragmentActivity activity = (FragmentActivity)context; 
		AlertDialogFragment newFragment = AlertDialogFragment.newInstance(icon,title,message,null,null);
		FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
	    // 指定一个系统转场动画   
	    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  
		newFragment.show(ft, mDialogTag);
	    return newFragment;
	}
	
	/**
	 * 显示一个一般的对话框（图标，标题，string内容，确认，取消）.
	 * @param context
	 * @param icon
	 * @param title 对话框标题内容
	 * @param message 对话框提示内容
	 * @param onClickListener 点击确认按钮的事件监听
	 */
	public static AlertDialogFragment showAlertDialog(Context context,int icon,String title,String message,AlertDialogFragment.DialogOnClickListener onClickListener) {
		FragmentActivity activity = (FragmentActivity)context; 
		AlertDialogFragment newFragment = AlertDialogFragment.newInstance(icon,title,message,null,onClickListener);
		FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  
		newFragment.show(ft, mDialogTag);
	    return newFragment;
	}
	
	
	/**
	 * 显示一个一般的对话框（标题，String内容，确认，取消）.
	 * @param context
	 * @param title 对话框标题内容
	 * @param message 对话框提示内容
	 * @param onClickListener 点击确认按钮的事件监听
	 */
	public static AlertDialogFragment showAlertDialog(Context context,String title,String message,AlertDialogFragment.DialogOnClickListener onClickListener) {
		FragmentActivity activity = (FragmentActivity)context;
		AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0,title,message,null,onClickListener);
		FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  
		newFragment.show(ft, mDialogTag);
	    return newFragment;
	}
	
	/**
	 * 显示一个一般的对话框（View内容）.
	 * @param view 对话框标题内容
	 */
	public static AlertDialogFragment showAlertDialog(View view) {
		FragmentActivity activity = (FragmentActivity)view.getContext();
		AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0,null,null,view,null);
		FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  
		newFragment.show(ft, mDialogTag);
	    return newFragment;
	}
	
	/**
	 * 显示一个一般的对话框（String内容）.
	 * @param context
	 *
	 */
	public static AlertDialogFragment showAlertDialog(Context context,String message) {
		FragmentActivity activity = (FragmentActivity)context; 
		AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0,null,message,null,null);
		FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  
		newFragment.show(ft, mDialogTag);
	    return newFragment;
	}
	
	/**
	 * 描述：对话框dialog （图标，标题，View内容）.
	 * @param icon
	 * @param title 对话框标题内容
	 * @param view  对话框提示内容
	 */
	public static AlertDialogFragment showAlertDialog(int icon,String title,View view) {
		FragmentActivity activity = (FragmentActivity)view.getContext();
		AlertDialogFragment newFragment = AlertDialogFragment.newInstance(icon,title,null,view,null);
		FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  
		newFragment.show(ft, mDialogTag);
	    return newFragment;
	}
	
	/**
	 * 显示一个一般的对话框（图标，标题，View内容，确认，取消）.
	 * @param icon
	 * @param title 对话框标题内容
	 * @param view 对话框提示内容
	 * @param onClickListener 点击确认按钮的事件监听
	 */
	public static AlertDialogFragment showAlertDialog(int icon,String title,View view,AlertDialogFragment.DialogOnClickListener onClickListener) {
		FragmentActivity activity = (FragmentActivity)view.getContext();
		AlertDialogFragment newFragment = AlertDialogFragment.newInstance(icon,title,null,view,onClickListener);
		FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  
		newFragment.show(ft, mDialogTag);
	    return newFragment;
	}
	
	/**
	 * 描述：对话框dialog （标题，View内容）.
	 * @param title 对话框标题内容
	 * @param view  对话框提示内容
	 */
	public static AlertDialogFragment showAlertDialog(String title,View view) {
		FragmentActivity activity = (FragmentActivity)view.getContext();
		AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0,title,null,view,null);
		FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  
		newFragment.show(ft, mDialogTag);
	    return newFragment;
	}
	
	/**
	 * 显示一个一般的对话框（标题，View内容，确认，取消）.
	 * @param title 对话框标题内容
	 * @param view 对话框提示内容
	 * @param onClickListener 点击确认按钮的事件监听
	 */
	public static AlertDialogFragment showAlertDialog(String title,View view,AlertDialogFragment.DialogOnClickListener onClickListener) {
		FragmentActivity activity = (FragmentActivity)view.getContext(); 
		AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0,title,null,view,onClickListener);
		FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  
		newFragment.show(ft, mDialogTag);
	    return newFragment;
	}
	
	/**
	 * 描述：对话框dialog （标题，String内容）.
	 * @param context
	 * @param title 对话框标题内容
	 *
	 */
	public static AlertDialogFragment showAlertDialog(Context context,String title,String message) {
		FragmentActivity activity = (FragmentActivity)context; 
		AlertDialogFragment newFragment = AlertDialogFragment.newInstance(0,title,message,null,null);
		FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画   
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);  
		newFragment.show(ft, mDialogTag);
	    return newFragment;
	}
	

	/**
	 * 描述：显示进度框.
	 * 
	 * @param context
	 *            the context
	 * @param indeterminateDrawable
	 *            用默认请写0
	 * @param message
	 *            the message
	 */
	public static ProgressDialogFragment showProgressDialog(Context context,
			int indeterminateDrawable, String message) {
		FragmentActivity activity = (FragmentActivity) context;
		ProgressDialogFragment newFragment = ProgressDialogFragment
				.newInstance(indeterminateDrawable, message);
		FragmentTransaction ft = activity.getFragmentManager()
				.beginTransaction();
		// 指定一个系统转场动画
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		newFragment.show(ft, mDialogTag);
		return newFragment;
	}
	
	


	/**
	 * 描述：移除Fragment.
	 * 
	 * @param context
	 *            the context
	 */
	public static void removeDialog(Context context) {
		try {
			FragmentActivity activity = (FragmentActivity) context;
			FragmentTransaction ft = activity.getFragmentManager()
					.beginTransaction();
			// 指定一个系统转场动画
			ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
			Fragment prev = activity.getFragmentManager().findFragmentByTag(
					mDialogTag);
			if (prev != null) {
				ft.remove(prev);
			}
			ft.addToBackStack(null);
			ft.commit();
		} catch (Exception e) {
			// 可能有Activity已经被销毁的异常
			e.printStackTrace();
		}
	}

	/**
	 * 描述：移除Fragment和View
	 * 
	 * @param view
	 */
	public static void removeDialog(View view) {
		removeDialog(view.getContext());
		ViewUtil.removeSelfFromParent(view);
	}

}
