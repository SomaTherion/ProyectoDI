package com.example.proyectodi

import Modelos.Bolera
import Modelos.Menus
import Modelos.Pistas
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*

class PistaDetailActivity : AppCompatActivity() {

    companion object {

        val EXTRA_PISTA_INDEX = "EXTRA_TABLE_INDEX"
        val EXTRA_MENU_INDEX = "EXTRA_MENU_INDEX"
        val EXTRA_DELETE_MENU = "EXTRA_DELETE_MENU"
        val EXTRA_NOTE = "EXTRA_NOTE"
        val EXTRA_MENU_POSITION = "EXTRA_MENU_POSITION"

        fun intent(context: Context, pistaIndex: Int, menuIndex: Int, deleteMode: Boolean, note: String?, menuPosition: Int): Intent {
            val intent = Intent(context, PistaDetailActivity::class.java)
            intent.putExtra(EXTRA_PISTA_INDEX, pistaIndex)
            intent.putExtra(EXTRA_MENU_INDEX, menuIndex)
            intent.putExtra(EXTRA_DELETE_MENU, deleteMode)
            intent.putExtra(EXTRA_NOTE, note)
            intent.putExtra(EXTRA_MENU_POSITION, menuPosition)

            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pista_detail)

        // Back button
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Data
        val pista = Pistas.getPista(intent.getIntExtra(EXTRA_PISTA_INDEX, 0))
        val menu = Menus.getMenu(intent.getIntExtra(EXTRA_MENU_INDEX, 0))
        val delete = intent.getBooleanExtra(EXTRA_DELETE_MENU, false)
        val note = intent.getStringExtra(EXTRA_NOTE)
        val menuPosition = intent.getIntExtra(EXTRA_MENU_POSITION, 0)

        val descriptionTextView = findViewById<TextView>(R.id.menuDescriptionTextView)
        val imageView = findViewById<ImageView>(R.id.menuImageView)
        val creditsTextView = findViewById<TextView>(R.id.menuCreditsTextView)
        val actionButton = findViewById<Button>(R.id.menuActionButton)
        val notesEditText = findViewById<EditText>(R.id.menuNotesEditText)
        if(delete) {
            notesEditText.isEnabled = false
            notesEditText.setText(note)
            actionButton.text = getString(R.string.remove)
        } else {
            actionButton.text = getString(R.string.add)
        }

        title = menu.title
        descriptionTextView?.text = menu.description
        creditsTextView?.text = String.format(getString(R.string.menu_credits), menu.price.toString())
        imageView?.setImageResource(menu.photoSrc)

        actionButton.setOnClickListener {
            if(delete) {
                Bolera.removeMenu(pista, menuPosition)
                val returnIntent = Intent()
                setResult(Activity.RESULT_OK, returnIntent)
            } else {
                Bolera.addMenu(pista, menu, notesEditText.text.toString())
            }

            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
                android.R.id.home -> {
                this.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
