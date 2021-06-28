package com.youkemm.kcommonproject.mvp.contract

import com.youkemm.kcommon.base.IBasePresenter
import com.youkemm.kcommon.base.IBaseView

/**
 * Created by harry on 2020/6/20.
 */
interface WebContract {
    interface IWebModel

    interface IWebPresenter : IBasePresenter

    interface IWebView : IBaseView<Any?>
}