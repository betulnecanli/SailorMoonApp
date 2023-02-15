package com.betulnecanli.sailormoonapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.betulnecanli.sailormoonapp.data.remote.model.SailorMoon
import com.betulnecanli.sailormoonapp.databinding.CharacterCardBinding
import com.betulnecanli.sailormoonapp.utils.Constants.BASE_URL

class ListAdapter(
    private val listener : OnItemClickListener
) : PagingDataAdapter<SailorMoon, ListAdapter.SailorMoonViewHolder>(diffCallBack) {

    inner class SailorMoonViewHolder(
        val binding : CharacterCardBinding) :RecyclerView.ViewHolder(binding.root){
            init{
                binding.apply {
                    root.setOnClickListener {
                        val position = adapterPosition
                        if(position != RecyclerView.NO_POSITION){
                            val character = getItem(position)
                            if(character != null){
                                listener.onItemClickListener(character)
                            }
                        }
                    }
                }
            }
            fun bind(characters: SailorMoon){
                binding.apply {
                    characterNameTextView.text = characters.name
                    characterImgView.load(BASE_URL + characters.image) {
                        crossfade(true)
                        crossfade(1000)
                    }

                }
            }

        }

    companion object{
        val diffCallBack = object : DiffUtil.ItemCallback<SailorMoon>(){
            override fun areItemsTheSame(oldItem: SailorMoon, newItem: SailorMoon): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: SailorMoon, newItem: SailorMoon): Boolean {
                return oldItem == newItem
                 }

        }
    }

    override fun onBindViewHolder(holder: SailorMoonViewHolder, position: Int) {
        val currentItem = getItem(position)
        if(currentItem!=null){
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SailorMoonViewHolder {
       return SailorMoonViewHolder(
           CharacterCardBinding.inflate(
               LayoutInflater.from(parent.context),
               parent,
               false
           )
       )
    }
    interface OnItemClickListener{
        fun onItemClickListener(character: SailorMoon)
    }
}