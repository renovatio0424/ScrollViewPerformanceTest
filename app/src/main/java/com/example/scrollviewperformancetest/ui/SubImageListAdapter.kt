package com.example.scrollviewperformancetest.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.scrollviewperformancetest.R.drawable
import com.example.scrollviewperformancetest.R.id
import com.example.scrollviewperformancetest.R.layout
import com.example.scrollviewperformancetest.SIZE_IMAGE
import com.example.scrollviewperformancetest.data.PicsumImageInfo
import com.example.scrollviewperformancetest.ui.SubImageListAdapter.ImageViewHolder

class SubImageListAdapter : ListAdapter<PicsumImageInfo, ImageViewHolder>(imageDiff) {

    companion object {
        private val imageDiff = object : DiffUtil.ItemCallback<PicsumImageInfo>() {
            override fun areItemsTheSame(oldItem: PicsumImageInfo, newItem: PicsumImageInfo): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: PicsumImageInfo, newItem: PicsumImageInfo): Boolean {
                return oldItem == newItem // && oldItem.url == newItem.url
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layout.layout_list_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(position, currentList[position])
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView.findViewById<TextView>(id.textView)
        private val imageView = itemView.findViewById<ImageView>(id.imageView)

        @SuppressLint("SetTextI18n")
        fun onBind(position: Int, item: PicsumImageInfo) {
            textView.text = "${item.author} $position"
            Glide.with(itemView.context)
                .load(item.downloadUrl)
                .override(SIZE_IMAGE)
                .placeholder(drawable.ic_launcher_background)
                .into(imageView)
        }
    }
}
