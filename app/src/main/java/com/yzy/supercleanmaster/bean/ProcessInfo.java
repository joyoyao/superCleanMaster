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
package com.yzy.supercleanmaster.bean;



// TODO: Auto-generated Javadoc
/**

 */
public class ProcessInfo{

	/**
	 * The user id of this process.
	 */
	public String uid;
	
	/** The name of the process that this object is associated with. */
	public String processName;

	/** The pid of this process; 0 if none. */
	public int pid;

	/**  占用的内存 B. */
	public long memory;
	
	/**  占用的CPU. */
	public String cpu;
	
	/**  进程的状态，其中S表示休眠，R表示正在运行，Z表示僵死状态，N表示该进程优先值是负数. */
	public String status;
	
	/**  当前使用的线程数. */
	public String threadsCount;
	
	/**
	 * Instantiates a new ab process info.
	 */
	public ProcessInfo() {
		super();
	}

	/**
	 * Instantiates a new ab process info.
	 *
	 * @param processName the process name
	 * @param pid the pid
	 */
	public ProcessInfo(String processName, int pid) {
		super();
		this.processName = processName;
		this.pid = pid;
	}


}
