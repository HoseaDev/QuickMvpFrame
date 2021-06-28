package com.youkemm.kcommonproject.mvp.presenter

import android.annotation.SuppressLint
import com.youkemm.kcommon.base.BasePresenter
import com.youkemm.kcommon.entity.net.Optional
import com.youkemm.kcommon.facade.CommonLibrary
import com.youkemm.kcommon.http.subscribers.NoProgressObserver
import com.youkemm.kcommon.http.subscribers.ObserverCallBack
import com.youkemm.kcommonproject.common.entity.net.DataItem
import com.youkemm.kcommonproject.mvp.contract.MainPageContract
import com.youkemm.kcommonproject.mvp.model.MainPageModel
import com.blankj.utilcode.util.NetworkUtils
import com.trello.rxlifecycle2.kotlin.bindToLifecycle

class MainPagePresenter(iMainPageView: MainPageContract.IMainPageView) :
        BasePresenter<MainPageContract.IMainPageModel, MainPageContract.IMainPageView>(iMainPageView),
        MainPageContract.IMainPagePresenter {
    override val model: MainPageContract.IMainPageModel
        get() = MainPageModel()

    override fun initData(dataMap: Map<String, String>?) {
        initData(dataMap, CommonLibrary.instance.startPage)
    }

    @SuppressLint("CheckResult")
    override fun initData(dataMap: Map<String, String>?, pageNo: Int) {
        if (!NetworkUtils.isConnected()) {
            mView.showTip("网络已断开，当前数据为缓存数据")
        }

        if (pageNo == CommonLibrary.instance.startPage) {
            //如果请求的是分页的首页，必须先调用这个方法
            mView.beforeInitData()
            mModel.getData(
                    dataMap!!["type"].toString(),
                    pageNo,
                    CommonLibrary.instance.pageSize).bindToLifecycle(mLifecycleProvider).subscribeWith(
                    NoProgressObserver(mView, object : ObserverCallBack<Optional<List<DataItem>>> {
                        override fun onNext(t: Optional<List<DataItem>>) {
                            mView.showSuccessView(t.data)
                            mView.dismissLoading()
                        }

                        override fun onError(e: Throwable) {
                            mView.showErrorView("网络连接异常")
                            mView.dismissLoading()
                        }
                    }))
        } else {
            mModel.getData(
                    dataMap!!["type"].toString(),
                    pageNo, CommonLibrary.instance.pageSize).bindToLifecycle(mLifecycleProvider).subscribeWith(
                    NoProgressObserver(
                            mView, mIsLoadMore = true))
        }
    }
}

