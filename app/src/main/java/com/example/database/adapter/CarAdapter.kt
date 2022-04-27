package com.example.database.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.database.databinding.ItemCarBinding
import com.example.database.room.Car


class CarAdapter(private val action: (Car) -> Unit) : ListAdapter<Car, CarViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(
            binding = ItemCarBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onCarClicked = action
        )
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Car>(){
            override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
                return oldItem.carBrand == newItem.carBrand && oldItem.carModel == newItem.carModel
            }
        }
    }
}
