package com.example.scrollviewperformancetest.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.scrollviewperformancetest.R
import com.example.scrollviewperformancetest.R.layout
import com.example.scrollviewperformancetest.ui.recycler.RecyclerViewActivity
import com.example.scrollviewperformancetest.ui.scroll.ScrollActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        findViewById<Button>(R.id.buttonScroll).setOnClickListener {
            val intent = Intent(this, ScrollActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.buttonRecyclerView).setOnClickListener {
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }
    }
}
