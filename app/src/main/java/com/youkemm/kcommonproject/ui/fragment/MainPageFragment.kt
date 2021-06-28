package com.youkemm.kcommonproject.ui.fragment

import android.annotation.SuppressLint
import com.youkemm.kcommon.base.BaseRefreshAndLoadMoreFragment
import com.youkemm.kcommon.ktx.core.ext.bundleOf
import com.youkemm.kcommon.widget.FixedLinearLayoutManager
import com.youkemm.kcommonproject.R
import com.youkemm.kcommonproject.common.entity.net.DataItem
import com.youkemm.kcommonproject.common.http.ApiService
import com.youkemm.kcommonproject.common.http.CacheService
import com.youkemm.kcommonproject.mvp.contract.MainPageContract
import com.youkemm.kcommonproject.mvp.presenter.MainPagePresenter
import com.youkemm.kcommonproject.ui.activity.WebActivity
import com.youkemm.kcommonproject.ui.adapter.listadapter.MainPageAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_main_page.*


/**
 * Created by harry on 2020/6/10.
 */
@SuppressLint("ValidFragment")
class MainPageFragment() :
        BaseRefreshAndLoadMoreFragment<ApiService, CacheService, MainPageContract.IMainPagePresenter, List<DataItem>>(),
        MainPageContract.IMainPageView {
    private val mTypeArray: Array<String> by lazy {
        arrayOf("all", "Android", "iOS", "休息视频", "福利", "拓展资源", "前端", "瞎推荐", "App")
    }

    private lateinit var mType: String

    override val adapter: BaseQuickAdapter<*, *>?
        get() = MainPageAdapter(arrayListOf())

    override val recyclerView: androidx.recyclerview.widget.RecyclerView?
        get() = rv_list

    override val layoutManager: androidx.recyclerview.widget.RecyclerView.LayoutManager?
        get() = FixedLinearLayoutManager(activity)

    override val swipeRefreshView: androidx.swiperefreshlayout.widget.SwipeRefreshLayout?
        get() = swipe_refresh

    override val multiStateView: MultiStateView?
        get() = multi_state_view

    override val layoutResId: Int
        get() = R.layout.fragment_main_page

    override val presenter: MainPageContract.IMainPagePresenter
        get() = MainPagePresenter(this)

    constructor(position: Int) : this() {
        mType = mTypeArray[position]
    }

    override fun initData() {
        mDataMap["type"] = mType
        mPresenter.initData(mDataMap)
    }

    override fun showContentView(data: List<DataItem>) {
        mAdapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            startActivity(
                    WebActivity::class.java,
                    bundleOf(
                            "url" to (mAdapter?.data!![position] as DataItem).url,
                            "title" to (mAdapter?.data!![position] as DataItem).desc))
        }
    }
}