package com.youkemm.kcommonproject.mvp.contract

import com.youkemm.kcommon.base.*

/**
 * Created by harry on 2020/6/20.
 */
interface MainContract {
    interface IMainModel

    interface IMainPresenter : IBasePresenter

    interface IMainView : IBaseView<Any?>
}