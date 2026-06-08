package com.arandusgaming.mosaicofhistory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historical_figures")
data class HistoricalFigure(
    @PrimaryKey val id: Int,
    val name: String,
    val nameAr: String,
    val continent: String,
    val level: Int,
    val birthYear: String,
    val deathYear: String,
    val biography: String,
    val biographyAr: String,
    val achievements: String,
    val achievementsAr: String,
    val funFact: String,
    val funFactAr: String,
    val imageUrl: String,
    val difficulty: Int
)

@Entity(tableName = "player_progress")
data class PlayerProgress(
    @PrimaryKey val id: Int = 1,
    val totalLevelsCompleted: Int = 0,
    val totalScore: Int = 0,
    val currentLevel: Int = 1,
    val currentContinent: String = "Asia",
    val totalPlayTime: Long = 0L
)

@Entity(tableName = "level_achievement")
data class LevelAchievement(
    @PrimaryKey val levelId: Int,
    val figureId: Int,
    val isCompleted: Boolean = false,
    val stars: Int = 0,
    val completedTime: Long = 0L
)
