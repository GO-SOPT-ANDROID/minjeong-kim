package org.android.go.sopt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ItemGithubRepoBinding

class RepoViewAdapter(context: Context): RecyclerView.Adapter<RepoViewAdapter.RepoViewHolder>() {

    private val inflater by lazy { LayoutInflater.from(context) }

    private var itemList: List<Repo> = emptyList()

    class RepoViewHolder(private val binding: ItemGithubRepoBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun onBind(item : Repo){
            binding.imgGithubRepo.setImageDrawable(binding.root.context.getDrawable(item.image))
            binding.tvGithubRepoName.text = item.name
            binding.tvGithubRepoAuthor.text = item.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding : ItemGithubRepoBinding = ItemGithubRepoBinding.inflate(inflater, parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setList(mockRepoList: List<Repo>) {
        this.itemList = mockRepoList.toList()
        notifyDataSetChanged()
    }

}

data class Repo(

    @DrawableRes val image: Int,
    val name: String,
    val author : String

)