package com.example.database.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.database.databinding.ItemCarBinding
import com.example.database.room.Car

class CarViewHolder(
    private val binding: ItemCarBinding,
    private val onCarClicked: (Car) -> Unit
): RecyclerView.ViewHolder(binding.root){

    fun bind(car : Car){
        with(binding){
            textResultBrand.text = car.carBrand
            textResultModel.text = car.carModel

            deleteButton.setOnClickListener {
                onCarClicked(car)
            }

        }
    }
}