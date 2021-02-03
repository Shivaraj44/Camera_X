package com.example.camerax_gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.camerax_gallery.DataBase.Model
import com.example.camerax_gallery.R
import com.example.camerax_gallery.viewholder.ViewHolder

class Adapter(var userlist:List<Model>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view= LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model=userlist[position]
        holder.SetDataHome(model)
    }

    override fun getItemCount(): Int {
        return  userlist.size
    }

    fun UpdateData(user:List<Model>){
        userlist=user
        notifyDataSetChanged()
    }
}