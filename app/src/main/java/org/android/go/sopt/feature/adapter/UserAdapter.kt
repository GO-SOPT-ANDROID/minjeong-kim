package org.android.go.sopt.feature.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.android.go.sopt.data.datasource.model.ResponseUserDTO
import org.android.go.sopt.databinding.ItemUserListBinding

class UserAdapter(context: Context) : ListAdapter<ResponseUserDTO.UserData, RecyclerView.ViewHolder>(
    diffUtil
) {
    private val inflater by lazy { LayoutInflater.from(context) }

    class UserViewHolder(
        private val binding: ItemUserListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ResponseUserDTO.UserData) {
            with(binding) {
                imgUser.load(data.avatar)
                tvUserName.text = data.first_name
                tvUserEmail.text = data.email
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserListBinding.inflate(inflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> holder.onBind(
                currentList[position]
            )
        }
    }

    override fun submitList(list: List<ResponseUserDTO.UserData>?) {
        super.submitList(list?.let {ArrayList(it)})
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResponseUserDTO.UserData>() {
            override fun areItemsTheSame(
                oldItem: ResponseUserDTO.UserData,
                newItem: ResponseUserDTO.UserData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponseUserDTO.UserData,
                newItem: ResponseUserDTO.UserData
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}