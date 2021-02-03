package com.example.camerax_gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.camerax_gallery.R
import com.example.camerax_gallery.listner.DisplayListner
import com.example.camerax_gallery.viewholder.DisplayViewHolder

class DisplayAdapter(val list:List<String>,val listner: DisplayListner):RecyclerView.Adapter<DisplayViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        return DisplayViewHolder(view,listner)
    }

    override fun onBindViewHolder(holder: DisplayViewHolder, position: Int) {
        val item = list[position]
        holder.setdat(item)
    }

    override fun getItemCount(): Int {
return list.size
    }
}