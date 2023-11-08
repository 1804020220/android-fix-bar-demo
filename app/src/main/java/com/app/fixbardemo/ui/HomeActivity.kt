package com.app.fixbardemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.app.fixbardemo.R

class HomeActivity : AppCompatActivity() {

    private val button1: Button by lazy { findViewById<Button>(R.id.button1) }
    private val button2: Button by lazy { findViewById<Button>(R.id.button2) }
    private val button12: Button by lazy { findViewById<Button>(R.id.button12) }
    private val button4: Button by lazy { findViewById<Button>(R.id.button4) }
    private val button3: Button by lazy { findViewById<Button>(R.id.button3) }
    private val button5: Button by lazy { findViewById<Button>(R.id.button5) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        button1.setOnClickListener {
            startActivity(Intent(this, FakeBarActivity::class.java))
        }
        button12.setOnClickListener {
            startActivity(Intent(this, FakeBarActivity2::class.java))
        }
        button2.setOnClickListener {
            startActivity(Intent(this, CoordinatorLayoutActivity::class.java))
        }
        button4.setOnClickListener {
            startActivity(Intent(this, DecorationActivity::class.java))
        }
        button3.setOnClickListener {
            startActivity(Intent(this, GroupActivity::class.java))
        }
        button5.setOnClickListener {
            startActivity(Intent(this, StickyActivity::class.java))
        }

    }
}