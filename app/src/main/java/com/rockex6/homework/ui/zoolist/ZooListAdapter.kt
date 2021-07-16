package com.rockex6.homework.ui.zoolist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.rockex6.homework.R
import com.rockex6.homework.databinding.ItemZooListBinding
import com.rockex6.homework.loadImage
import com.rockex6.homework.ui.zoolist.model.ZooListResult

class ZooListAdapter(
    private val context: Context,
    private val data: MutableList<ZooListResult>,
    private val onItemClickListener: (ZooListResult, ImageView) -> Unit) :
    RecyclerView.Adapter<ZooListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ZooListViewHolder {
        val binging = ItemZooListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ZooListViewHolder(binging)
    }

    override fun onBindViewHolder(holder: ZooListViewHolder, position: Int) {
        holder.bind(data[position])
        holder.vZooName.text = data[position].E_Name
        holder.vZooDescription.text = data[position].E_Info
        holder.vZooImg.loadImage(context, data[position].E_Pic_URL)
        holder.vZooMemo.text = if (data[position].E_Memo.isEmpty()) {
            context.getString(R.string.list_no_memo)
        } else {
            data[position].E_Memo
        }
        holder.vZooImg.transitionName = data[position].E_no
        holder.itemView.setOnClickListener {
            onItemClickListener.invoke(data[position], holder.vZooImg)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}


class ZooListViewHolder(private val binding: ItemZooListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(zooListResult: ZooListResult) {
        ViewCompat.setTransitionName(vZooImg, zooListResult.E_no)
    }

    val vZooImg: ImageView = binding.vZooImg
    val vZooName: TextView = binding.vZooName
    val vZooDescription: TextView = binding.vZooDescription
    val vZooMemo: TextView = binding.vZooMemo
}