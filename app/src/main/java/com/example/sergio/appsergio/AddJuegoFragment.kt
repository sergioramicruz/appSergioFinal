package com.example.sergio.appsergio

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_add.*
import java.io.IOException
import java.net.URL


class AddJuegoFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnAdd.setOnClickListener {
            addJuego()
        }
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AddJuegoFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    fun addJuego() {
        if (txtTitulo.text.toString().isNotEmpty() && txtGenero.text.toString().isNotEmpty() && txtDuracion.text.toString().isNotEmpty()) {
            var nombre = txtTitulo.text.toString()
            var genero = txtGenero.text.toString()
            var duracion = txtDuracion.text.toString().toInt()
            if (urlFoto.text.toString().length > 1) {
                var foto = urlFoto.text.toString()
                val url = "http://iesayala.ddns.net/sergio/insertar.php?nombre=" + nombre +
                        "&genero=" + genero +
                        "&duracion=" + duracion +
                        "&foto='" + foto + "'"
                Toast.makeText(context, "Juego añadido correctamente", Toast.LENGTH_SHORT).show()
                leerUrl(url)
                ivCaratula.loadUrl(foto)
            } else {
                var foto = "https://pannelfilter.com/inicio/wp-content/uploads/2017/12/Image-No-Disponible.gif"
                val url = "http://iesayala.ddns.net/sergio/insertar.php?nombre=" + nombre +
                        "&genero=" + genero +
                        "&duracion=" + duracion +
                        "&foto='" + foto + "'"
                Toast.makeText(context, "Juego añadido correctamente sin caratula", Toast.LENGTH_SHORT).show()
                leerUrl(url)
            }
       } else {
            Toast.makeText(context, "Rellene todos los campos", Toast.LENGTH_SHORT).show()
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
