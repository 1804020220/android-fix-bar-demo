package com.app.fixbardemo.ui

import android.os.Bundle
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
class FakeBarActivity2 : AppCompatActivity() {

    private val scrollView: MyScrollView by lazy { findViewById<MyScrollView>(R.id.scrollView) }
    private val insideFixedBarParent: LinearLayout by lazy { findViewById<LinearLayout>(R.id.inside_fixed_bar_parent) }
    private val rlInsideFixed: RelativeLayout by lazy { findViewById<RelativeLayout>(R.id.rl_inside_fixed) }
    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view) }
    private val llFixedParent: LinearLayout by lazy { findViewById<LinearLayout>(R.id.ll_fixed_parent) }

    private val list: List<String> = ArrayList<String>().apply {
        for (i in 1..100) {
            add("" + i)
        }
    }
    private var normalAdapter: NormalAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private var topHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_bar)

        topHeight = ViewUtils.dip2px(this, 200F)

        linearLayoutManager = LinearLayoutManager(this)
        normalAdapter = NormalAdapter(list)

        recyclerView.layoutManager = linearLayoutManager
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = normalAdapter
        scrollView.setScrollChangeListener { x, y, oldX, oldY ->
            if (y >= topHeight) {
                if (rlInsideFixed.parent !== llFixedParent) {
                    insideFixedBarParent.removeView(rlInsideFixed)
                    llFixedParent.addView(rlInsideFixed)
                    recyclerView.isNestedScrollingEnabled = true
                }
            } else {
                if (rlInsideFixed.parent !== insideFixedBarParent) {
                    llFixedParent.removeView(rlInsideFixed)
                    insideFixedBarParent.addView(rlInsideFixed)
                    recyclerView.isNestedScrollingEnabled = false
                }
            }
        }
    }
}