package com.arandusgaming.mosaicofhistory.utils

object GameConstants {
    // Grid sizes
    const val GRID_5x5 = 5
    const val GRID_6x6 = 6
    const val GRID_7x7 = 7
    const val GRID_8x8 = 8
    const val GRID_9x9 = 9

    // Level ranges
    const val LEVELS_1_20_GRID = GRID_5x5
    const val LEVELS_21_40_GRID = GRID_6x6
    const val LEVELS_41_60_GRID = GRID_7x7
    const val LEVELS_61_80_GRID = GRID_8x8
    const val LEVELS_81_100_GRID = GRID_9x9

    // Total levels
    const val TOTAL_LEVELS = 100

    // Time limits (in seconds)
    const val TIME_LIMIT_3_STARS = 120  // 2 minutes
    const val TIME_LIMIT_2_STARS = 300  // 5 minutes

    // Continents
    val CONTINENTS = listOf(
        "Asia",
        "Africa",
        "Europe",
        "North America",
        "South America",
        "Oceania"
    )

    // Levels per continent
    const val ASIA_LEVELS = 20
    const val AFRICA_LEVELS = 20
    const val EUROPE_LEVELS = 30
    const val NORTH_AMERICA_LEVELS = 15
    const val SOUTH_AMERICA_LEVELS = 10
    const val OCEANIA_LEVELS = 5

    // Hints
    const val HINTS_PER_LEVEL = 3

    // Music and Sound
    const val MUSIC_VOLUME = 0.7f
    const val SFX_VOLUME = 1.0f

    // Database
    const val DATABASE_NAME = "mosaic_history_db"
}
