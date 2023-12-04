package com.example.aplicacionfragmentos


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionfragmentos.models.Hotel


class AdapterHotel( var listHotel : MutableList<Hotel>) : RecyclerView.Adapter<ViewHHotel>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHHotel {
        val layoutInflater = LayoutInflater.from(parent. context)//objeto para crear la vista.
        val layoutItemHotel = R.layout.item_hotel //accedo al xml del item a crear.
        return ViewHHotel(layoutInflater.inflate(layoutItemHotel, parent, false))
    }
    /*
    Este método, debe renderizar todos los datos o propiedades de cada hotel con la view.
    Accedemos al objeto por medio de position
    */
    override fun onBindViewHolder(holder: ViewHHotel, position: Int) {
        holder.renderize( listHotel.get(position)) //renderizamos la view.
    }
    /*
    Este método, devuelve el número de objetos a representar en el recyclerView.
    */
    override fun getItemCount(): Int = listHotel.size
}