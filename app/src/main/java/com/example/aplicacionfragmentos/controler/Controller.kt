package com.example.aplicacionfragmentos

import android.content.Context
import android.widget.Toast


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
        myActivity. binding.myRecyclerView.adapter = AdapterHotel( listHotels) // Cargamos el Adapter que creamos.
    }
}