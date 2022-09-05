package com.example.scrollviewperformancetest.ui.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollviewperformancetest.R
import com.example.scrollviewperformancetest.presenter.PicsumPresenter
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecyclerViewActivity : AppCompatActivity() {
    @Inject
    lateinit var presenter: PicsumPresenter
    private lateinit var skeleton: Skeleton

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

        skeleton = recyclerView.applySkeleton(R.layout.layout_list_item)

        lifecycleScope.launchWhenCreated {
            showLoadingView()
            repeat(10) { idx ->
                // 1-base index
                imageListAdapter.addAll(presenter.getPicsumImageList(idx + 1))
            }
            dismissLoadingView()
        }
    }

    private fun showLoadingView() {
        skeleton.showSkeleton()
    }

    private fun dismissLoadingView() {
        skeleton.showOriginal()
    }
}
