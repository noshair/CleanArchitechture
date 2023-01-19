package com.cleanarchitechture.metrosearchdetail.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cleanarchitechture.R
import com.cleanarchitechture.databinding.SearchDetailItemBinding

class SearchDetailAdapter(val context: Context) :
    RecyclerView.Adapter<SearchDetailAdapter.SearchViewHolder>() {

    // data
    private lateinit var recyclerViewList: List<String>


    inner class SearchViewHolder(private var searchDetailItemBinding: SearchDetailItemBinding) :
        RecyclerView.ViewHolder(searchDetailItemBinding.root) {
        fun bind(item: String) {
            Glide.with(context)
                .load(item)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(searchDetailItemBinding.additionalImg)
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchViewHolder {
        val view: SearchDetailItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.search_detail_item, parent, false
            )
        return SearchViewHolder(view)


    }


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(recyclerViewList[position])
    }


    override fun getItemCount(): Int {
        if (::recyclerViewList.isInitialized) {
            return recyclerViewList.size
        }
        return 0
    }

    // reloadData()
    fun update(users: List<String>) {
        recyclerViewList = kotlin.collections.ArrayList(users)
        notifyDataSetChanged()
    }
}
