package com.example.sergio.appsergio.activitys


import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_add.*
import java.io.IOException
import java.net.URL
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.sergio.appsergio.R


class AddJuegoActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val spinner_generos = findViewById(R.id.txtGenero) as Spinner
        val spinner_adapter =
            ArrayAdapter.createFromResource(this,
                R.array.generos,
                R.layout.spinner_item
            )

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_generos.adapter = spinner_adapter


        btnAdd.setOnClickListener {
            addJuego()
        }
    }

    fun addJuego() {
        if (txtTitulo.text.toString().isNotEmpty() && txtDuracion.text.toString().isNotEmpty()) {
            var nombre = txtTitulo.text.toString()
            var genero = txtGenero.selectedItem.toString()
            var duracion = txtDuracion.text.toString().toInt()
            if (urlFoto.text.toString().length > 1) {
                var foto = urlFoto.text.toString()
                val url = "http://iesayala.ddns.net/sergio/insertar.php?nombre=" + nombre +
                        "&genero=" + genero +
                        "&duracion=" + duracion +
                        "&foto='" + foto + "'"
                Toast.makeText(applicationContext, "Juego añadido correctamente", Toast.LENGTH_SHORT).show()
                leerUrl(url)
                ivCaratula.loadUrl(foto)
            } else {
                var foto = "https://pannelfilter.com/inicio/wp-content/uploads/2017/12/Image-No-Disponible.gif"
                val url = "http://iesayala.ddns.net/sergio/insertar.php?nombre=" + nombre +
                        "&genero=" + genero +
                        "&duracion=" + duracion +
                        "&foto='" + foto + "'"
                Toast.makeText(applicationContext, "Juego añadido correctamente sin caratula", Toast.LENGTH_SHORT).show()
                leerUrl(url)
            }
       } else {
            Toast.makeText(applicationContext, "Rellene todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun leerUrl(urlString: String): String {
        val response = try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            URL(urlString)
                .openStream()
                .bufferedReader()
                .use { it.readText() }
        } catch (e: IOException) {
            "Error with ${e.message}."
        }
        return response
    }

    fun ImageView.loadUrl(url: String) {
        Picasso.get().load(url).into(this)
    }

}
