package Adaptador

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import Modelos.*
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectodi.PistaActivity
import com.example.proyectodi.R


class PistaRecyclerViewAdapter(private val pistas: List<Pista>): RecyclerView.Adapter<PistaRecyclerViewAdapter.PistaViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PistaViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_pista, parent, false)

        // Notificamos al onClickListener
        view.setOnClickListener {
            onClickListener?.onClick(it)
        }

        return PistaViewHolder(view)
    }

    override fun getItemCount() = pistas.size

    override fun onBindViewHolder(holder: PistaViewHolder, position: Int) {
        holder.bindPista(pistas[position])
    }

    inner class PistaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val pistaTitleText = itemView.findViewById<TextView?>(R.id.pistaTitleTextView)
        private val pistaNameText = itemView.findViewById<TextView?>(R.id.pistaNameTextView)

        private val context = itemView.context

        fun bindPista(pista: Pista) {

            // Actualizamos la vista con el modelo
            pistaTitleText?.text = String.format(context.getString(R.string.pista_number), pista.number)
            pistaNameText?.text = pista.name
        }
    }

}