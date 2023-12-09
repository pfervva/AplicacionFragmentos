package com.example.aplicacionfragmentos.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionfragmentos.R
import com.example.aplicacionfragmentos.models.Hotel

class AdapterHotel(private val listHotel: MutableList<Hotel>) : RecyclerView.Adapter<ViewHHotel>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHHotel {
        val layoutInflater = LayoutInflater.from(parent.context)
        val layoutItemHotel = R.layout.item_hotel
        return ViewHHotel(layoutInflater.inflate(layoutItemHotel, parent, false), this, listHotel)
    }

    override fun onBindViewHolder(holder: ViewHHotel, position: Int) {
        holder.renderize(listHotel[position])
    }

    override fun getItemCount(): Int = listHotel.size
}
