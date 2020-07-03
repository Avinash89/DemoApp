package com.learningapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learningapp.R
import com.learningapp.data.model.User
import kotlinx.android.synthetic.main.item.view.*

class ListAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<ListAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.txt_name.text = user.name
            itemView.txt_email.text = user.email
            Glide.with(itemView.img_avatar.context)
                .load(user.avator)
                .into(itemView.img_avatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addAll(list: List<User>) {
        users.addAll(list)
    }

}