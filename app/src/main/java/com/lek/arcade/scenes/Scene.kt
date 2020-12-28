package com.lek.arcade.scenes

import android.graphics.Canvas
import android.view.MotionEvent
import com.lek.arcade.game.IGameCallback

interface Scene {
    fun displayOn(canvas: Canvas)
    fun update()
    fun pause()
    fun resume()
    fun quit()
    fun setGameLifecycleCallback(callback: IGameCallback)
    fun onTouch(event: MotionEvent): Boolean
    fun playBackgroundSound()
}
