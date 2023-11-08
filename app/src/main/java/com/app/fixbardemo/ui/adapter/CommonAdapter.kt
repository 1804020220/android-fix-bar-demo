package com.app.fixbardemo.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.app.fixbardemo.R
import com.app.fixbardemo.ui.ViewUtils
import kotlin.random.Random

/**
 * @Description:
 * @author gk
 * @date 2023/11/7
 */
class CommonAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataList = ArrayList<BaseModel>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_STICKY -> {
                StickyViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_sticky_item, parent, false)
                )
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item, parent, false)
                CommonViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (dataList[position] is StickyModel) {
            (holder as StickyViewHolder).bindData(dataList[position] as StickyModel)
        } else {
            (holder as CommonViewHolder).bindData(dataList[position] as CommonModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = dataList[position]
        return if (item is StickyModel) VIEW_TYPE_STICKY
        else VIEW_TYPE_COMMON
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    companion object {
        const val VIEW_TYPE_STICKY = 0x1
        const val VIEW_TYPE_COMMON = 0x2
    }

    private fun getRandomColor(): Int {
        val random = Random(System.currentTimeMillis())
        // 生成随机的 RGB 值
        val red: Int = random.nextInt(256)
        val green: Int = random.nextInt(256)
        val blue: Int = random.nextInt(256)

        // 使用 Color.rgb() 方法将 RGB 值组合成颜色
        return Color.rgb(0, 0, 0)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: ArrayList<BaseModel>) {
        dataList = list
        notifyDataSetChanged()
    }

}