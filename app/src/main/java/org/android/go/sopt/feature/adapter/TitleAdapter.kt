package org.android.go.sopt.feature.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemTitleBinding

class TitleAdapter(context: Context): RecyclerView.Adapter<TitleAdapter.TitleViewHolder>() {

    private val inflater by lazy { LayoutInflater.from(context) }

    private var itemList: List<Title> = emptyList()

    class TitleViewHolder(private val binding: ItemTitleBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun onBind(item : Title){
            binding.tvItemTitle.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TitleViewHolder {
        val binding : ItemTitleBinding = ItemTitleBinding.inflate(inflater, parent, false)
        return TitleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TitleViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setList(mockTitleList: List<Title>) {
        this.itemList = mockTitleList.toList()
        notifyDataSetChanged()
    }
}

data class Title(
    val title: String
)