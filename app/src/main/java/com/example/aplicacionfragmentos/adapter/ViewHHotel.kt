package com.example.aplicacionfragmentos

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacionfragmentos.databinding.ItemHotelBinding
import com.example.aplicacionfragmentos.models.Hotel

class ViewHHotel (view: View) : RecyclerView.ViewHolder (view){
    lateinit var binding: ItemHotelBinding
    init {
        binding = ItemHotelBinding.bind(view)

        binding.btnEdit.setOnClickListener{

        }

        binding.btnDelete.setOnClickListener{

        }
    }
    //método que se encarga de mapear los item por propiedad del modelo.
    fun renderize(hotel : Hotel){
        binding.txtviewName.setText(hotel.name)
        binding.txtviewCity.setText(hotel.city)
        binding.txtviewProvince.setText(hotel.province)
        binding.txtviewPhone.setText(hotel.phone)
        Glide
            .with( itemView.context)
            .load(hotel. image)
            .centerCrop()
            .into( binding.ivHotel)
    }

}
