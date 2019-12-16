package Modelos

import com.example.proyectodi.R

object Menus {

    private val menus: List<Menu> = listOf(
        Menu(1, "Alquiler pista", "Alquiler de la pista por partida y persona\n\nNo incluye alquiler de equipo (zapatos)",
            R.drawable.menu_1, 4.0),
        Menu(2, "Alquiler Zapatos", "De uso obligatorio para usar la pista\n\nSi el cliente no tiene deberá alquilarlos", R.drawable.menu_2, 1.0),
        Menu(3, "Refresco", "Coca-Cola, Nestea, Zumos, etc", R.drawable.menu_3, 1.5),
        Menu(4, "Batidos", "Copa de helado o batido", R.drawable.menu_4, 2.0),
        Menu(5, "Menú Bocadillo", "Hamburguesa, Bocadillos\n\nIncluye bebida", R.drawable.menu_5,  5.5)

    )

    val count get() = menus.size

    fun getMenu(index: Int) = menus[index]

    fun getIndex(menu: Menu) = menus.indexOf(menu)

    fun list() = menus

}