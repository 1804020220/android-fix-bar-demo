package com.app.fixbardemo.ui

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fixbardemo.R
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

/**
 * @Description:
 * @author gk
 * @date 2023/11/6
 */
class CoordinatorLayoutActivity : AppCompatActivity() {

    private val list: List<String> = ArrayList<String>().apply {
        for (i in 1..100) {
            add("" + i)
        }
    }
    private var normalAdapter: NormalAdapter? = null
    private var linearLayoutManager: LinearLayoutManager? = null
    private val appBar: AppBarLayout by lazy { findViewById<AppBarLayout>(R.id.app_bar) }
    private val toolbarLayout: CollapsingToolbarLayout by lazy { findViewById(R.id.toolbar_layout) }
    private val rlInsideFixed: RelativeLayout by lazy { findViewById<RelativeLayout>(R.id.rl_inside_fixed) }
    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.recycler_view) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_design)
        linearLayoutManager = LinearLayoutManager(this)
        normalAdapter = NormalAdapter(list)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = normalAdapter
    }

}