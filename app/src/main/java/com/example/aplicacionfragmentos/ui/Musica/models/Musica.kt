package com.example.aplicacionfragmentos.ui.Musica.models

class Musica (
    var name: String,
    var artita: String,
    var image: String
) {
    override fun toString(): String {
        return "Musica(name='$name', city='$artita', image='$image')"
    }
}