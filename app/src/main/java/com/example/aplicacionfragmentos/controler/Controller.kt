package com.example.aplicacionfragmentos.controler

import android.content.Context
import android.widget.Toast
import com.example.aplicacionfragmentos.adapter.AdapterHotel
import com.example.aplicacionfragmentos.MainActivity
import com.example.aplicacionfragmentos.dao.DaoHotels
import com.example.aplicacionfragmentos.models.Hotel


class Controller ( val context : Context){
    lateinit var listHotels : MutableList<Hotel> //lista de objetos
    init {
        initData()
    }
    fun initData(){
        // listHotels = DaoHotels2.myDao.toMutableList()
        listHotels = DaoHotels. myDao.getDataHotels(). toMutableList() //llamamos al singleton.
    }
    fun loggOut() {
        Toast.makeText( context, "He mostrado los datos en pantalla", Toast. LENGTH_LONG).show()
        listHotels.forEach{
            println (it)
        }
    }
    fun setAdapter() { // Cargamos nuestro AdapterHotgel al adapter del RecyclerView
        val myActivity = context as MainActivity
        myActivity. binding.myRecyclerView.adapter = AdapterHotel(listHotels) // Cargamos el Adapter que creamos.
    }
}