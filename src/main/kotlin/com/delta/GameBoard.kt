package com.delta

import com.google.gson.Gson

class GameBoard(val size: Int) {
    val board: Array<Array<PlayerID?>> = Array(size) { Array(size) { null } }

    fun isValidCoordinate(x: Int, y: Int): Boolean = (x in 0 until size) and (y in 0 until size)

    /**
     * Allows to write `varName[i, j]` to get cell in position `(i, j)`.
     *
     * Example:
     *
     * ```
     * val board = GameBoard(5)
     * val a = board[1, 1] // return cell in position (1, 1)
     * ```
     *
     * To get cells inside methods of this class, use `this[i, j]`.
     */
    operator fun get(row: Int, col: Int) : PlayerID? = board.getOrNull(row)?.getOrNull(col)

    /**
     * Allows to write `varName[i, j] = ...` to set cell in position (i, j).
     *
     * Example:
     * ```
     * val board = GameBoard(5)
     * board[1, 1] = PLAYER_1
     * ```
     *
     * To set cells inside methods of this class, use `this[i, j] = ...`.
     */
    operator fun set(row: Int, col: Int, player: PlayerID) {
        if (isValidCoordinate(row, col)){
            board[row][col] = player
        } else {
            throw IllegalArgumentException("Can't place here.") // TODO: Add handling to this exception
        }
    }

    private fun freeCell(raw: Int, col: Int) : Boolean = get(raw, col) == null
    fun getNeighbors(row: Int, col: Int): List<Pair<Int, Int>> {
        return listOf(
            Pair(row - 1, col),
            Pair(row + 1, col),
            Pair(row, col - 1),
            Pair(row, col + 1),
        ).filter { (x, y) -> isValidCoordinate(x, y) }
    }

    fun getAllNeighbors(row: Int, col: Int): List<Pair<Int, Int>> {
        return listOf(
            Pair(row - 1, col),
            Pair(row + 1, col),
            Pair(row, col - 1),
            Pair(row, col + 1),
            Pair(row - 1, col - 1),
            Pair(row + 1, col + 1),
            Pair(row - 1, col + 1),
            Pair(row + 1, col - 1),
        ).filter { (x, y) -> isValidCoordinate(x, y) }
    }

    fun countFriendlyNeighbors(row: Int, col: Int, player: PlayerID) : Int {
        return getNeighbors(row, col).count { (x, y) -> get(x, y) == player }
    }
    fun countFriendlyNeighborsCorners(row: Int, col: Int, player: PlayerID): Int {
        return getAllNeighbors(row, col).count { (x, y) -> get(x, y) == player}
    }
    fun countEnemyNeighbors(row: Int, col: Int, player: PlayerID): Int{
        return getNeighbors(row, col).count { (x, y) -> get(x, y) != player && get(x, y) != null}
    }

    fun isSurroundedWithFriendly(row: Int, col: Int, player: PlayerID): Boolean {
        return countFriendlyNeighborsCorners(row, col, player ) == 8
    }
    fun isCorner(row: Int, col: Int): Boolean {
        return (row == 0 && col == 0) ||
                (row == 0 && col == size - 1) ||
                (row == size - 1 && col == 0) ||
                (row == size - 1 && col == size - 1)
    }

    fun toJson(): String = Gson().toJson(this)
    companion object {
        fun fromJson(json: String): GameBoard = Gson().fromJson(json, GameBoard::class.java)
    }
}