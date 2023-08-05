package com.delta

import com.google.gson.Gson

class Tilous(private val board: GameBoard) {
    private val playersResources = PlayerID.values().associateWith { 1 }.toMutableMap()
    private val playersStates = PlayerID.values().associateWith { PlayerState.PLAYING }.toMutableMap()

    private val nextPlayersMap: Map<PlayerID, PlayerID> = mapOf() //TODO change it!
    var currentPlayer = PlayerID.PLAYER_1
        private set
    var gameIsOver = false
        private set

    /* TODO: Use "init" syntax here to place one cell for each player in the corners */

    // Info about board
    fun getCell(row: Int, col: Int): PlayerID? = board[row, col]
    fun getBoardSize(): Int = board.size

    // Info about players
    fun getPlayerResources() = playersResources.toMap()
    fun getPlayerStates() = playersStates.toMap()
    fun isInGame () : List<PlayerID> = playersStates.filter {it.value == PlayerState.PLAYING}.keys.toList()
    fun getCurrentPlayer (): PlayerID = currentPlayer
    fun getNextPlayer(): PlayerID {
        val currentIndex = isInGame().indexOf(currentPlayer)
        val nextIndex = (currentIndex + 1) % isInGame().size
        return isInGame()[nextIndex]
        }
    
    fun getWinner(): PlayerID = TODO()

    // Game checks and info
    fun isValidCellToPlace(row: Int, col: Int, player: PlayerID): Boolean = TODO()

    fun isProductive(row: Int, col: Int, player: PlayerID): Boolean = TODO()
    fun isProductive(row: Int, col: Int): Boolean = TODO()
    fun isSuperStable(row: Int, col: Int): Boolean = TODO()

    fun getDefencePoints(row: Int, col: Int): Int = TODO()

    private fun countCellsWithCondition(condition: (Int, Int) -> Boolean): Int = TODO()
    fun countProductiveCells(player: PlayerID): Int = TODO()
    fun countFreeCells(): Int = TODO()
    fun countFriendlyCells(player: PlayerID): Int = TODO()

    fun getSuperStableCells(player: PlayerID): List<Pair<Int, Int>> = TODO("Later...")
    fun getUnstableCells(player: PlayerID): List<Pair<Int, Int>> = TODO("Later...")
    fun getStableCells(player: PlayerID): List<Pair<Int, Int>> = TODO("Later...")

    /**
     * This is a main function!
     *
     * Check if game is over
     * If not -- check if player can place a cell here
     * Don't forget about resources
     * If everything is ok, place the cell AND update everything:
     * player's resources, board state and so on.
     * Use already implemented methods.
     *
     *
     * @return 'true' is the cell was successfully placed, 'false' if not
     */
    fun placeCell(row: Int, col: Int, player: PlayerID): Boolean = false

    /**
     * Another main function!
     *
     * Handles request of a player to finish theirs turn.
     * We have to check here if the game was over,
     * update resources and
     * change current player
     *
     * @return 'true' if succeeded
     */
    fun finishPlayersTurn(player: PlayerID): Boolean = false

    // Internal game actions
    private fun removeUnstableCells(): Nothing = TODO()
    private fun updatePlayerStates(): Nothing = TODO()

    fun toJson() = Gson().toJson(this)
}