import com.delta.*

fun main() {
    val board = GameBoard(5)
    val tilous = Tilous(board)

    board.set(1, 1, PlayerID.PLAYER_1);

    board.set(0, 0, PlayerID.PLAYER_1);
    board.set(1, 0, PlayerID.PLAYER_1);
    board.set(2, 0, PlayerID.PLAYER_1);
    board.set(2, 1, PlayerID.PLAYER_1);
    board.set(2, 2, PlayerID.PLAYER_1);
    board.set(1, 2, PlayerID.PLAYER_1);
    board.set(0, 2, PlayerID.PLAYER_1);
    board.set(0, 1, PlayerID.PLAYER_1);

    println(tilous.getDefencePoints(1, 1))
}