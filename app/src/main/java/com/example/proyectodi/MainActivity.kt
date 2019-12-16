package com.example.proyectodi

import Adaptador.PistaRecyclerViewAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


import Modelos.*
import Adaptador.*
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import com.example.proyectodi.R
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var pistas: List<Pista>? = null
        set(value) {
            field = value

            if (value != null) {
                val adapter = PistaRecyclerViewAdapter(value)
                pistas_list.adapter = adapter
                setRecyclerViewClickListener()
            }
        }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = getString(R.string.app_name)


        pistas = Pistas.list()



        //Asigna el contenido del array al recycler
        pistas_list.layoutManager = GridLayoutManager(this, 2)
        pistas_list.itemAnimator = DefaultItemAnimator()
        pistas = Pistas.list()
    }
    fun setRecyclerViewClickListener() {

        // Si alguien pulsa un elemento del RecyclerView
        val adapter = pistas_list?.adapter as? PistaRecyclerViewAdapter
        adapter?.onClickListener = View.OnClickListener {

            val pistaIndex = pistas_list.getChildAdapterPosition(it)

            val intent = PistaActivity.intent(this, pistaIndex)
            startActivity(intent)
        }
    }

}
