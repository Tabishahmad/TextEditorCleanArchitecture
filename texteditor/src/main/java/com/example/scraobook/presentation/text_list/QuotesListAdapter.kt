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
    private var rv: RecyclerView? = null
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
        holder.b.download.setOnClickListener {
            var capturedView = getTextView(position)
            listener?.onQuoteItemClick(
                it,
                list[position],
                position,
                capturedView
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
        fun onQuoteItemClick(clickedView: View, quote: String, index: Int,capturedView: View?)
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

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        rv = recyclerView
    }
    fun getTextView(index: Int): View? {
        val viewHolder = rv?.findViewHolderForAdapterPosition(index)
        viewHolder?.let {
            val holder = it as MyHolder
            return holder.b.card
        }
        return null
    }
}