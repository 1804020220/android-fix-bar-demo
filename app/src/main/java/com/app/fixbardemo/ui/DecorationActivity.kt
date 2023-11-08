package com.app.fixbardemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fixbardemo.R

/**
 * @Description:
 * @author gk
 * @date 2023/11/6
 */
class DecorationActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.main_recycler_view) }
    private var fruitList: ArrayList<itemBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decoration)
        initData()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(fruitDecoration(this))

        recyclerView.adapter = fruitAdapter(this, fruitList)
    }

    private fun initData() {
        fruitList = ArrayList()
        for (i in 0..3) {
            for (j in 0..19) {
                if (i % 2 == 0) {
                    fruitList!!.add(itemBean("apple$j", "$i"))
                } else {
                    fruitList!!.add(itemBean("banana$j", "$i"))
                }
            }
        }
    }
}