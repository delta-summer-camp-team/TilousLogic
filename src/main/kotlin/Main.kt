import com.delta.*

fun main() {
    val board = GameBoard(5)
    val tilous = Tilous(board)

<<<<<<< HEAD
    board.set(0, 0, PlayerID.PLAYER_1);
    board.set(1, 1, PlayerID.PLAYER_1);
=======

    board.set(0, 0, PlayerID.PLAYER_1)
    board.set(0, 1, PlayerID.PLAYER_1)
    board.set(2, 2, PlayerID.PLAYER_1);
    board.set(1, 1, PlayerID.PLAYER_1);
    board.set(2, 1, PlayerID.PLAYER_1);
    board.set(3, 1, PlayerID.PLAYER_1);
    board.set(3, 2, PlayerID.PLAYER_1);
    board.set(3, 3, PlayerID.PLAYER_1);
    board.set(2, 3, PlayerID.PLAYER_1);
    board.set(1, 3, PlayerID.PLAYER_1);
    board.set(1, 2, PlayerID.PLAYER_1);
>>>>>>> 40deaef (almost removeUnstableCells())

//    board.board.forEach {
//        var str = ""
//        it.forEach { it2 ->
//            str += if (it2 == null) 0 else (mapOf(PlayerID.PLAYER_1 to 1, PlayerID.PLAYER_2 to 2, PlayerID.PLAYER_3 to 3, PlayerID.PLAYER_4 to 4))[it2];
//        }
//        println(str)
//    }
    //println(tilous.isSuperStable(2, 2))
    tilous.removeUnstableCells()
}
