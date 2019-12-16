package Adaptador

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import Modelos.Menu
import android.content.Intent
import android.view.LayoutInflater
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectodi.MenuActivity
import com.example.proyectodi.PistaActivity
import com.example.proyectodi.R




class MenuListViewAdapter(private val context: Context, private val menus: List<Menu>): BaseAdapter() {

    override fun getCount(): Int {
        return menus.count()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return menus[position]
    }

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

        var viewHolder: MenuViewHolder?
        var view: View?

        if (convertView == null) {
            view = View.inflate(context, R.layout.content_menu, null)
            viewHolder = MenuViewHolder(view)
            view.tag = viewHolder
        } else {
            viewHolder = convertView.tag as MenuViewHolder
            view = convertView
        }

        val menu = getItem(position) as Menu

        viewHolder?.nameTextView?.text = menu.title
        viewHolder?.imageView?.setImageResource(menu.photoSrc)
        viewHolder?.creditsTextView?.text = String.format(context.getString(R.string.menu_credits), menu.price.toString())



        return view!!
    }

    inner class MenuViewHolder(itemView: View) {

        val nameTextView = itemView.findViewById<TextView>(R.id.menuNameTextView)
        val imageView = itemView.findViewById<ImageView>(R.id.menuImageView)
        val creditsTextView = itemView.findViewById<TextView>(R.id.menuCreditsTextView)


        //private val context = itemView.context

    }

}