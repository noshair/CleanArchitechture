package com.appfactorycoding.section.search_detail.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.appfactorycoding.R
import com.appfactorycoding.databinding.SearchDetailItemBinding
import com.bumptech.glide.Glide

class SearchDetailAdapter(val context: Context) :
    RecyclerView.Adapter<SearchDetailAdapter.SearchViewHolder>() {

    // data
    private lateinit var recyclerViewList: List<String>


    inner class SearchViewHolder(var searchDetailItemBinding: SearchDetailItemBinding) :
        RecyclerView.ViewHolder(searchDetailItemBinding.root) {
        fun bind(item: String) {
            Glide.with(context)
                .load(item)
                .placeholder(R.drawable.loadingif)
                .into(searchDetailItemBinding.additionalImg)
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchDetailAdapter.SearchViewHolder {
        val view: SearchDetailItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.search_detail_item, parent, false
            )
        return SearchViewHolder(view)


    }


    override fun onBindViewHolder(holder: SearchDetailAdapter.SearchViewHolder, position: Int) {
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
