package com.youkemm.kcommonproject.mvp.model

import com.youkemm.kcommon.base.BaseModel
import com.youkemm.kcommonproject.common.http.ApiService
import com.youkemm.kcommonproject.common.http.CacheService
import com.youkemm.kcommonproject.mvp.contract.MainContract

class MainModel : BaseModel<ApiService, CacheService>(), MainContract.IMainModel