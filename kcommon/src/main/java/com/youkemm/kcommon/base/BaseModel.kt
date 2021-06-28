package com.youkemm.kcommon.base

import com.youkemm.kcommon.facade.CommonLibrary
import com.youkemm.kcommon.http.HttpProvider
import com.blankj.utilcode.util.SPUtils

/**
 * Created by harry on 2010/6/20.
 */

open class BaseModel<A, C> {
    protected var mApiService = HttpProvider.instance.provideApiService<A>()
    protected var mCacheService = HttpProvider.instance.provideCacheService<C>()
    protected var mSPUtils = SPUtils.getInstance(CommonLibrary.instance.spName)
}
