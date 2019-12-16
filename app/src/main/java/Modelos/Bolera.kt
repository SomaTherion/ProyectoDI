package Modelos

object Bolera {

    // Sesión del restaurante. Mapa con índice de mesa y listado de comida
    private var session: MutableMap<Int, MutableList<SessionMenu>> = hashMapOf()

    fun addMenu(pista: Pista, menu: Menu, notes: String) {
        pista.addMenu(menu)
        if(!session.containsKey(pista.number)) {
            session[pista.number] = arrayListOf()
        }
        session[pista.number]?.add(SessionMenu(menu, notes))
    }

    fun removeMenu(pista: Pista, menuIndex: Int) {
        pista.getMenus().removeAt(menuIndex)
        session[pista.number]!!.removeAt(menuIndex)
    }

    fun removeMenus(pista: Pista) {
        pista.removeMenus()
        session.remove(pista.number)
    }

    fun getMenus(pista: Pista): List<SessionMenu> {
        if(session.containsKey(pista.number) && !session[pista.number]!!.isEmpty()) {
            return session[pista.number]!!
        }
        return arrayListOf()
    }

}

// Wrapper de meús para fijar notas del mismo
data class SessionMenu(private val menu: Menu, var notes: String)