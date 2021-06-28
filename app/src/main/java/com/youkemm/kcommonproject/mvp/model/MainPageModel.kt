package com.youkemm.kcommonproject.mvp.model

import com.youkemm.kcommon.base.BaseModel
import com.youkemm.kcommon.entity.net.Optional
import com.youkemm.kcommon.http.transformer.DefaultTransformer
import com.youkemm.kcommonproject.common.entity.net.DataItem
import com.youkemm.kcommonproject.common.http.ApiService
import com.youkemm.kcommonproject.common.http.CacheService
import com.youkemm.kcommonproject.mvp.contract.MainPageContract
import com.blankj.utilcode.util.NetworkUtils
import io.reactivex.Observable

class MainPageModel : BaseModel<ApiService, CacheService>(), MainPageContract.IMainPageModel {
    override fun getData(type: String, pageNo: Int, limit: Int): Observable<Optional<List<DataItem>>> {

        return if (NetworkUtils.isConnected()) {
//            mCacheService.getMainDataList(
//                    mApiService.getMainDataList(
//                            type, limit, pageNo).compose(DefaultTransformer()),
//                    DynamicKeyGroup(type, pageNo),
//                    EvictDynamicKeyGroup(true)).subscribeOn(Schedulers.io()).observeOn(
//                    AndroidSchedulers.mainThread())
            mApiService.getMainDataList(
                    type, limit, pageNo).compose(DefaultTransformer())
        } else {
//            mCacheService.getMainDataList(
//                    mApiService.getMainDataList(
//                            type, limit, pageNo).compose(DefaultTransformer()),
//                    DynamicKeyGroup(type, pageNo),
//                    EvictDynamicKeyGroup(false)).subscribeOn(Schedulers.io()).observeOn(
//                    AndroidSchedulers.mainThread())
            mApiService.getMainDataList(
                    type, limit, pageNo).compose(DefaultTransformer())
        }
    }
}
