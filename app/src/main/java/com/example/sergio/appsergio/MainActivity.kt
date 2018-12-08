package com.example.sergio.appsergio

import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    HomeFragment.OnFragmentInteractionListener,
    CatalogoFragment.OnFragmentInteractionListener,
    AddJuegoFragment.OnFragmentInteractionListener,
    CompartirFragment.OnFragmentInteractionListener,
    InfoFragment.OnFragmentInteractionListener{

    override fun onFragmentInteraction(uri: Uri) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        if (savedInstanceState == null) {
            supportFragmentManager
                fragmentTransaction(HomeFragment())
            nav_view.menu.getItem(0).isChecked = true
        }
    }

    private fun fragmentTransaction(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_main, fragment)
            .commit()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.principal -> {
                nav_view.menu.getItem(1).subMenu.getItem(0).isChecked = true
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, HomeFragment.newInstance(), "rageComicList")
                    .commit()
                nav_view.menu.getItem(1).subMenu.getItem(0).isChecked = false
                nav_view.menu.getItem(1).subMenu.getItem(1).isChecked = false
                nav_view.menu.getItem(1).subMenu.getItem(2).isChecked = false
                nav_view.menu.getItem(2).subMenu.getItem(0).isChecked = false
                nav_view.menu.getItem(2).subMenu.getItem(1).isChecked = false
            }

            R.id.show_juegos -> {
                nav_view.menu.getItem(1).subMenu.getItem(0).isChecked = true
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, CatalogoFragment.newInstance(), "rageComicList")
                    .commit()
                nav_view.menu.getItem(0).isChecked = false
                nav_view.menu.getItem(1).subMenu.getItem(1).isChecked = false
                nav_view.menu.getItem(1).subMenu.getItem(2).isChecked = false
                nav_view.menu.getItem(2).subMenu.getItem(0).isChecked = false
                nav_view.menu.getItem(2).subMenu.getItem(1).isChecked = false
            }

            R.id.add_juego -> {
                nav_view.menu.getItem(1).subMenu.getItem(1).isChecked = true
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, AddJuegoFragment.newInstance(), "rageComicList")
                    .commit()
                nav_view.menu.getItem(0).isChecked = false
                nav_view.menu.getItem(1).subMenu.getItem(0).isChecked = false
                nav_view.menu.getItem(1).subMenu.getItem(2).isChecked = false
                nav_view.menu.getItem(2).subMenu.getItem(0).isChecked = false
                nav_view.menu.getItem(2).subMenu.getItem(1).isChecked = false
            }

            R.id.tienda -> {
                nav_view.menu.getItem(1).subMenu.getItem(2).isChecked = true
                fragmentTransaction(TiendaFragment())
                nav_view.menu.getItem(0).isChecked = false
                nav_view.menu.getItem(1).subMenu.getItem(0).isChecked = false
                nav_view.menu.getItem(1).subMenu.getItem(1).isChecked = false
                nav_view.menu.getItem(2).subMenu.getItem(0).isChecked = false
                nav_view.menu.getItem(2).subMenu.getItem(1).isChecked = false
            }

            R.id.share -> {
                nav_view.menu.getItem(2).subMenu.getItem(0).isChecked = true
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, CompartirFragment.newInstance(), "rageComicList")
                    .commit()
                nav_view.menu.getItem(0).isChecked = false
                nav_view.menu.getItem(1).subMenu.getItem(0).isChecked = false
                nav_view.menu.getItem(1).subMenu.getItem(1).isChecked = false
                nav_view.menu.getItem(1).subMenu.getItem(2).isChecked = false
                nav_view.menu.getItem(2).subMenu.getItem(1).isChecked = false
            }
            R.id.info -> {
                nav_view.menu.getItem(2).subMenu.getItem(1).isChecked = true
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.content_main, InfoFragment.newInstance(), "rageComicList")
                    .commit()
                nav_view.menu.getItem(0).isChecked = false
                nav_view.menu.getItem(1).subMenu.getItem(0).isChecked = false
                nav_view.menu.getItem(1).subMenu.getItem(1).isChecked = false
                nav_view.menu.getItem(1).subMenu.getItem(2).isChecked = false
                nav_view.menu.getItem(2).subMenu.getItem(0).isChecked = false
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
