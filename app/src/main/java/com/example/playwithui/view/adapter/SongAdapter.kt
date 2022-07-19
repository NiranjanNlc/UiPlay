package com.example.playwithui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.playwithui.databinding.ItemSongBinding
import com.example.playwithui.modal.data.Song

class SongAdapter : ListAdapter<Song, SongAdapter.SongViewHolder>(SONG_COMPARATOR)
{
    class SongViewHolder (var binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root)
    companion object {
        val SONG_COMPARATOR = object : DiffUtil.ItemCallback<Song>() {
            override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
                println(" item same ")
                return oldItem == newItem;
            }

            override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
                print(" Content same ")
                return oldItem.equals(newItem)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder
    {
        println("On view create ")
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSongBinding.inflate(inflater)
        return SongViewHolder(binding)
    }
    override fun onBindViewHolder(holder: SongViewHolder, position: Int)
    {
        println("On view bind"+getItem(position))
        holder.binding.song= getItem(position)
        holder.binding.executePendingBindings()
    }
}