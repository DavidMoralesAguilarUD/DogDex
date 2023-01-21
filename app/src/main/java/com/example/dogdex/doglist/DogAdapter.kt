package com.example.dogdex.doglist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.dogdex.model.Dog
import com.example.dogdex.databinding.DogListItemBinding

class DogAdapter: ListAdapter<Dog, DogAdapter.DogViewHolder>(DiffCallback){

    companion object DiffCallback : DiffUtil.ItemCallback<Dog>(){
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem.id === newItem.id
    }}
    private var onItemCLickListener: ((Dog) -> Unit)? = null
    fun setOnItemClickListener(onItemClickListener: (Dog) -> Unit){
        this.onItemCLickListener = onItemClickListener
    }

    private var onLongItemCLickListener: ((Dog) -> Unit)? = null
    fun setLongOnItemClickListener(onLongItemClickListener: (Dog) -> Unit){
        this.onLongItemCLickListener = onLongItemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {

        val binding = DogListItemBinding
            .inflate(LayoutInflater.from(parent.context))

        return DogViewHolder(binding)

    }

    override fun onBindViewHolder(dogViewHolder: DogViewHolder, position: Int) {
        val dog = getItem(position)
        dogViewHolder.bind(dog)
    }

    inner class DogViewHolder(private val binding: DogListItemBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(dog: Dog){
                binding.dogListItemLayout.setOnClickListener {
                    onItemCLickListener?.invoke(dog)
                }
                binding.dogListItemLayout.setOnLongClickListener{
                    onLongItemCLickListener?.invoke(dog)
                    true
                }
                binding.dogImage.load(dog.imageUrl)
            }

    }

}

