package com.example.scrollviewperformancetest.ui.scroll

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollviewperformancetest.PAGE_COUNT
import com.example.scrollviewperformancetest.R.id
import com.example.scrollviewperformancetest.R.layout
import com.example.scrollviewperformancetest.data.PicsumImageInfo
import com.example.scrollviewperformancetest.presenter.PicsumPresenter
import com.example.scrollviewperformancetest.ui.SubImageListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScrollActivity : AppCompatActivity() {

    @Inject
    lateinit var presenter: PicsumPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_scroll)

        lifecycle.coroutineScope.launchWhenCreated {
            val imageList = mutableListOf<PicsumImageInfo>()
            repeat(10) { idx ->
                // 1-base index
                imageList.addAll(presenter.getPicsumImageList(idx + 1))
            }

            for ((index, chunkedImageList) in imageList.chunked(PAGE_COUNT).withIndex()) {
                setChildView(index, chunkedImageList)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setChildView(index: Int, imageList: List<PicsumImageInfo>) {
        val scrollBody = findViewById<LinearLayout>(id.linearLayoutScrollBody)
        val childView = layoutInflater.inflate(layout.layout_list, scrollBody, false)
        val textView = childView.findViewById<TextView>(id.textView2)
        val recyclerView = childView.findViewById<RecyclerView>(id.recyclerView2)

        val subAdapter = SubImageListAdapter()
        textView.text = "Page : $index"

        with(recyclerView) {
            layoutManager = LinearLayoutManager(childView.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = subAdapter
            setHasFixedSize(true)
        }
        subAdapter.submitList(imageList)

        scrollBody.addView(childView)
    }
}
