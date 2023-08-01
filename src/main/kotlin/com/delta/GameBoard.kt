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
        if (freeCell(row, col)) {
            board[row][col] = player
        }
        else {
            throw IllegalArgumentException("Cell is not free")
        }
        // TODO this is unsafe, fix it!
    }

    private fun freeCell(raw: Int, col: Int) : Boolean {
        if (board[raw][col] == null) {
            return true
        }
        else {
            return false
        }
    }
    fun getNeighbors(row: Int, col: Int): List<Pair<Int, Int>> = TODO()

    fun countFriendlyNeighbors(row: Int, col: Int, player: PlayerID) : Int = TODO()
    fun countFriendlyNeighborsCorners(row: Int, col: Int, player: PlayerID): Int = TODO()
    fun countEnemyNeighbors(row: Int, col: Int, player: PlayerID): Int = TODO()

    fun isSurroundedWithFriendly(row: Int, col: Int): Boolean = TODO()
    fun isCorner(row: Int, col: Int): Boolean = TODO()

    fun toJson(): String = Gson().toJson(this)
    companion object {
        fun fromJson(json: String): GameBoard = Gson().fromJson(json, GameBoard::class.java)
    }
}