package com.arandusgaming.mosaicofhistory.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoricalFigureDao {
    @Query("SELECT * FROM historical_figures WHERE continent = :continent")
    suspend fun getFiguresByContinent(continent: String): List<HistoricalFigure>

    @Query("SELECT * FROM historical_figures WHERE id = :figureId")
    suspend fun getFigureById(figureId: Int): HistoricalFigure?

    @Query("SELECT * FROM historical_figures WHERE level = :level")
    suspend fun getFigureByLevel(level: Int): HistoricalFigure?

    @Insert
    suspend fun insertFigure(figure: HistoricalFigure)

    @Insert
    suspend fun insertFigures(figures: List<HistoricalFigure>)

    @Query("SELECT COUNT(*) FROM historical_figures")
    suspend fun getFigureCount(): Int

    @Query("SELECT * FROM historical_figures ORDER BY level ASC")
    fun getAllFigures(): Flow<List<HistoricalFigure>>
}

@Dao
interface PlayerProgressDao {
    @Query("SELECT * FROM player_progress WHERE id = 1")
    fun getProgress(): Flow<PlayerProgress>

    @Query("SELECT * FROM player_progress WHERE id = 1")
    suspend fun getProgressSuspend(): PlayerProgress?

    @Update
    suspend fun updateProgress(progress: PlayerProgress)

    @Insert
    suspend fun insertProgress(progress: PlayerProgress)
}

@Dao
interface LevelAchievementDao {
    @Query("SELECT * FROM level_achievement WHERE levelId = :levelId")
    suspend fun getAchievement(levelId: Int): LevelAchievement?

    @Query("SELECT * FROM level_achievement")
    fun getAllAchievements(): Flow<List<LevelAchievement>>

    @Query("SELECT COUNT(*) FROM level_achievement WHERE isCompleted = 1")
    suspend fun getCompletedLevelsCount(): Int

    @Update
    suspend fun updateAchievement(achievement: LevelAchievement)

    @Insert
    suspend fun insertAchievement(achievement: LevelAchievement)

    @Query("SELECT SUM(stars) FROM level_achievement WHERE isCompleted = 1")
    suspend fun getTotalStars(): Int?
}
