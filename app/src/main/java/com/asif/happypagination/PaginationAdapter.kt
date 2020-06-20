package com.asif.happypagination

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asif.data.response.Results


class PaginationAdapter : RecyclerView.Adapter<PaginationAdapter.TestViewHolder>() {
    private var mList: ArrayList<Results>? = null
    var mActivity: Activity? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pagination_item, parent, false)
        return TestViewHolder(view)
    }
    override fun getItemCount(): Int {
        return mList!!.size
    }

    fun setList(mActivity: Activity, mList: ArrayList<Results>) {
        this.mActivity = mActivity
        this.mList = mList
    }


    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.item.text = mList!![position].title
    }

    class TestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item = itemView.findViewById<TextView>(R.id.textItem)

    }

}