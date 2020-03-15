package dog.snow.androidrecruittest.feature.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dog.snow.androidrecruittest.databinding.ListItemBinding
import dog.snow.androidrecruittest.feature.list.model.ListItem

class ListAdapter(
    private val onClick: (item: ListItem, position: Int, imageView: ImageView) -> Unit,
    private val onFilterResult: (List<ListItem>) -> Unit
) :
    androidx.recyclerview.widget.ListAdapter<ListItem, ListAdapter.ViewHolder>(DIFF_CALLBACK),
    Filterable {

    private var searchableList: ArrayList<ListItem> = ArrayList()
    private var originalDataSet: List<ListItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ViewHolder(
        private val binding: ListItemBinding,
        private val onClick: (item: ListItem, position: Int, imageView: ImageView) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListItem) = with(itemView) {
            binding.item = item
            setOnClickListener { onClick(item, adapterPosition, binding.ivThumb) }

            binding.executePendingBindings()
        }
    }

    fun setData(dataSet: List<ListItem>) {
        originalDataSet = dataSet
        submitList(dataSet)
    }

    fun filterItems(searchedTitle: CharSequence) {
        filter.filter(searchedTitle)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            private val filterResults = FilterResults()
            override fun performFiltering(searchedTitle: CharSequence?): FilterResults {
                searchableList.clear()
                if (searchedTitle.isNullOrBlank()) {
                    searchableList.addAll(originalDataSet)
                } else {
                    val searchResults =
                        originalDataSet.filter {
                            it.photoInfo.title.toLowerCase()
                                .contains(searchedTitle.toString().toLowerCase())
                        }
                    searchableList.addAll(searchResults)
                }
                return filterResults.also {
                    it.values = searchableList
                }
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                onFilterResult(searchableList)
            }
        }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListItem>() {
            override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
                oldItem.photoInfo.id == newItem.photoInfo.id

            override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
                oldItem == newItem
        }
    }

}
