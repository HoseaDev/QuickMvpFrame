package com.youkemm.kcommonproject.common.http

import com.youkemm.kcommonproject.BuildConfig
import com.youkemm.kcommonproject.common.entity.net.DataItem
import com.youkemm.kcommonproject.common.entity.net.HttpResultEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by harry on 2020/6/20.
 */
interface ApiService {
    @GET("${BuildConfig.EXTRA_URL}search/query/listview/category/{type}/count/{limit}/page/{pageNo}")
    fun getMainDataList(
            @Path("type") type: String, @Path("limit") limit: Int, @Path(
                    "pageNo") pageNo: Int): Observable<HttpResultEntity<List<DataItem>>>
}