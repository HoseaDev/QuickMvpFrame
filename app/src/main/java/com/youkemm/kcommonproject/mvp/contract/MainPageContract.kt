package com.youkemm.kcommonproject.mvp.contract

import com.youkemm.kcommon.base.IBaseRefreshAndLoadMorePresenter
import com.youkemm.kcommon.base.IBaseRefreshAndLoadMoreView
import com.youkemm.kcommon.entity.net.Optional
import com.youkemm.kcommonproject.common.entity.net.DataItem
import io.reactivex.Observable

/**
 * Created by harry on 2020/6/20.
 */
interface MainPageContract {
    interface IMainPageModel {
        fun getData(type: String, pageNo: Int, limit: Int): Observable<Optional<List<DataItem>>>
    }

    interface IMainPagePresenter : IBaseRefreshAndLoadMorePresenter

    interface IMainPageView : IBaseRefreshAndLoadMoreView<List<DataItem>>
}