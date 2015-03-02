package com.yzy.supercleanmaster.base;

import android.content.Context;

import java.lang.Thread.UncaughtExceptionHandler;

public class MyCrashHandler implements UncaughtExceptionHandler {
	// 保证MyCrashHandler只有一个实例
	// 2.提供一个静态的程序变量
	private static MyCrashHandler myCrashHandler;
	private Context context;

	// 1.私有化构造方法
	private MyCrashHandler() {

	}

	// 3.暴露出来一个静态的方法 获取myCrashHandler

	public static synchronized MyCrashHandler getInstance() {
		if (myCrashHandler == null) {
			myCrashHandler = new MyCrashHandler();
		}
		return myCrashHandler;
	}

	public void init(Context context) {
		this.context = context;
	}

	// 程序发生异常的时候调用的方法
	// try catch

	public void uncaughtException(Thread thread, Throwable ex) {
	//	System.out.println("出现错误啦 哈哈");
		ex.printStackTrace();
//		StringBuilder sb = new StringBuilder();
//		// 1.获取当前应用程序的版本号.
//		PackageManager pm = context.getPackageManager();
//		try {
//			PackageInfo packinfo = pm.getPackageInfo(context.getPackageName(),
//					0);
//			sb.append("程序的版本号为" + packinfo.versionName);
//			sb.append("\n");
//
//			// 2.获取手机的硬件信息.
//			Field[] fields = Build.class.getDeclaredFields();
//			for (int i = 0; i < fields.length; i++) {
//				// 暴力反射,获取私有的字段信息
//				fields[i].setAccessible(true);
//				String name = fields[i].getName();
//				sb.append(name + " = ");
//				String value = fields[i].get(null).toString();
//				sb.append(value);
//				sb.append("\n");
//			}
//			// 3.获取程序错误的堆栈信息 .
//			StringWriter writer = new StringWriter();
//			PrintWriter printWriter = new PrintWriter(writer);
//			ex.printStackTrace(printWriter);
//			
//		    String result =	writer.toString();
//		    sb.append(result);
//		    String time =  new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
//		    String name ="xizi"+time+".txt";
//		    String fileName=Environment.getExternalStorageDirectory()+File.separator+"xz_app"+File.separator+"log";
//		    
//		    File file1 =new File(fileName);
//		    if(!file1.exists()){
//		        file1.mkdir();
//		    }
//		    File file=new File(fileName,name);
//		    if(!file.exists()){
//                file.createNewFile();
//            }
//		//    File file = new File(Environment.getExternalStorageDirectory(),"error.log");
//            FileOutputStream fos = new FileOutputStream(file);
//            fos.write(sb.toString().getBytes());
//            fos.close();
//		   
//
//			// 4.把错误信息 提交到服务器
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// 完成自杀的操作
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
