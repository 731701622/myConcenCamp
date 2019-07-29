package com.edu.wkx.myConcencamp.view.adapter

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.edu.wkx.myConcencamp.R
import com.edu.wkx.myConcencamp.repository.room.model.WelfareModel

class WelfareAdapter : BaseQuickAdapter<WelfareModel, BaseViewHolder>(R.layout.item_welfare, null) {

    override fun convert(helper: BaseViewHolder, item: WelfareModel?) {
        val imgView = helper.getView<ImageView>(R.id.imgWelfare)
        Glide.with(mContext)
                .load(item?.url)
                .into(imgView)
    }
}