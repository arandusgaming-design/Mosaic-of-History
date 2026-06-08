package com.arandusgaming.mosaicofhistory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [HistoricalFigure::class, PlayerProgress::class, LevelAchievement::class],
    version = 1,
    exportSchema = false
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun historicalFigureDao(): HistoricalFigureDao
    abstract fun playerProgressDao(): PlayerProgressDao
    abstract fun levelAchievementDao(): LevelAchievementDao

    companion object {
        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameDatabase::class.java,
                    "game_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
