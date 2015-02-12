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
public class CacheInfo {
    private String name;
    private String packageName;
    private Drawable icon;
    //应用大小
    private String codeSize;
    //数据大小
    private String dataSize;
    //缓存大小
    private String cacheSize;

    public String getName() {
        return name;
    }

    public String getDataSize() {
        return dataSize;
    }

    public String getCacheSize() {
        return cacheSize;
    }

    public String getCodeSize() {
        return codeSize;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setCacheSize(String cacheSize) {
        this.cacheSize = cacheSize;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize;
    }

    public void setCodeSize(String codeSize) {
        this.codeSize = codeSize;
    }

    public String getPackageName() {
        return packageName;
    }
}
