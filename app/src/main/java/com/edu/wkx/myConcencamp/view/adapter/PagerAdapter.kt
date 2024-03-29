package com.edu.wkx.myConcencamp.view.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class PagerAdapter : FragmentPagerAdapter {

    private var mFragments: List<Fragment>? = null
    private var mTitles: List<String>? = null

    constructor(fm: FragmentManager?, mFragments: List<Fragment>?, mTitles: List<String>?) : super(fm) {
        this.mFragments = mFragments
        this.mTitles = mTitles
    }

    override fun getPageTitle(position: Int): CharSequence {
//        val pos = if (position < 4) position else 3
        return mTitles!![position]
    }

    override fun getItem(position: Int): Fragment {
        return mFragments!![position]
    }

    override fun getCount(): Int {
        return mFragments!!.size
    }
}