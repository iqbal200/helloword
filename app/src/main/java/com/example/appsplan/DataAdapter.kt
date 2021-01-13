package com.example.appsplan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_data.view.*

class DataAdapter (val callback: Callback): RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    private var datalist : List<DataModel>  = ArrayList()

    fun setData(data : List<DataModel>){
        this.datalist = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.ViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return ViewHolder(view, callback)
    }

    override fun getItemCount(): Int {

    return datalist.size
    }

    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {

    holder.bind(datalist[position])
    }

    class ViewHolder(itemView : View, val callback: Callback) : RecyclerView.ViewHolder(itemView){
    fun bind(data : DataModel ){
        itemView.tv_title.text = data.title
        itemView.tv_desc.text= data.desc

        itemView.setOnClickListener{
            callback.onClick(data)
        }



    }


    }
    interface  Callback{
        fun  onClick(data: DataModel)
    }
}


