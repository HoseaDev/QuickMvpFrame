package com.youkemm.kcommon.bean

import android.graphics.drawable.Drawable

/**
 * Created by Harry
 * on 2019/6/12 10:54
 */
data class AppInfo(
    val apkPath: String,
    val packageName: String,
    val versionName: String,
    val versionCode: Long,
    val appName: String,
    val icon: Drawable
)