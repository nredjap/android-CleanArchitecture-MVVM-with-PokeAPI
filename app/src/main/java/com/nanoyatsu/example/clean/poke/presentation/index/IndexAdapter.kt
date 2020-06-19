package com.nanoyatsu.example.clean.poke.presentation.index

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nanoyatsu.example.clean.poke.databinding.ItemIndexBinding
import com.nanoyatsu.example.clean.poke.domain.poke.PokeNameImage

// memo : エラー表示で拡張するのでRecyclerView.ViewHolderのまま
class IndexAdapter(private val items: List<PokeNameImage>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    //class IndexAdapter : PagedListAdapter<PokeNameImage, RecyclerView.ViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return IndexItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val data = items[position]
//        val data = getItem(position)
        (holder as IndexItemViewHolder).bind(data!!)
    }

    class IndexItemViewHolder(private val binding: ItemIndexBinding) :
        RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun from(parent: ViewGroup): IndexItemViewHolder {
                val bind = ItemIndexBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
                return IndexItemViewHolder(bind)
            }
        }

        fun bind(data: PokeNameImage) {
            binding.data = data
        }
    }

    companion object {
        class DiffCallback : DiffUtil.ItemCallback<PokeNameImage>() {
            override fun areItemsTheSame(old: PokeNameImage, new: PokeNameImage): Boolean =
                old.name == new.name

            override fun areContentsTheSame(old: PokeNameImage, new: PokeNameImage): Boolean =
                old == new
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}