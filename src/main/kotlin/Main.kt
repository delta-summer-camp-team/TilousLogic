import com.delta.*

fun main() {
    val board = GameBoard(5)
    val tilous = Tilous(board)

    board.set(0, 0, PlayerID.PLAYER_1);
    board.set(1, 1, PlayerID.PLAYER_1);

    println(tilous.getDefencePoints(1, 1))
}