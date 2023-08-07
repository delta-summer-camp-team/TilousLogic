import com.delta.*
import com.google.gson.Gson

fun main() {
    val tilous = Tilous(GameBoard(6))
    println(tilous.getBoardSize())
}
