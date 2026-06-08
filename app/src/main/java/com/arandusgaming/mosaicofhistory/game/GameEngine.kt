package com.arandusgaming.mosaicofhistory.game

import com.arandusgaming.mosaicofhistory.data.HistoricalFigure
import kotlin.random.Random

data class PuzzlePiece(
    val id: Int,
    val gridX: Int,
    val gridY: Int,
    var currentX: Float = 0f,
    var currentY: Float = 0f,
    var isPlaced: Boolean = false
)

class GameEngine(
    private val figure: HistoricalFigure,
    gridSize: Int
) {
    val gridSize = gridSize
    val totalPieces = gridSize * gridSize
    val puzzlePieces = mutableListOf<PuzzlePiece>()
    var score = 0
    var placedPieces = 0
    var startTime = System.currentTimeMillis()
    var hintsUsed = 0
    var maxHints = 3

    init {
        initializePuzzlePieces()
    }

    private fun initializePuzzlePieces() {
        val random = Random(System.currentTimeMillis())
        
        for (i in 0 until totalPieces) {
            val x = i % gridSize
            val y = i / gridSize
            
            val piece = PuzzlePiece(
                id = i,
                gridX = x,
                gridY = y,
                currentX = random.nextFloat() * 500f,
                currentY = random.nextFloat() * 500f,
                isPlaced = false
            )
            puzzlePieces.add(piece)
        }
    }

    fun placePiece(pieceId: Int, gridX: Int, gridY: Int): Boolean {
        val piece = puzzlePieces.find { it.id == pieceId } ?: return false
        
        if (piece.gridX == gridX && piece.gridY == gridY) {
            piece.isPlaced = true
            placedPieces++
            score += calculatePieceScore()
            return true
        }
        return false
    }

    private fun calculatePieceScore(): Int {
        val baseScore = 100
        val timeBonus = (300000 - (System.currentTimeMillis() - startTime)).coerceAtLeast(0L) / 1000
        return baseScore + timeBonus.toInt()
    }

    fun useHint(): Boolean {
        if (hintsUsed >= maxHints) return false
        hintsUsed++
        
        // Find first unplaced piece and show it
        val unplacedPiece = puzzlePieces.find { !it.isPlaced } ?: return false
        // In real implementation, this would trigger UI to show the piece
        return true
    }

    fun isGameComplete(): Boolean {
        return placedPieces == totalPieces
    }

    fun getGameTime(): Long {
        return (System.currentTimeMillis() - startTime) / 1000
    }

    fun calculateStars(): Int {
        val timeInSeconds = getGameTime()
        return when {
            timeInSeconds <= 120 -> 3  // 2 minutes
            timeInSeconds <= 300 -> 2  // 5 minutes
            else -> 1
        }
    }

    fun getProgressPercentage(): Float {
        return (placedPieces.toFloat() / totalPieces) * 100
    }
}
