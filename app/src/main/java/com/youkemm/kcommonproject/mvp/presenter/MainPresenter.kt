package com.youkemm.kcommonproject.mvp.presenter

import com.youkemm.kcommon.base.BasePresenter
import com.youkemm.kcommonproject.mvp.contract.MainContract
import com.youkemm.kcommonproject.mvp.model.MainModel

class MainPresenter(iMainView: MainContract.IMainView) :
        BasePresenter<MainContract.IMainModel, MainContract.IMainView>(iMainView),
        MainContract.IMainPresenter {
    override val model: MainContract.IMainModel
        get() = MainModel()

    override fun initData(dataMap: Map<String, String>?) {
    }

}

