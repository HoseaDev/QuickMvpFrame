package com.youkemm.kcommon.ktx.core.lifecycle

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.youkemm.kcommon.facade.CommonLibrary
import com.youkemm.kcommon.ktx.core.ext.loge


/**
 * Created by Harry
 * on 2019/8/6 10:45
 */
class KtxLifeCycleCallBack : Application.ActivityLifecycleCallbacks {


    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        KtxManager.pushActivity(activity)
        CommonLibrary.instance.onPageCreateListener?.onPageCreate(activity)
        "onActivityCreated : ${activity.localClassName}".loge()
    }

    override fun onActivityStarted(activity: Activity) {
        "onActivityStarted : ${activity.localClassName}".loge()
    }

    override fun onActivityResumed(activity: Activity) {
        "onActivityResumed : ${activity.localClassName}".loge()
        CommonLibrary.instance.onPageResumeListener?.onPageResume(activity)
    }

    override fun onActivityPaused(activity: Activity) {
        "onActivityPaused : ${activity.localClassName}".loge()
        CommonLibrary.instance.onPagePauseListener?.onPagePause(activity)
    }


    override fun onActivityDestroyed(activity: Activity) {
        "onActivityDestroyed : ${activity.localClassName}".loge()
        KtxManager.popActivity(activity)
        CommonLibrary.instance.onPageDestroyListener?.onPageDestroy(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        "onActivitySaveInstanceState : ${activity.localClassName}".loge()

    }

    override fun onActivityStopped(activity: Activity) {
        "onActivityStopped : ${activity.localClassName}".loge()
    }


}