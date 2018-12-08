package com.example.sergio.appsergio

class VideojuegosArray {
    var videojuegos: ArrayList<Videojuego> = ArrayList<Videojuego>()

    fun addJuegos(nom:String, gen:String, dur:Int, foto:String){
        val v = Videojuego(nom,gen,dur,foto)
        videojuegos?.add(v)
    }

    fun getJuegos():ArrayList<Videojuego>{
        return videojuegos
    }
}