package com.example.proyectodi

import Adaptador.MenuListViewAdapter
import Modelos.*
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_pista.*

class PistaActivity : AppCompatActivity() {

    private val DETALLES_PISTA = 1
    private lateinit var pista: Pista

    companion object {

        private val EXTRA_PISTA_INDEX = "EXTRA_PISTA_INDEX"

        fun intent(context: Context, pistaIndex: Int): Intent {
            val intent = Intent(context, PistaActivity::class.java)
            intent.putExtra(EXTRA_PISTA_INDEX, pistaIndex)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pista)
        //setSupportActionBar(toolbar)

        // Back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Data
        val pistaIndex = intent.getIntExtra(EXTRA_PISTA_INDEX, 0)
        pista = Pistas.getPista(pistaIndex)

        title = String.format(getString(R.string.pista_number), pista.number)


        add_button.setOnClickListener(View.OnClickListener {

            val intent = MenuActivity.intent(this, pistaIndex)
            startActivityForResult(intent, DETALLES_PISTA)
        })

        cargaPista()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.pista_menu, menu)
        return true
    }

    private fun cargaPista() {

        val sessionMenus = Bolera.getMenus(pista)


        val adapter = MenuListViewAdapter(this, pista.getMenus())

        table_menus_list.adapter = adapter
        table_menus_list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            // Opciones especiales para navegar con vistas comunes
            val animationOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                view,
                getString(R.string.transition_to_detail)
            )

            val menu = pista.getMenus()[position]
            startActivityForResult(PistaDetailActivity.intent(this, pista.number - 1, menu.number - 1, true, sessionMenus[position].notes, position), DETALLES_PISTA, animationOptions.toBundle())
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            android.R.id.home -> {
                this.onBackPressed()
                return true
            }
            R.id.menu_calculate -> {

                var total: Double = 0.0
                for(menu in pista.getMenus()) {
                    total += menu.price
                }
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Total:")
                builder.setMessage(String.format(getString(R.string.menu_credits), total.toString()))
               // builder.setPositiveButton("Ok"){dialog, which ->
                    // Do nothing
                //}
                val dialog: AlertDialog = builder.create()
                dialog.show()
                return true
            }
            R.id.menu_delete -> {
                pista.removeMenus()
                cargaPista()
                //val parentLayout = findViewById<View>(android.R.id.content)
                //Snackbar.make(parentLayout, "Se han borrado todos los consumos", Snackbar.LENGTH_LONG).show()
                Toast.makeText(this,"Se han borrado todos los consumos",Toast.LENGTH_SHORT ).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            DETALLES_PISTA -> {
                if (resultCode == Activity.RESULT_OK) {
                    cargaPista()
                }
            }
        }
    }



}
