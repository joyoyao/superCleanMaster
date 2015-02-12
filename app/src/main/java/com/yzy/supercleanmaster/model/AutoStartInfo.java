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
package com.yzy.supercleanmaster.model;

// TODO: Auto-generated Javadoc

import android.graphics.drawable.Drawable;

/**

 */
public class AutoStartInfo {
    private String label;
    private String packageName;
    private Drawable icon;
    private String name;

    private String packageReceiver;
    private String desc;
    /**  是否是系统进程. */
    public boolean isSystem;
    public boolean isenable;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSystem() {
        return isSystem;
    }

    public void setSystem(boolean isSystem) {
        this.isSystem = isSystem;
    }

    public boolean isEnable() {
        return isenable;
    }

    public void setEnable(boolean enable) {
        this.isenable = enable;
    }

    public String getPackageReceiver() {
        return packageReceiver;
    }

    public void setPackageReceiver(String packageReceiver) {
        this.packageReceiver = packageReceiver;
    }
}
