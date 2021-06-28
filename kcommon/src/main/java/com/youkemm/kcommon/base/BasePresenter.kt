package com.youkemm.kcommon.base

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.youkemm.kcommon.facade.CommonLibrary

import com.blankj.utilcode.util.SPUtils
import com.trello.rxlifecycle2.LifecycleProvider

/**
 * Created by Ivan on 2017/1/3.
 */

abstract class BasePresenter<M, V : IBaseView<*>>(protected var mView: V) {

    protected var mModel: M

    protected var mSPUtils = SPUtils.getInstance(CommonLibrary.instance.spName)

    protected var mContext: Context? = null

    protected lateinit var mLifecycleProvider: LifecycleProvider<*>


    protected abstract val model: M


    init {
        this.mModel = model
        initContext()
    }


    private fun initContext() {
        mContext = if (mView is Fragment) {
            (mView as Fragment).activity
        } else {
            mView as Activity
        }
        mLifecycleProvider = mContext as LifecycleProvider<*>
    }
}
