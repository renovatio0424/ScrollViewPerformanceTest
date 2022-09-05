package com.example.scrollviewperformancetest.ui.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollviewperformancetest.R
import com.example.scrollviewperformancetest.presenter.PicsumPresenter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecyclerViewActivity : AppCompatActivity() {
    @Inject
    lateinit var presenter: PicsumPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val imageListAdapter = ImageListAdapter()

        with(recyclerView) {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = imageListAdapter
            setHasFixedSize(true)
        }

        lifecycleScope.launchWhenCreated {
            repeat(10) { idx ->
                // 1-base index
                imageListAdapter.addAll(presenter.getPicsumImageList(idx + 1))
            }
        }
    }
}
