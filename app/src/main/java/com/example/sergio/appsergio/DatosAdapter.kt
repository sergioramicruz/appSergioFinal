package com.example.sergio.appsergio


import android.content.Context
import android.os.StrictMode
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_datos.view.*
import java.io.IOException
import java.net.URL


class DatosAdapter(val items : ArrayList<Videojuego>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_datos, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nom = items.get(position).nombre
        val gen = items.get(position).genero
        val dur = items.get(position).duracion.toString()
        holder?.tvNombre?.text = nom
        holder?.tvGenero?.text = gen
        holder?.tvDuracion?.text = dur
        holder?.ivFoto?.loadUrl(items.get(position).foto)
        holder?.itemView?.setOnClickListener(View.OnClickListener {
            Toast.makeText(context,  items.get(position).nombre, Toast.LENGTH_SHORT).show() })
    }

    fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(this)
    }

    fun removeItem(position: Int) {
        items.removeAt(position)
        val url = "http://iesayala.ddns.net/sergio/borrar.php?nombre=" + items.get(position).nombre.toString()
        Toast.makeText(context, "Juego borrado correctamente", Toast.LENGTH_SHORT).show()
        leerUrl(url)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, items.size)
    }

    private fun leerUrl (urlString:String) : String {
        val response = try{
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            URL(urlString)
                .openStream()
                .bufferedReader()
                .use {it.readText()}
        } catch (e: IOException){
            "Error with ${e.message}."
        }
        return response
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvNombre = view.textViewDatos
    val tvGenero= view.textViewDatos2
    val tvDuracion= view.textViewDatos3
    val ivFoto= view.ivDato
}

