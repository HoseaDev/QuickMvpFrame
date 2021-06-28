package com.youkemm.kcommonproject.ui.activity

import android.text.format.Formatter
import com.youkemm.kcommon.base.BaseActivity
import com.youkemm.kcommon.ktx.core.ext.bundleOf
import com.youkemm.kcommon.ktx.core.ext.toast
import com.youkemm.kcommon.widget.GlideCircleTransform
import com.youkemm.kcommonproject.R
import com.youkemm.kcommonproject.common.http.ApiService
import com.youkemm.kcommonproject.common.http.CacheService
import com.youkemm.kcommonproject.mvp.contract.MainContract
import com.youkemm.kcommonproject.mvp.presenter.MainPresenter
import com.youkemm.kcommonproject.ui.adapter.pageradapter.MainPagerAdapter
import com.youkemm.kcommonproject.ui.fragment.factory.FragmentFactory
import com.blankj.utilcode.util.CacheDiskUtils
import com.bumptech.glide.Glide
import com.kennyc.view.MultiStateView
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.menu_main_drawer.*


class MainActivity : BaseActivity<ApiService, CacheService, MainPresenter, Any?>(),
        MainContract.IMainView {
    private val AVATAR_URL = "https://avatars.githubusercontent.com/u/15081751?v=4"
    private val ABOUT_ME_URL = "https://github.com/hlgithub369"
    private val READ_ME_URL = "https://github.com/BlackFlagBin/KCommonProject/blob/master/README.md"
    private val MORE_PROJECT_URL = "https://github.com/hlgithub369?tab=repositories"
    private val mTypeArray: Array<String> by lazy {
        arrayOf("all", "Android", "iOS", "休息视频", "福利", "拓展资源", "前端", "瞎推荐", "App")
    }


    override val swipeRefreshView: androidx.swiperefreshlayout.widget.SwipeRefreshLayout?
        get() = null

    override val multiStateView: MultiStateView?
        get() = null

    override val layoutResId: Int
        get() = R.layout.activity_main

    override val presenter: MainPresenter
        get() = MainPresenter(this)

    override fun initView() {
        super.initView()

        setupSlidingView()
        setupViewPager()
        rl_right.setOnClickListener {
            startActivity(
                    WebActivity::class.java, bundleOf("url" to ABOUT_ME_URL, "title" to "关于作者"))

        }

        ll_read_me.setOnClickListener {
            startActivity(
                    WebActivity::class.java, bundleOf("url" to READ_ME_URL, "title" to "ReadMe"))

        }

        ll_more_project.setOnClickListener {
            startActivity(
                    WebActivity::class.java, bundleOf("url" to MORE_PROJECT_URL, "title" to "更多项目"))

        }
        ll_clear_cache.setOnClickListener {
            clearCache()
        }


    }

    override fun initData() {
    }

    override fun showContentView(data: Any?) {
    }

    private fun setupSlidingView() {
        val slidingRootNav = SlidingRootNavBuilder(this).withToolbarMenuToggle(
                tb_main).withMenuOpened(
                false).withContentClickableWhenMenuOpened(false).withMenuLayout(
                R.layout.menu_main_drawer).inject()

        ll_menu_root.setOnClickListener {


            slidingRootNav.closeMenu(true)
        }

        Glide.with(this).load(
                AVATAR_URL).placeholder(
                R.mipmap.avatar).error(R.mipmap.avatar).dontAnimate().transform(
                GlideCircleTransform(
                        this)).into(iv_user_avatar)
    }

    private fun setupViewPager() {
        vp_content.adapter = MainPagerAdapter(supportFragmentManager)
        tl_type.setupWithViewPager(vp_content)
        vp_content.offscreenPageLimit = mTypeArray.size - 1
    }

    private fun clearCache() {
        val cache = CacheDiskUtils.getInstance(cacheDir)
        val cacheSize = Formatter.formatFileSize(
                this, cache.cacheSize)
        cache.clear()
        toast("清除缓存$cacheSize")
    }

    override fun onDestroy() {
        super.onDestroy()
        //清除缓存的fragment，避免内存泄漏
        FragmentFactory.clear()
    }

}
