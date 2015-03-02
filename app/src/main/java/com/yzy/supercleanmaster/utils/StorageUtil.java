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
package com.yzy.supercleanmaster.utils;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import com.yzy.supercleanmaster.model.SDCardInfo;
import com.yzy.supercleanmaster.model.StorageSize;

import java.io.File;


// TODO: Auto-generated Javadoc

public class StorageUtil {

    // storage, G M K B
    public static String convertStorage(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;

        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else
            return String.format("%d B", size);
    }

    public static StorageSize convertStorageSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        StorageSize sto = new StorageSize();
        if (size >= gb) {

            sto.suffix = "GB";
            sto.value = (float) size / gb;
            return sto;
        } else if (size >= mb) {

            sto.suffix = "MB";
            sto.value = (float) size / mb;

            return sto;
        } else if (size >= kb) {


            sto.suffix = "KB";
            sto.value = (float) size / kb;

            return sto;
        } else {
            sto.suffix = "B";
            sto.value = (float) size;

            return sto;
        }


    }

    public static SDCardInfo getSDCardInfo() {
        // String sDcString = Environment.getExternalStorageState();

        if (Environment.isExternalStorageRemovable()) {
            String sDcString = Environment.getExternalStorageState();
            if (sDcString.equals(Environment.MEDIA_MOUNTED)) {
                File pathFile = Environment
                        .getExternalStorageDirectory();

                try {
                    StatFs statfs = new StatFs(
                            pathFile.getPath());

                    // 获取SDCard上BLOCK总数
                    long nTotalBlocks = statfs.getBlockCount();

                    // 获取SDCard上每个block的SIZE
                    long nBlocSize = statfs.getBlockSize();

                    // 获取可供程序使用的Block的数量
                    long nAvailaBlock = statfs.getAvailableBlocks();

                    // 获取剩下的所有Block的数量(包括预留的一般程序无法使用的块)
                    long nFreeBlock = statfs.getFreeBlocks();

                    SDCardInfo info = new SDCardInfo();
                    // 计算SDCard 总容量大小MB
                    info.total = nTotalBlocks * nBlocSize;

                    // 计算 SDCard 剩余大小MB
                    info.free = nAvailaBlock * nBlocSize;

                    return info;
                } catch (IllegalArgumentException e) {

                }
            }
        }
        return null;
    }

    public static SDCardInfo getSystemSpaceInfo(Context context) {
        File path = Environment.getDataDirectory();
        // File path = context.getCacheDir().getAbsoluteFile();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        long availableBlocks = stat.getAvailableBlocks();

        long totalSize = blockSize * totalBlocks;
        long availSize = availableBlocks * blockSize;
        SDCardInfo info = new SDCardInfo();
        info.total = totalSize;
        info.free = availSize;
        return info;


    }

    public static SDCardInfo getRootSpaceInfo() {
        File path = Environment.getRootDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        long availableBlocks = stat.getAvailableBlocks();

        long totalSize = blockSize * totalBlocks;
        long availSize = availableBlocks * blockSize;
        // 获取SDCard上每个block的SIZE
        long nBlocSize = stat.getBlockSize();

        SDCardInfo info = new SDCardInfo();
        // 计算SDCard 总容量大小MB
        info.total = totalSize;

        // 计算 SDCard 剩余大小MB
        info.free = availSize;
        return info;

    }
}
