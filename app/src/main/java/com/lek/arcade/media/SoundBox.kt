package com.lek.arcade.media

object SoundBox {
    var soundManager: SoundManager? = null
    var isPlaying = false
    fun resume() {
        soundManager?.resume()
    }

    fun pause() {
        soundManager?.pauseBackgroundSound()
    }

    fun quit() {
        isPlaying = false
        soundManager?.stopBackgroundSound()
    }
}
