package org.android.go.sopt.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemPagerBinding

class GalleryAdapter(
    _itemList: List<Int> = listOf(),
) : RecyclerView.Adapter<GalleryAdapter.HomeViewHolder>() {
    private var itemList: List<Int> = _itemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    class HomeViewHolder(
        private val binding: ItemPagerBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(src: Int) {
            binding.imgGallery.setImageResource(src)
        }
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    fun setItemList(itemList: List<Int>) {
        this.itemList = itemList
        notifyDataSetChanged()
    }
}