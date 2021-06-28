package com.youkemm.kcommonproject.ui.adapter.pageradapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.youkemm.kcommonproject.ui.fragment.factory.FragmentFactory

/**
 * Created by harry on 2020/6/21.
 */
class MainPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val mTypeArray: Array<String> by lazy {
        arrayOf("all", "Android", "iOS", "休息视频", "福利", "拓展资源", "前端", "瞎推荐", "App")
    }

    override fun getItem(position: Int): Fragment {
        return FragmentFactory.getFragment(position)
    }

    override fun getCount(): Int {
        return mTypeArray.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mTypeArray[position]
    }
}