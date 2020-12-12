package com.example.mymansion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymansion.model.MansionItem

class MansionAdapter(val callback: FirstFragment): RecyclerView.Adapter<MansionAdapter.MansionViewHolder>() {

    private var mansionList= emptyList<MansionItem>()
    fun updateAdapter(myList:List<MansionItem>) {
        mansionList = myList
        notifyDataSetChanged()
    }

    inner class MansionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val listImage = itemView.findViewById<ImageView>(R.id.imageRecycler)
        val listPrice = itemView.findViewById<TextView>(R.id.tvListPrice)
        val listName = itemView.findViewById<TextView>(R.id.tvListName)
        val click = itemView.setOnClickListener {
            callback.passItemInfo(mansionList[adapterPosition])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MansionAdapter.MansionViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.mansionlist_item, parent, false)
        return MansionViewHolder(view)
    }

    override fun onBindViewHolder(holder: MansionAdapter.MansionViewHolder, position: Int) {
        val image = mansionList[position].photo
        Glide.with(holder.itemView.context).load(image).fitCenter().into(holder.listImage)

        holder.listPrice.text = mansionList[position].price.toString()
        holder.listName.text = mansionList[position].name
    }

    override fun getItemCount() = mansionList.size

    interface PassMansionData{
        fun passItemInfo(mansion: MansionItem)
    }

}