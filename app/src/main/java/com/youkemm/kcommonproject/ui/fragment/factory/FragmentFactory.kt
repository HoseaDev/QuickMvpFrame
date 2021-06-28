package com.youkemm.kcommonproject.ui.fragment.factory

import androidx.fragment.app.Fragment
import com.youkemm.kcommonproject.ui.fragment.MainPageFragment

/**
 * Created by harry on 2020/6/20.
 */
object FragmentFactory {
    private val mFragmentMap: HashMap<Int, Fragment> by lazy { hashMapOf<Int, Fragment>() }

    fun getFragment(position: Int): Fragment {
        if (mFragmentMap[position] == null) {
            mFragmentMap[position] = MainPageFragment(position)
        }
        return mFragmentMap[position]!!
    }

    fun clear() {
        mFragmentMap.clear()
    }
}