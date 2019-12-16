package Modelos

object Pistas {

    private val pistas: List<Pista> = listOf(
        Pista(1, "Pista 1"),
        Pista(2, "Pista 2"),
        Pista(3, "Pista 3"),
        Pista(4, "Pista 4"),
        Pista(5, "Pista 5"),
        Pista(6, "Pista 6"),
        Pista(7, "Pista 7"),
        Pista(8, "Pista 8"),
        Pista(9, "Pista 9"),
        Pista(10, "Pista 10")
    )

    val count get() = pistas.size

    fun getPista(index: Int) = pistas[index]

    fun getIndex(pista: Pista) = pistas.indexOf(pista)


    fun list() = pistas

}