package com.arandusgaming.mosaicofhistory.game

import android.content.Context
import android.media.MediaPlayer
import com.arandusgaming.mosaicofhistory.utils.GameConstants

class AudioManager(private val context: Context) {
    private var backgroundMusic: MediaPlayer? = null
    private var soundEffects: MediaPlayer? = null
    private var isMusicEnabled = true
    private var isSoundEnabled = true

    fun playBackgroundMusic(resourceId: Int) {
        if (!isMusicEnabled) return
        
        try {
            backgroundMusic?.stop()
            backgroundMusic?.release()
            
            backgroundMusic = MediaPlayer.create(context, resourceId)
            backgroundMusic?.isLooping = true
            backgroundMusic?.setVolume(GameConstants.MUSIC_VOLUME, GameConstants.MUSIC_VOLUME)
            backgroundMusic?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playSoundEffect(resourceId: Int) {
        if (!isSoundEnabled) return
        
        try {
            soundEffects?.release()
            soundEffects = MediaPlayer.create(context, resourceId)
            soundEffects?.setVolume(GameConstants.SFX_VOLUME, GameConstants.SFX_VOLUME)
            soundEffects?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun stopBackgroundMusic() {
        backgroundMusic?.stop()
        backgroundMusic?.release()
        backgroundMusic = null
    }

    fun pauseBackgroundMusic() {
        backgroundMusic?.pause()
    }

    fun resumeBackgroundMusic() {
        backgroundMusic?.start()
    }

    fun setMusicEnabled(enabled: Boolean) {
        isMusicEnabled = enabled
        if (!enabled) {
            pauseBackgroundMusic()
        } else {
            resumeBackgroundMusic()
        }
    }

    fun setSoundEnabled(enabled: Boolean) {
        isSoundEnabled = enabled
    }

    fun release() {
        backgroundMusic?.release()
        soundEffects?.release()
        backgroundMusic = null
        soundEffects = null
    }
}
