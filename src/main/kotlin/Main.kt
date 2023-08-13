import com.delta.*
import com.google.gson.Gson

fun main() {
    val tilous = Tilous(GameBoard(3))

    tilous.placeCell(0, 1, PlayerID.PLAYER_1)

    tilous.finishPlayersTurn(PlayerID.PLAYER_1)
    tilous.finishPlayersTurn(PlayerID.PLAYER_2)
    tilous.finishPlayersTurn(PlayerID.PLAYER_3)
    tilous.finishPlayersTurn(PlayerID.PLAYER_4)

    println(tilous.currentPlayer)
    println(tilous)

    println(tilous.getBoardSize())
}
