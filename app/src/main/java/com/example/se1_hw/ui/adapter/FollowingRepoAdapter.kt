package com.example.se1_hw.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.se1_hw.data.following.FollowingRepo
import com.example.se1_hw.databinding.ItemRepoBinding

class FollowingRepoAdapter : RecyclerView.Adapter<FollowingRepoAdapter.FollowingRepoViewHolder>() {

    val repoList = mutableListOf<FollowingRepo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingRepoViewHolder {
        val binding = ItemRepoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FollowingRepoViewHolder(binding)
    }

    override fun getItemCount(): Int = repoList.size

    override fun onBindViewHolder(holder: FollowingRepoViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    class FollowingRepoViewHolder(
        private val binding:ItemRepoBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun onBind(followingRepo: FollowingRepo){
            binding.text1.text = followingRepo.repotext1
            binding.text2.text = followingRepo.repotext2
            binding.text3.text = followingRepo.repotext3
        }
    }
}