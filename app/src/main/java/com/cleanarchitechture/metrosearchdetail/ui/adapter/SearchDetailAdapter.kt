package com.cleanarchitechture.metrosearchdetail.ui.adapter

/*
class SearchDetailAdapter(val context: Context) :
    RecyclerView.Adapter<SearchDetailAdapter.SearchViewHolder>() {

    // data
        private lateinit var binding: SearchDetailItemBinding


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
        binding =
            SearchDetailItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return SearchViewHolder(binding)


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
*/
