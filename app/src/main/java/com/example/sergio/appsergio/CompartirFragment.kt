package com.example.sergio.appsergio

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_share.*
import android.content.Intent
import android.widget.Toast
import java.io.IOException


class CompartirFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_share, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnFace.setOnClickListener {
            try{
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "¡La mejor app de videojuegos! GAMELAND")
                intent.setPackage("com.facebook.katana")
                startActivity(Intent.createChooser(intent,
                    "Using: "))
                Toast.makeText(context, "Instale Facebook", Toast.LENGTH_LONG).show()
            } catch (e: IOException) {
                Toast.makeText(context, "Instale Facebook", Toast.LENGTH_LONG).show()
            }
        }
        btnTwit.setOnClickListener {
            try{
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "¡La mejor app de videojuegos! GAMELAND")
                intent.setPackage("com.twitter.android")
                startActivity(Intent.createChooser(intent,
                    "Using: "))
                Toast.makeText(context, "Instale Twitter", Toast.LENGTH_LONG).show()
            } catch (e: IOException) {
                Toast.makeText(context, "Instale Twitter", Toast.LENGTH_LONG).show()
            }
        }
        btnWasap.setOnClickListener {
            try{
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, "¡La mejor app de videojuegos! GAMELAND")
                intent.setPackage("com.whatsapp")
                startActivity(Intent.createChooser(intent,
                    "Using: "))
                Toast.makeText(context, "Instale WhatsApp", Toast.LENGTH_LONG).show()
            } catch (e: IOException) {
                Toast.makeText(context, "Instale WhatsApp", Toast.LENGTH_LONG).show()
            }
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
            CompartirFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}