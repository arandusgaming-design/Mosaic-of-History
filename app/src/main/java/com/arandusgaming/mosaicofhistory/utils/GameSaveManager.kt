package com.arandusgaming.mosaicofhistory.utils

import android.content.Context
import android.content.SharedPreferences
import com.arandusgaming.mosaicofhistory.game.GameEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class GameSaveManager(private val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "game_progress",
        Context.MODE_PRIVATE
    )

    companion object {
        const val KEY_CURRENT_LEVEL = "current_level"
        const val KEY_CURRENT_CONTINENT = "current_continent"
        const val KEY_TOTAL_SCORE = "total_score"
        const val KEY_LAST_PLAYED = "last_played"
        const val KEY_GAME_STATE = "game_state_"
        const val KEY_LEVEL_COMPLETE = "level_"
        const val KEY_LEVEL_STARS = "stars_"
        const val KEY_TOTAL_TIME_PLAYED = "total_time_played"
    }

    fun saveProgress(level: Int, continent: String, totalScore: Int) {
        prefs.edit().apply {
            putInt(KEY_CURRENT_LEVEL, level)
            putString(KEY_CURRENT_CONTINENT, continent)
            putInt(KEY_TOTAL_SCORE, totalScore)
            putLong(KEY_LAST_PLAYED, System.currentTimeMillis())
            apply()
        }
    }

    fun saveGameState(levelId: Int, gameEngine: GameEngine) {
        val gameState = JSONObject().apply {
            put("placedPieces", gameEngine.placedPieces)
            put("score", gameEngine.score)
            put("hintsUsed", gameEngine.hintsUsed)
            put("gameTime", gameEngine.getGameTime())
            put("gridSize", gameEngine.gridSize)
        }
        
        prefs.edit().apply {
            putString(KEY_GAME_STATE + levelId, gameState.toString())
            apply()
        }
    }

    fun saveLevelCompletion(levelId: Int, stars: Int, timeTaken: Long) {
        prefs.edit().apply {
            putBoolean(KEY_LEVEL_COMPLETE + levelId, true)
            putInt(KEY_LEVEL_STARS + levelId, stars)
            putLong("time_$levelId", timeTaken)
            apply()
        }
    }

    fun getLevelCompletion(levelId: Int): Boolean {
        return prefs.getBoolean(KEY_LEVEL_COMPLETE + levelId, false)
    }

    fun getLevelStars(levelId: Int): Int {
        return prefs.getInt(KEY_LEVEL_STARS + levelId, 0)
    }

    fun getCurrentLevel(): Int {
        return prefs.getInt(KEY_CURRENT_LEVEL, 1)
    }

    fun getCurrentContinent(): String {
        return prefs.getString(KEY_CURRENT_CONTINENT, "Asia") ?: "Asia"
    }

    fun getTotalScore(): Int {
        return prefs.getInt(KEY_TOTAL_SCORE, 0)
    }

    fun getTotalPlayTime(): Long {
        return prefs.getLong(KEY_TOTAL_TIME_PLAYED, 0)
    }

    fun addPlayTime(milliseconds: Long) {
        val current = getTotalPlayTime()
        prefs.edit().putLong(KEY_TOTAL_TIME_PLAYED, current + milliseconds).apply()
    }

    fun getLastPlayedTime(): Long {
        return prefs.getLong(KEY_LAST_PLAYED, 0)
    }

    fun clearAllProgress() {
        prefs.edit().clear().apply()
    }

    fun getCompletedLevelsCount(): Int {
        var count = 0
        for (i in 1..GameConstants.TOTAL_LEVELS) {
            if (getLevelCompletion(i)) count++
        }
        return count
    }

    fun getTotalStars(): Int {
        var totalStars = 0
        for (i in 1..GameConstants.TOTAL_LEVELS) {
            totalStars += getLevelStars(i)
        }
        return totalStars
    }
}
