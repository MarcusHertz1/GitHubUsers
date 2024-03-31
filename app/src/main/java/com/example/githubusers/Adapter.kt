package com.example.githubusers

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubusers.databinding.UserListItemBinding

class Adapter (private var itemsList: MutableList<UsersDataClass>, private val context:Context): RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val binding = UserListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(itemsList[position]) {
                println("login=$login")

                Glide
                    .with(context)
                    .load(avatarUrl)
                    .into(binding.avatar)
                binding.title.text = context.getString(R.string.login, login)
                binding.subtitle.text = context.getString(R.string.id, id.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}