package com.example.sergio.appsergio.tabs

import android.content.Context
import android.graphics.*
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_juegos.*
import java.io.IOException
import java.net.URL
import android.os.StrictMode
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import com.example.sergio.appsergio.R
import com.example.sergio.appsergio.models.VideojuegosArray
import com.example.sergio.appsergio.adapters.DatosAdapter

class LuchaFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private val p = Paint()
    private var adaptera : DatosAdapter?=null
    var videojuegos: VideojuegosArray =
        VideojuegosArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_juegos,container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mostrarVideojuegos()
        val juegos = videojuegos.getJuegos()
        RecyclerJuegos.layoutManager = LinearLayoutManager(context)
        RecyclerJuegos.adapter= DatosAdapter(juegos, context!!)
        RecyclerJuegos.layoutManager = GridLayoutManager(context!!, 1)
        adaptera= DatosAdapter(juegos, context!!)
        RecyclerJuegos.adapter = adaptera
        initSwipe()
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
            LuchaFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    private fun mostrarVideojuegos (){
        try {
            val jsonTexto = leerUrl("http://iesayala.ddns.net/sergio/index.php")
            val gson = Gson()
            val juegos = gson.fromJson(jsonTexto, VideojuegosArray::class.java)
            for (item in juegos.videojuegos.iterator()) {
                if(item.genero.equals("Lucha")) {
                        videojuegos.addJuegos(item.nombre, item.genero, item.duracion, item.foto)
                    }
            }
        }catch (e: Exception){
            Toast.makeText(context,  "Error en la conexión", Toast.LENGTH_SHORT).show()
        }
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

    private fun initSwipe() {
        val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (direction == ItemTouchHelper.LEFT) {
                    adaptera!!.removeItem(position)
                } else {

                    adaptera!!.removeItem(position)

                }
            }

            private fun removeView() {
                if (view!!.parent != null) {
                    (view!!.parent as ViewGroup).removeView(view)
                }
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

                val icon: Bitmap
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    val itemView = viewHolder.itemView
                    val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                    val width = height / 4

                    p.color = Color.parseColor("#FF0000")
                    val background = RectF(itemView.left.toFloat(), itemView.top.toFloat(), dX, itemView.bottom.toFloat())
                    c.drawRect(background, p)
                    icon = BitmapFactory.decodeResource(resources, R.drawable.papelera)
                    val icon_dest = RectF(itemView.left.toFloat() + width, itemView.top.toFloat() + width, itemView.left.toFloat() + 2 * width, itemView.bottom.toFloat() - width)
                    c.drawBitmap(icon, null, icon_dest, p)
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(RecyclerJuegos)
    }

}

private fun String.equals(other: String, ignoreCase: () -> Unit): Boolean {
    return true
}

