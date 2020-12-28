package com.lek.arcade.media

object SoundBox {
    var soundManager: SoundManager? = null

    fun resume() {
        soundManager?.resume()
    }

    fun pause() {
        soundManager?.pauseBackgroundSound()
    }

    fun quit() {
        soundManager?.stopBackgroundSound()
    }
}
