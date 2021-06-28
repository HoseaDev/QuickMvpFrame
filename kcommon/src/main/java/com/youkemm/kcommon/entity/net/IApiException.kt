package com.youkemm.kcommon.entity.net

interface IApiException {
    val resultCode: Int

    val msg: String
}