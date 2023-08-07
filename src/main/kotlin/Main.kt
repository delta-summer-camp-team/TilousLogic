import com.delta.*
import com.google.gson.Gson

fun main() {
    val board = GameBoard(10)
    val tilous = Tilous(board)

    val _temp = "[[1,1,2,2,2,2,4,4,2,2],[1,1,1,1,2,2,4,2,2,0],[0,1,2,2,2,2,4,2,2,0],[0,1,1,1,0,0,4,0,2,0],[0,0,0,1,0,0,4,0,0,0],[1,1,1,0,0,0,4,0,0,0],[0,1,1,0,0,0,4,4,4,0],[0,0,3,3,0,0,4,4,4,0],[3,3,3,3,0,0,4,4,4,4],[3,3,3,0,0,0,0,0,4,0]]"
    val _tempArray = Gson().fromJson(_temp, Array<Array<Int>>::class.java);

    val _temp2 = mapOf(1 to PlayerID.PLAYER_1, 2 to PlayerID.PLAYER_2, 3 to PlayerID.PLAYER_3, 4 to PlayerID.PLAYER_4);
    for (i in 0 until _tempArray.size) {
        for (j in 0 until _tempArray[i].size) {
            val curr = _tempArray[i][j];
            if (curr == 0) continue;
            board[i, j] = _temp2[curr]!!
        }
    }

    tilous.removeUnstableCells()
}


/* OLD

//    board.set(0, 0, PlayerID.PLAYER_1)
//    board.set(0, 1, PlayerID.PLAYER_1)
//    board.set(2, 2, PlayerID.PLAYER_1)
//    board.set(1, 1, PlayerID.PLAYER_1)
//    board.set(2, 1, PlayerID.PLAYER_1)
//    board.set(3, 1, PlayerID.PLAYER_1)
//    board.set(3, 2, PlayerID.PLAYER_1)
//    board.set(3, 3, PlayerID.PLAYER_1)
//    board.set(2, 3, PlayerID.PLAYER_1)
//    board.set(1, 3, PlayerID.PLAYER_1)
//    board.set(1, 2, PlayerID.PLAYER_1)

//    board.board.forEach {
//        var str = ""
//        it.forEach { it2 ->
//            str += if (it2 == null) 0 else (mapOf(PlayerID.PLAYER_1 to 1, PlayerID.PLAYER_2 to 2, PlayerID.PLAYER_3 to 3, PlayerID.PLAYER_4 to 4))[it2];
//        }
//        println(str)
//    }
//println(tilous.isSuperStable(2, 2))

*/