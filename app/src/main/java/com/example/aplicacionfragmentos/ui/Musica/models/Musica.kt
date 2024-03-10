package com.example.aplicacionfragmentos.ui.Musica.models

class Musica (
    var id: Int,
    var name: String,
    var artista: String,
    var image: String
) {
    override fun toString(): String {
        return "Musica(name='$name', artista='$artista', image='$image')"
    }
}
