package com.example.proyectodi

import Adaptador.MenuListViewAdapter
import Modelos.Menus
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityOptionsCompat
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    companion object {

        val EXTRA_PISTA_INDEX = "EXTRA_PISTA_INDEX"

        fun intent(context: Context, pistaIndex: Int): Intent {
            val intent = Intent(context, MenuActivity::class.java)
            intent.putExtra(PistaDetailActivity.EXTRA_PISTA_INDEX, pistaIndex)
            return intent
        }
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        title = getString(R.string.add_menu)

        // Back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Data
        val pistaIndex = intent.getIntExtra(PistaDetailActivity.EXTRA_PISTA_INDEX, 0)

        val adapter = MenuListViewAdapter(this, Menus.list())
        menus_list.adapter = adapter
        menus_list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            // Opciones especiales para navegar con vistas comunes
            val animationOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                view,
                getString(R.string.transition_to_detail)
            )

            startActivity(PistaDetailActivity.intent(this, pistaIndex, position, false, null, position), animationOptions.toBundle())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // Respond to the action bar's Up/Home button
            android.R.id.home -> {
                val returnIntent = Intent()
                setResult(Activity.RESULT_OK, returnIntent)
                this.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
