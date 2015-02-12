package com.yzy.supercleanmaster.utils;

public class Constants {

    /**
     * 第一次获取root权限后，push 到 /system/bin/ 目录下的 二进制可执行程序 的名称
     */
	public static final String ROOT_SU = "zlsu";
    /**
     * 调用 {@link #ROOT_SU}命令时传入的returnKey 返回值是通过该returnKey来获取
     */
	public static final String RETURNCODE_MOVE_PACKAGE = "returnCode_move_package:";
	public static final String RETURN_CODE_ERROR = "return_code_error";

 
}
