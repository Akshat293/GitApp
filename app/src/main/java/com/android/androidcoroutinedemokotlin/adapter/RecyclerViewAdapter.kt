package com.android.androidcoroutinedemokotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.androidcoroutinedemokotlin.R
import com.android.androidcoroutinedemokotlin.models.RecyclerData
import com.squareup.picasso.Picasso

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

    var items = ArrayList<RecyclerData>()
    private lateinit var mlistner:onItemClickListner
    interface onItemClickListner{
        fun onItemClicked(position: Int)
    }
    fun setonItemClickListner(listner:onItemClickListner){
        mlistner=listner
    }

    fun setUpdatedData(items : ArrayList<RecyclerData>) {
        this.items = items
        notifyDataSetChanged()
    }
    class MyViewHolder(view: View,listner:onItemClickListner): RecyclerView.ViewHolder(view) {
        val imageThumb = view.findViewById<ImageView>(R.id.imageThumb)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvDesc = view.findViewById<TextView>(R.id.tvDesc)

        fun bind(data : RecyclerData) {
            tvTitle.text = data.name
            tvDesc.text = data.description

            val url  = data.owner.avatar_url

            Picasso.get()
                .load(url)
                .into(imageThumb)
        }
        init {
            view.setOnClickListener{
                listner.onItemClicked(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row, parent, false)

        return MyViewHolder(view,mlistner)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.bind(items.get(position))
    }
    private var onItemClickListener: ((RecyclerData) -> Unit)? = null


}