package com.android.applemarket

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.applemarket.databinding.AppleItemBinding

class AppleAdapter (private val items: MutableList<AppleItem>) : RecyclerView.Adapter<AppleAdapter.Holder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)

        fun onLongClick(view: View, position: Int)
    }
    var itemClick : ItemClick? = null
    inner class Holder(private val binding: AppleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val sampleImgView = binding.imgItemSample
        val sampleNameView = binding.txItemName
        val sampleAddressView = binding.txItemAddress
        val samplePriceView = binding.txItemPrice
        val sampleChatView = binding.txItemChat
        val sampleHeartView = binding.txItemHeart
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = AppleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        holder.itemView.setOnLongClickListener {
            itemClick?.onLongClick(it, position)
            return@setOnLongClickListener (false)
        }
        items[position].appleImg.let { holder.sampleImgView.setImageResource(it) }
        holder.sampleNameView.text = items[position].appleName
        holder.sampleAddressView.text = items[position].appleAddress
        holder.samplePriceView.text = items[position].applePrice
        holder.sampleChatView.text = items[position].appleChat.toString()
        holder.sampleHeartView.text = items[position].appleHeart.toString()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeItem(position: Int) {
        items.removeAt(position)
        notifyDataSetChanged()
    }
}