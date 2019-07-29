package com.edu.wkx.myConcencamp.view.adapter

import android.content.Intent
import android.support.v7.widget.CardView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.edu.wkx.myConcencamp.R
import com.edu.wkx.myConcencamp.repository.room.model.AndroidModel
import com.edu.wkx.myConcencamp.view.activity.WebviewActivity
import org.jetbrains.anko.sdk25.coroutines.onClick

class AndroidAdapter : BaseQuickAdapter<AndroidModel, BaseViewHolder>(R.layout.item_android, null) {

    override fun convert(helper: BaseViewHolder, item: AndroidModel) {
        helper.setText(R.id.tvTitle, item.desc)
        helper.setText(R.id.tvAuth, "作者: ${item.who?:"匿名"}    " +
                "发布时间: ${item.publishedAt?.substring(0, 10)}")
        helper.getView<CardView>(R.id.itemAndroid).onClick {
            val intent = Intent()
            intent.putExtra(WebviewActivity.PARAM_URL, item.url)
            intent.setClass(mContext, WebviewActivity::class.java)
            mContext.startActivity(intent)
        }
    }
}