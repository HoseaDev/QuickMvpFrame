package com.youkemm.kcommonproject.app

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.multidex.MultiDex
import com.youkemm.kcommon.entity.net.IApiException
import com.youkemm.kcommon.facade.CommonLibrary
import com.youkemm.kcommonproject.BuildConfig
import com.youkemm.kcommonproject.common.http.ApiService
import com.youkemm.kcommonproject.common.http.CacheService
import com.youkemm.kcommonproject.common.util.AppBlockCanaryContext
import com.blankj.utilcode.util.SPUtils
import com.squareup.leakcanary.LeakCanary
import com.github.moduth.blockcanary.BlockCanary


/**
 * Created by harry on 2020/6/20.
 */
class App : Application() {
    companion object {
        //跳转到登录页面，需要传入context和登录页面Activity的Class对象
        fun startLoginActivity(context: Context, loginClazz: Class<*>) {

            //重置网络请求头，很好理解，退出登录了肯定要清空之前的请求头
            CommonLibrary.instance.headerMap = hashMapOf(
                    "token" to SPUtils.getInstance("KCommonDemo").getString("token", "123"))

            //跳转到登录页面并清空之前的任务栈
            context.startActivity(
                    Intent(
                            context,
                            loginClazz).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
        }
    }

    //Multidex
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        //初始化LeakCanary，用于测试内存泄漏
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this)

        //初始化BlockCanary，用于测试UI卡顿
        BlockCanary.install(this, AppBlockCanaryContext()).start()


        //初始化CommonLibrary
        CommonLibrary.instance.initLibrary(this,
                BuildConfig.APP_URL,
                ApiService::class.java,
                CacheService::class.java,
                spName = "KCommonDemo",
                errorHandleMap = hashMapOf<Int, (exception: IApiException) -> Unit>(401 to { exception ->

                }, 402 to { exception ->

                }, 403 to { exception ->

                }),
                isDebug = BuildConfig.DEBUG)
    }
}