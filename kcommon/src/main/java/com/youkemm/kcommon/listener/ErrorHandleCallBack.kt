package com.youkemm.kcommon.listener

import com.youkemm.kcommon.entity.net.IApiException

interface ErrorHandleCallBack {
    fun handleError(exception: IApiException)
}