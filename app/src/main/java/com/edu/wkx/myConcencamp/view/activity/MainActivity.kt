package com.edu.wkx.myConcencamp.view.activity

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.edu.wkx.myConcencamp.R
import com.edu.wkx.myConcencamp.view.fragment.AndroidFragment
import com.edu.wkx.myConcencamp.view.fragment.SourceFragment
import com.edu.wkx.myConcencamp.view.fragment.WelfareFragment
import kotlinx.android.synthetic.main.activity_main.*
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem
import me.majiajie.pagerbottomtabstrip.item.NormalItemView
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener


class MainActivity : AppCompatActivity(), LifecycleRegistryOwner {

    private var lifeRegister = LifecycleRegistry(this)
    private val fragmentList = listOf(
            SourceFragment(), WelfareFragment())
    private val TAG_IOS = "干货"
    private val TAG_WELFARE = "福利"

    override fun getLifecycle(): LifecycleRegistry {
        return lifeRegister
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.main_container, fragmentList[0], AndroidFragment.TAG)
                .commit()
        initBottomBar()
    }

    private fun initBottomBar() {
        val navController = bottomTab
                .custom()
                .addItem(newItem(R.drawable.bottom_android, R.drawable.bottom_android_pressed, TAG_IOS))
                .addItem(newItem(R.drawable.bottom_welfare, R.drawable.bottom_welfare_pressed, TAG_WELFARE))
                .build()
        navController.setSelect(0)
        navController.addTabItemSelectedListener(object : OnTabItemSelectedListener {
            override fun onSelected(index: Int, old: Int) {
                if (index == 0) {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.main_container, fragmentList[0], AndroidFragment.TAG)
                            .commit()
                } else if (index == 1) {
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.main_container, fragmentList[1], AndroidFragment.TAG)
                            .commit()
                }

            }

            override fun onRepeat(index: Int) {

            }
        })
    }

    private fun newItem(drawable: Int, checkedDrawable: Int, text: String): BaseTabItem {
        val normalItemView = NormalItemView(this)
        normalItemView.initialize(drawable, checkedDrawable, text)
        normalItemView.setTextDefaultColor(resources.getColor(R.color.bottomText))
        normalItemView.setTextCheckedColor(resources.getColor(R.color.colorPrimary))
        return normalItemView
    }
}
