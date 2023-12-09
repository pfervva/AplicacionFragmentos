package com.example.aplicacionfragmentos.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aplicacionfragmentos.databinding.ItemHotelBinding
import com.example.aplicacionfragmentos.models.Hotel

class ViewHHotel(view: View, private val adapter: AdapterHotel, private val hotelList: MutableList<Hotel>) :
    RecyclerView.ViewHolder(view) {

    lateinit var binding: ItemHotelBinding

    init {
        binding = ItemHotelBinding.bind(view)
        binding.btnEdit.setOnClickListener {
            // Implementa la lógica para editar el hotel si es necesario
        }
        binding.btnDelete.setOnClickListener {
            // Obtiene la posición del hotel en la lista
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                // Remueve el hotel de la lista
                hotelList.removeAt(position)
                // Notifica al adaptador sobre el cambio en los datos
                adapter.notifyItemRemoved(position)
            }
        }
    }

    // Método que se encarga de mapear los item por propiedad del modelo.
    fun renderize(hotel: Hotel) {
        binding.txtviewName.text = hotel.name
        binding.txtviewCity.text = hotel.city
        binding.txtviewProvince.text = hotel.province
        binding.txtviewPhone.text = hotel.phone
        Glide.with(itemView.context)
            .load(hotel.image)
            .centerCrop()
            .into(binding.ivHotel)
    }
}

