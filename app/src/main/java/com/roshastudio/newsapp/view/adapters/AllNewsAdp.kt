package com.roshastudio.newsapp.view.adapters

import android.content.Context
import android.database.DatabaseUtils
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.roshastudio.newsapp.R
import com.roshastudio.newsapp.databinding.AllnewsRvItemBinding
import com.roshastudio.newsapp.model.News

class AllNewsAdp(val context: Context, private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //compare list objects with DiffUtil to apply changes to the list
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {

        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.desc == newItem.desc && oldItem.img == newItem.img && oldItem.title == newItem.title
        }

    }

    //do the DiffUtil operations in another thread
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding: AllnewsRvItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.allnews_rv_item, parent, false)
        return ItemViewHolder(binding, interaction)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                if (position != null) {
                    holder.bind(differ.currentList[position])

                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<News>) {
        differ.submitList(list)
    }

    class ItemViewHolder
    constructor(
        itemView: AllnewsRvItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView.root) {
        private val itemBinding = itemView
        fun bind(item: News) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(bindingAdapterPosition, item)
            }
            itemBinding.news = item

        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: News)
    }
}