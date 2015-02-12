package com.yzy.supercleanmaster.base;

import android.app.Activity;
import android.content.Context;

import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * activity 栈管理
 * @author duohuo-jinghao
 *
 */
public class ActivityTack {
	
	
	public 	 List<Activity> activityList=new ArrayList<Activity>();
	
	public static ActivityTack tack=new ActivityTack();
	
	public static  ActivityTack getInstanse(){
		return tack;
	}
	
	private ActivityTack(){
		
	}
	
	public  void addActivity(Activity activity){
		activityList.add(activity);
	}
	
	public  void removeActivity(Activity activity){
		activityList.remove(activity);
	}
	
	/**
	 * 完全退出
	 * @param context
	 */
	public  void exit(Context context){

        MobclickAgent.onKillProcess(context);
		while (activityList.size()>0) {
			activityList.get(activityList.size()-1).finish();
		}
	    System.exit(0);
	}
	
	
	/**
	 * 根据class name获取activity
	 * @param name
	 * @return
	 */
	public Activity getActivityByClassName(String name){
		for(Activity ac:activityList){
			if(ac.getClass().getName().indexOf(name)>=0)
			{
		      	return ac;	
			}
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public Activity getActivityByClass(Class cs){
		for(Activity ac:activityList){
			if(ac.getClass().equals(cs))
			{
		      	return ac;	
			}
		}
		return null;
	}
	
	/**
	 * 弹出activity
	 * @param activity
	 */
	public void popActivity(Activity activity){
		removeActivity(activity);
		activity.finish();
	}
	
	
	/**
	 * 弹出activity到
	 * @param cs
	 */
	@SuppressWarnings("rawtypes")
	public void popUntilActivity(Class... cs){
		List<Activity> list=new ArrayList<Activity>();
		 for (int i = activityList.size()-1; i>=0; i--){
			 Activity ac= activityList.get(i);
			 boolean isTop=false;
			 for (int j = 0; j < cs.length; j++) {
				 if(ac.getClass().equals(cs[j])){
					 isTop=true;
					 break;
				 }
			}
			 if(!isTop){
				 list.add(ac);
			 }else break;
		}
		 for (Iterator<Activity> iterator = list.iterator(); iterator.hasNext();) {
				Activity activity = iterator.next();
				popActivity(activity);
		  }
	}
}
