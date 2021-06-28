package com.youkemm.kcommon.ktx.core.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.youkemm.kcommon.facade.CommonLibrary
import com.youkemm.kcommon.ktx.core.ext.toast

/**
 * Created by Harry
 * on 2019/8/6 15:10
 */
class KtxAppLifeObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onForeground() {
     CommonLibrary.instance.context.toast("应用进入前台")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onBackground() {
        CommonLibrary.instance.context.toast("应用进入后台")
    }
}