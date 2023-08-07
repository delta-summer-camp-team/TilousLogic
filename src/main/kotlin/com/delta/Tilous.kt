package com.delta

import com.google.gson.Gson
import kotlin.math.max

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

    fun getNextPlayer(): PlayerID = TODO()

    fun getWinner(): PlayerID? {
        val playingPlayers = playersStates.filterValues { it == PlayerState.PLAYING }.keys.toList()
        val lostPlayers = playersStates.filterValues { it == PlayerState.LOST }.keys.toList()

        return when {
            playingPlayers.isEmpty() -> null // No player is playing, no winner
            playingPlayers.size == 1 -> {
                // Only one player remaining, that player wins
                val winner = playingPlayers[0]
                playersStates[winner] = PlayerState.WON
                gameIsOver = true
                winner
            }

            else -> {
                // Check if all players except one have lost
                val remainingPlayers = playingPlayers - lostPlayers
                if (remainingPlayers.size == 1) {
                    val winner = remainingPlayers[0]
                    playersStates[winner] = PlayerState.WON
                    gameIsOver = true
                    winner
                } else {
                    null // More than one player remaining, no winner yet
                }
            }
        }
    }


    fun addResources(player: PlayerID) : Int{
        return 1 + countProductiveCells(player)
    }

    // Game checks and info
    fun isValidCellToPlace(row: Int, col: Int, player: PlayerID): Boolean {
        if (board.countFriendlyNeighbors(row, col, player) == 0) {
            return false
        } else if ( getPlayerResources()[player] == 0 ) {
            return false
        } else if ( getCell(row,col) == null ) {
            return true
        } else {
            val enemy = getCell(row,col)
            if (getEnemyDefense(row, col, enemy!!) <=  getPlayerResources()[player]!!) {
                return true
            } else {
                return false
            }
        }
    }
    private fun getEnemyDefense(row: Int, col: Int, enemyID: PlayerID): Int {
        val enemy = getCell(row,col)
        val enemyForce = board.countEnemyNeighbors(row, col, enemy!!)
        return (1 + max(0, enemyForce - 2))
    }

    fun isProductive(row: Int, col: Int, player: PlayerID): Boolean {
        val cellOwner = getCell(row, col)
        return player == cellOwner &&
            cellOwner.let { board.countFriendlyNeighbors(row, col, it) } == 1
    }
    fun isProductive(row: Int, col: Int): Boolean {
        val cellOwner = getCell(row, col)
        return cellOwner?.let { board.countFriendlyNeighbors(row, col, it) } == 1
    }
    fun isSuperStable(row: Int, col: Int): Boolean = TODO()

    fun getDefencePoints(row: Int, col: Int): Int = TODO()

    private fun countCellsWithCondition(condition: (Int, Int) -> Boolean): Int = TODO()
    fun countProductiveCells(player: PlayerID): Int {
        var totalProductiveCells = 0
        for (x in 0 until board.size) {
            for (y in 0 until board.size) {
                if (isProductive(x, y, player)) {
                    totalProductiveCells++
                }
            }
        }
        return totalProductiveCells
    }
    fun countFreeCells(): Int = TODO()
    fun countFriendlyCells(player: PlayerID): Int = TODO()

    fun countFriendlyNeighboursLisa(row: Int, col:Int,player: PlayerID): Int {
        var friendlyNeighbours = 0
        if ((row != 0) && (getCell(row - 1, col) == player))
            friendlyNeighbours += 1
        if ((row != (board.size-1)) && (getCell(row + 1, col) == player))
            friendlyNeighbours += 1
        if ((col != 0) && (getCell(row, col - 1) == player))
            friendlyNeighbours += 1
        if ((col != (board.size-1)) && (getCell(row, col + 1) == player))
            friendlyNeighbours += 1
        return friendlyNeighbours
    }

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
    fun placeCell(row: Int, col: Int, player: PlayerID): Boolean {
        if (player != currentPlayer)
            return false
        if (isValidCellToPlace(row, col, player) == false)
            return false
        if (getCell(row, col) == null) {
            board.set(row, col, player)
            playersResources[player] = playersResources[player]!! - 1
            return true
        } else {
            val enemy = getCell(row, col)
            val defence = getEnemyDefense(row, col, enemy!!)
            board.set(row, col, player)
            playersResources[player] = playersResources[player]!! - defence
            removeUnstableCells()
            return true
        }
        // add fun checkEndCondition
    }

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