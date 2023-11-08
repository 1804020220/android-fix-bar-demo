package com.app.fixbardemo.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fixbardemo.R
import com.app.fixbardemo.sticky.StickyHeaderCallbacks
import com.app.fixbardemo.sticky.StickyHeaderLinearLayoutManager
import com.app.fixbardemo.ui.adapter.BaseModel
import com.app.fixbardemo.ui.adapter.CommonAdapter
import com.app.fixbardemo.ui.adapter.CommonModel
import com.app.fixbardemo.ui.adapter.StickyModel

/**
 * @Description:
 * @author gk
 * @date 2023/11/7
 */
class StickyActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.main_recycler_view) }
    val data = ArrayList<BaseModel>().apply {
        for (i in 1..100) {
            if (i % 10 == 0) {
                add(StickyModel("i am sticky header $i"))
            } else {
                add(CommonModel("Item $i"))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sticky)
        recyclerView.layoutManager =
            StickyHeaderLinearLayoutManager(this, object : StickyHeaderCallbacks {
                override fun isStickyHeader(position: Int): Boolean {
                    return data[position] is StickyModel
                }

                override fun setupStickyHeaderView(stickyHeader: View) {
                    stickyHeader.setBackgroundResource(R.color.purple_200)
                }

                override fun teardownStickyHeaderView(stickyHeader: View) {
                    stickyHeader.background = null
                }
            })
        val adapter = CommonAdapter(this)
        recyclerView.adapter = adapter
        adapter.setData(data)

    }
}