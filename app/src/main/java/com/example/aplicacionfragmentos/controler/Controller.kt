package com.example.aplicacionfragmentos.controler

import android.content.Context
import android.widget.Toast
import com.example.aplicacionfragmentos.adapter.AdapterMusica
import com.example.aplicacionfragmentos.MainActivity
import com.example.aplicacionfragmentos.dao.DaoMusic
import com.example.aplicacionfragmentos.models.Musica


class Controller ( val context : Context){
    lateinit var listMusicas : MutableList<Musica>
    init {
        initData()
    }
    fun initData(){
        listMusicas = DaoMusic. myDao.getDataHotels(). toMutableList()
    }
    fun loggOut() {
        Toast.makeText( context, "He mostrado los datos en pantalla", Toast. LENGTH_LONG).show()
        listMusicas.forEach{
            println (it)
        }
    }
    fun setAdapter() {
        val myActivity = context as MainActivity
        myActivity. binding.myRecyclerView.adapter = AdapterMusica(listMusicas)
    }
}