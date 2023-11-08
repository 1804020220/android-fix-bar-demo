package com.app.fixbardemo.ui.adapter

import android.view.View
import android.widget.TextView
import com.app.fixbardemo.R

/**
 * @Description:
 * @author gk
 * @date 2023/8/2
 */
class CommonViewHolder(itemView: View) : BaseViewHolder<CommonModel>(itemView) {

    private val text: TextView by lazy { itemView.findViewById(R.id.tv_index) }

    override fun bindData(data: CommonModel) {
        itemView.setBackgroundColor(android.graphics.Color.parseColor("#FFFFFF"))
        text.text = data.text
        text.apply {
            setTextColor(android.graphics.Color.parseColor("#000000"))
        }
    }

}