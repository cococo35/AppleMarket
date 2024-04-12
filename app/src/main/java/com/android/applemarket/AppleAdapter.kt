package com.android.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.applemarket.databinding.AppleItemBinding

class AppleAdapter (val items: MutableList<AppleItem>) : RecyclerView.Adapter<AppleAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null
    inner class Holder(val binding: AppleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val sampleImgView = binding.imgItemSample
        val sampleNameView = binding.txItemName
        val sampleAddressView = binding.txItemAddress
        val samplePriceView = binding.txItemPrice
        val sampleChatView = binding.txItemChat
        val sampleHeartView = binding.txItemHeart
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        var binding = AppleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.sampleImgView.setImageResource(items[position].appleImg)
        holder.sampleNameView.text = items[position].appleName
        holder.sampleAddressView.text = items[position].appleAddress
        holder.samplePriceView.text = items[position].applePrice
        holder.sampleChatView.text = items[position].appleChat.toString()
        holder.sampleHeartView.text = items[position].appleHeart.toString()
    }

}