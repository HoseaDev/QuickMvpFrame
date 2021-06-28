package com.youkemm.kcommonproject.mvp.presenter

import com.youkemm.kcommon.base.BasePresenter
import com.youkemm.kcommonproject.mvp.contract.WebContract
import com.youkemm.kcommonproject.mvp.model.WebModel
import io.reactivex.android.schedulers.AndroidSchedulers

class WebPresenter(iWebView: WebContract.IWebView) :
        BasePresenter<WebContract.IWebModel, WebContract.IWebView>(iWebView),
        WebContract.IWebPresenter {

    override val model: WebContract.IWebModel
        get() = WebModel()

    override fun initData(dataMap: Map<String, String>?) {
        AndroidSchedulers.mainThread()
    }

}

