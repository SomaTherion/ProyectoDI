package Modelos

data class Pista(val number: Int, val name: String) {

    private var menus: MutableList<Menu> = mutableListOf()

    fun addMenu(menu: Menu) {
        menus.add(menu)
    }

    fun removeMenus() {
        menus = mutableListOf()
    }

    fun getMenus() = menus

}