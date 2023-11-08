package com.app.fixbardemo.ui.adapter

import android.view.View
import android.widget.TextView
import com.app.fixbardemo.R

/**
 * @Description:
 * @author gk
 * @date 2023/8/2
 */
class StickyViewHolder(itemView: View) : BaseViewHolder<StickyModel>(itemView){

    private val text: TextView by lazy { itemView.findViewById(R.id.tv_index) }

    override fun bindData(data: StickyModel) {
        text.text = data.text
    }
}