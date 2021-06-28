package com.youkemm.kcommon.http.subscribers

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

import com.youkemm.kcommon.base.IBaseRefreshAndLoadMoreView
import com.youkemm.kcommon.base.IBaseView
import com.youkemm.kcommon.http.ErrorHandler
import com.youkemm.kcommon.http.progress.ProgressCancelListener
import com.youkemm.kcommon.http.progress.ProgressDialogHandler

import io.reactivex.observers.ResourceObserver

class ProgressObserver<T,V>(
        private val mBaseView: IBaseView<V>,
        private val mCallBack: ObserverCallBack<T> = object : ObserverCallBack<T> {
            override fun onNext(t: T) {
            }

            override fun onError(e: Throwable) {
            }

        },
        private val mIsLoadMore: Boolean = false) : ResourceObserver<T>(), ProgressCancelListener {

    private lateinit var mProgressDialogHandler: ProgressDialogHandler

    private lateinit var mContext: Context

    init {
        if (mBaseView is Activity) {
            mContext = mBaseView
        }
        if (mBaseView is Fragment) {
            mContext = (mBaseView as Fragment).activity!!
        }
        mProgressDialogHandler = ProgressDialogHandler(mContext, this, true)
    }

    public override fun onStart() {
        showProgressDialog()
    }

    override fun onNext(t: T) {
        if (mIsLoadMore) {
            if (mBaseView is IBaseRefreshAndLoadMoreView) {
                mBaseView.afterLoadMore(t as V)
            }
        }
        mCallBack.onNext(t)
    }

    override fun onError(e: Throwable) {
        ErrorHandler.handleError(e, mBaseView)
        if (mIsLoadMore) {
            if (mBaseView is IBaseRefreshAndLoadMoreView<*>) {
                mBaseView.afterLoadMoreError(e)
            }
        } else {
            mCallBack.onError(e)
        }
        dismissProgressDialog()
    }

    override fun onComplete() {
        dismissProgressDialog()
    }

    override fun onCancelProgress() {
        if (!this.isDisposed) {
            this.dispose()
        }
    }

    private fun showProgressDialog() {
        mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget()
    }

    private fun dismissProgressDialog() {
        mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget()
    }

}