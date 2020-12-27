package com.lek.arcade.game

import android.graphics.Canvas

interface IGameCallback {
    fun onPause()
    fun onResume()
    fun onDestroy()
    fun update()
    fun drawOnCanvas(canvas: Canvas, paused: Boolean)
}
