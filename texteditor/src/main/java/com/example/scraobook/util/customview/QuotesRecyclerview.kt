package com.example.scraobook.util.customview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scraobook.presentation.text_list.QuotesListAdapter

class QuotesRecyclerview : RecyclerView {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = QuotesListAdapter()
    }

    private fun getMAdapter(): QuotesListAdapter {
        return adapter as QuotesListAdapter
    }

    fun setList(list: List<String>) {
        getMAdapter().setList(list)
    }
    fun setTextSize(textSize: Float){
        getMAdapter().setMyTextSize(textSize)
    }
    fun setListener(listener: QuotesListAdapter.QuotesItemClickListener) {
        getMAdapter().setItemClickListener(listener)
    }

}