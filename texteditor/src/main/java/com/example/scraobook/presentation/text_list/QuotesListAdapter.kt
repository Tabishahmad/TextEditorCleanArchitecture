package com.example.scraobook.presentation.text_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scraobook.databinding.QuotesListAdapterBinding

class QuotesListAdapter : RecyclerView.Adapter<QuotesListAdapter.MyHolder>() {

    private var listener: QuotesItemClickListener? = null
    private var inflater: LayoutInflater? = null
    private val list = ArrayList<String>()
    var fontTextSize : Float = 12.0F

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }
        val b = QuotesListAdapterBinding.inflate(inflater!!, parent, false)
        return MyHolder(b)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.b.tv.textSize = fontTextSize
        holder.setData(list[position])
        holder.b.card.setOnClickListener {
            listener?.onQuoteItemClick(
                it,
                list[position],
                position
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyHolder(itemView: QuotesListAdapterBinding) : RecyclerView.ViewHolder(itemView.root) {
        val b = itemView

        fun setData(text: String) {
            b.tv.text = text
        }
    }

    fun setItemClickListener(listener: QuotesItemClickListener) {
        this.listener = listener
    }

    interface QuotesItemClickListener {
        fun onQuoteItemClick(v: View, quote: String, index: Int)
    }
    fun setMyTextSize(tsize: Float){
        fontTextSize = tsize
    }
    fun setList(list: List<String>) {
        with(this.list) {
            this.clear()
            this.addAll(list)
        }
        notifyDataSetChanged()
    }

}