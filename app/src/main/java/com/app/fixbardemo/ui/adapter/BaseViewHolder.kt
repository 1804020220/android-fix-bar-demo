package com.app.fixbardemo.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @Description:可以设置通用的事件
 * @author gk
 * @date 2023/8/2
 */
open class BaseViewHolder<T : BaseModel>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var cardData: BaseModel? = null
    open fun bindData(data: T) {
        cardData = data
    }
}
