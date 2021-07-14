package com.rockex6.homework.ui.zoodetail

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rockex6.homework.databinding.ItemZooPlantBinding
import com.rockex6.homework.loadImage
import com.rockex6.homework.ui.zoodetail.model.ZooPlantModel

class ZooPlantAdapter(
    private val context: Context,
    private val data: MutableList<ZooPlantModel>,
    private val onItemClickListener: ItemClickListener) :
    RecyclerView.Adapter<ZooPlantViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZooPlantViewHolder {
        val binding =
            ItemZooPlantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ZooPlantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ZooPlantViewHolder, position: Int) {
        holder.vPlantName.text = data[position].F_Name_Ch
        holder.vPlantDescription.text = data[position].F_AlsoKnown
        holder.vPlantImg.loadImage(context, data[position].F_Pic01_URL)

    }

    override fun getItemCount(): Int {
        return data.size
    }
}


class ZooPlantViewHolder(private val binding: ItemZooPlantBinding) :
    RecyclerView.ViewHolder(binding.root) {


    val vPlantImg: ImageView = binding.vPlantImg
    val vPlantName: TextView = binding.vPlantName
    val vPlantDescription: TextView = binding.vPlantDescription
}


interface ItemClickListener {
    fun onItemClickListener(item: ZooPlantModel, imageView: ImageView)
}