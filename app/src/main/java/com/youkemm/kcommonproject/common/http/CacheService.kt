package com.youkemm.kcommonproject.common.http

import com.youkemm.kcommon.entity.net.Optional
import com.youkemm.kcommonproject.common.entity.net.DataItem
import io.reactivex.Observable
import io.rx_cache2.DynamicKeyGroup
import io.rx_cache2.EvictDynamicKeyGroup
import io.rx_cache2.LifeCache
import java.util.concurrent.TimeUnit

/**
 * Created by harry on 2020/6/20.
 */
interface CacheService {
    @LifeCache(duration = 10, timeUnit = TimeUnit.MINUTES)
    fun getMainDataList(
            observable: Observable<Optional<List<DataItem>>>,
            keyGroup: DynamicKeyGroup,
            evictDynamicKeyGroup: EvictDynamicKeyGroup): Observable<Optional<List<DataItem>>>
}