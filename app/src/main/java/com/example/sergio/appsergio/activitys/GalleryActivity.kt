package com.example.sergio.appsergio.activitys

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.sergio.appsergio.R


/**
 * Created by User on 1/2/2018.
 */

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        Log.d(TAG, "onCreate: started.")

        getIncomingIntent()
    }

    private fun getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.")

        if (intent.hasExtra("image_url") && intent.hasExtra("image_name")) {
            Log.d(TAG, "getIncomingIntent: found intent extras.")

            val imageUrl = intent.getStringExtra("image_url")
            val imageName = intent.getStringExtra("image_name")
            val genero = intent.getStringExtra("genero")

            setImage(imageUrl, imageName, genero)
        }
    }


    private fun setImage(imageUrl: String, imageName: String, genero: String) {
        Log.d(TAG, "setImage: setting te image and name to widgets.")

        val name = findViewById<TextView>(R.id.image_description)
        name.setText(imageName)

        val image = findViewById<ImageView>(R.id.image)
        Glide.with(this)
            .asBitmap()
            .load(imageUrl)
            .into(image)

        val gif = findViewById<ImageView>(R.id.imageGif)
        if(genero.equals("Accion")){
            val urlGif = "https://i.gifer.com/TJEM.gif"
            val uri = Uri.parse(urlGif)
            Glide.with(this).load(uri).into(gif)
        } else if (genero.equals("Aventura")){
            val urlGif = "http://66.media.tumblr.com/b5b22c5d826073bf09aacdf7b0d1b9dd/tumblr_mtclyfzuUJ1sicb2jo1_400.gif"
            val uri = Uri.parse(urlGif)
            Glide.with(this).load(uri).into(gif)
        } else if (genero.equals("Rol")){
            val urlGif = "http://orig15.deviantart.net/a058/f/2013/267/5/1/dark_soulss_by_alo81-d6nrmkr.gif"
            val uri = Uri.parse(urlGif)
            Glide.with(this).load(uri).into(gif)
        } else if (genero.equals("Deportes")){
            val urlGif = "https://media.giphy.com/media/MZuunVwqv59jW/giphy.gif"
            val uri = Uri.parse(urlGif)
            Glide.with(this).load(uri).into(gif)
        } else if (genero.equals("Carreras")){
            val urlGif = "https://media.giphy.com/media/WdubD0JFIN16M/giphy.gif"
            val uri = Uri.parse(urlGif)
            Glide.with(this).load(uri).into(gif)
        } else {
            val urlGif = "https://giffiles.alphacoders.com/206/206690.gif"
            val uri = Uri.parse(urlGif)
            Glide.with(this).load(uri).into(gif)
        }
    }

    companion object {

        private val TAG = "GalleryActivity"
    }

}