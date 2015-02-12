package com.yzy.supercleanmaster.engine;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.RemoteException;

import com.yzy.supercleanmaster.model.CacheInfo;
import com.yzy.supercleanmaster.utils.TextFormater;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;


public class CacheInfoProvider
{
	private Handler handler;
	private PackageManager packageManager;
	private Vector<CacheInfo> cacheInfos;
	private int size = 0;

	public CacheInfoProvider(Handler handler, Context context)
	{
		// 拿到一个包管理器
		packageManager = context.getPackageManager();
		this.handler = handler;
		cacheInfos = new Vector<CacheInfo>();
	}

	public void initCacheInfos()
	{
		// 获取到所有安装了的应用程序的信息，包括那些卸载了的，但没有清除数据的应用程序
		List<PackageInfo> packageInfos = packageManager
				.getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
		size = packageInfos.size();
		for (int i = 0; i < size; i++)
		{
			PackageInfo packageInfo = packageInfos.get(i);
			CacheInfo cacheInfo = new CacheInfo();
			// 拿到包名
			String packageName = packageInfo.packageName;
			cacheInfo.setPackageName(packageName);
			// 拿到应用程序的信息
			ApplicationInfo applicationInfo = packageInfo.applicationInfo;
			// 拿到应用程序的程序名
			String name = applicationInfo.loadLabel(packageManager).toString();
			cacheInfo.setName(name);
			// 拿到应用程序的图标
			Drawable icon = applicationInfo.loadIcon(packageManager);
			cacheInfo.setIcon(icon);

			initDataSize(cacheInfo, i);
		}
	}

	/**
	 * 通过AIDL的方法来获取到应用的缓存信息，getPackageSizeInfo是PackageManager里面的一个私有方法来的
	 * 我们通过反射就可以调用到它的了，但是这个方法里面会传递一个IPackageStatsObserver.Stub的对象
	 * 里面就可能通过AIDL来获取我们想要的信息了
	 * 
	 * 因为这样的调用是异步的，所以当我们完成获取完这些信息之后，我们就通过handler来发送一个消息
	 * 来通知我们的应用，通过getCacheInfos来获取到我们的Vector
	 * 
	 * 为什么要用Vector呢，因为下面的方法是异步的，也就是有可能是多线程操作，所以我们就用了线程安全的Vector
	 * 
	 * @param cacheInfo
	 * @param position
	 */
	private void initDataSize(final CacheInfo cacheInfo, final int position)
	{
		try
		{
			Method method = PackageManager.class.getMethod(
					"getPackageSizeInfo", new Class[] { String.class,
							IPackageStatsObserver.class });
			method.invoke(packageManager,
					new Object[] { cacheInfo.getPackageName(),
							new IPackageStatsObserver.Stub()
							{
								@Override
								public void onGetStatsCompleted(
										PackageStats pStats, boolean succeeded)
										throws RemoteException
								{
									System.out.println("onGetStatsCompleted" + position);
									long cacheSize = pStats.cacheSize;
									long codeSize = pStats.codeSize;
									long dataSize = pStats.dataSize;

									cacheInfo.setCacheSize(TextFormater
											.dataSizeFormat(cacheSize));
									cacheInfo.setCodeSize(TextFormater
											.dataSizeFormat(codeSize));
									cacheInfo.setDataSize(TextFormater
											.dataSizeFormat(dataSize));

									cacheInfos.add(cacheInfo);

									if (position == (size - 1))
									{
										// 当完全获取完信息之后，发送一个成功的消息
										// 1对应的就是CacheClearActivity里面的FINISH
										handler.sendEmptyMessage(1);
									}
								}
							} });
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public Vector<CacheInfo> getCacheInfos()
	{
		return cacheInfos;
	}

	public void setCacheInfos(Vector<CacheInfo> cacheInfos)
	{
		this.cacheInfos = cacheInfos;
	}

}
