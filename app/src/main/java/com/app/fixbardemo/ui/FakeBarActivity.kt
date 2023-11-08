package com.app.fixbardemo.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fixbardemo.R

/**
 * @Description:
 * @author gk
 * @date 2023/11/6
 */
class FakeBarActivity : AppCompatActivity() {

    private val scrollView: MyScrollView by lazy { findViewById<MyScrollView>(R.id.scrollView) }
    private val insideFixedBar: LinearLayout by lazy { findViewById<LinearLayout>(R.id.inside_fixed_bar) }
    private val rlInsideFixed: RelativeLayout by lazy { findViewById<RelativeLayout>(R.id.rl_inside_fixed) }
    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view) }
    private val llOutsideFixed: LinearLayout by lazy { findViewById<LinearLayout>(R.id.ll_outside_fixed) }


    private var topHeight = 0

    private val list: List<String> = ArrayList<String>().apply {
        for (i in 1..100) {
            add(i.toString() + "")
        }
    }
    private var normalAdapter: NormalAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_bar)

        topHeight = ViewUtils.dip2px(this, 200F)


        linearLayoutManager = LinearLayoutManager(this)
        normalAdapter = NormalAdapter(list)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = normalAdapter

        scrollView.setScrollChangeListener { x, y, _, _ ->
            Log.d("wuguoqing", "当前x：" + x + "当前y:" + y)
            if (y >= topHeight) {
                //重点 通过距离变化隐藏内外固定栏实现
                llOutsideFixed.visibility = View.VISIBLE
                insideFixedBar.visibility = View.GONE
                recyclerView.isNestedScrollingEnabled = true
            } else {
                llOutsideFixed.visibility = View.GONE
                insideFixedBar.visibility = View.VISIBLE
                recyclerView.isNestedScrollingEnabled = false
            }
        }

    }
}