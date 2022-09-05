package com.example.scrollviewperformancetest.ui.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scrollviewperformancetest.R.id
import com.example.scrollviewperformancetest.R.layout
import com.example.scrollviewperformancetest.data.PicsumImageInfo
import com.example.scrollviewperformancetest.ui.SubImageListAdapter
import com.example.scrollviewperformancetest.ui.recycler.ImageListAdapter.ListViewHolder

class ImageListAdapter : RecyclerView.Adapter<ListViewHolder>() {
    private val currentList: MutableList<List<PicsumImageInfo>> = mutableListOf()

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pageTextView: TextView = itemView.findViewById(id.textView2)
        private val recyclerView: RecyclerView = itemView.findViewById(id.recyclerView2)

        @SuppressLint("SetTextI18n")
        fun onBind(page: Int, imageList: List<PicsumImageInfo>) {
            val subAdapter = SubImageListAdapter()
            with(recyclerView) {
                layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                adapter = subAdapter
                setHasFixedSize(true)
            }
            subAdapter.submitList(imageList)
            pageTextView.text = "Page : $page"
        }
    }

    fun addAll(imageList: List<PicsumImageInfo>) {
        val lastIdx = currentList.lastIndex + 1
        currentList.add(imageList)
        notifyItemInserted(lastIdx)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.onBind(position, currentList[position])
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout.layout_list, parent, false)
        return ListViewHolder(view)
    }
}
