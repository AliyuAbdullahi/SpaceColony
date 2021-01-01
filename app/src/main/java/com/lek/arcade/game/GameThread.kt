package com.lek.arcade.game

import android.graphics.Canvas
import com.lek.arcade.media.SoundManager
import com.lek.arcade.core.logger.Console
import com.lek.arcade.media.SoundBox

const val FPS = 30L
const val DEFAULT_SLEEP_TIME = 20L

internal class GameThread(private val game: Game) : Thread() {

    private var running = false
    private var paused = false

    fun startRunning() {
        running = true
        start()
    }

    fun pause() {
        paused = true
    }

    fun resumeGame() {
        paused = false
    }

    fun stopRunning() {
        running = false
        try {
            join()
        }catch (e: InterruptedException) {
            println("Couldn't Stop game from runnig")
        }
    }

    override fun run() {
        val ticksPS = 1000 / FPS
        var startTime: Long
        var sleepTime: Long

        while (running) {
            game.playBackgroundSound()
            if (game.holder.surface.isValid) {
                var canvas: Canvas? = null
                startTime = System.currentTimeMillis()

                // Get current canvas from surface holder, lock it and paint on it (blocking), then release
                try {
                    canvas = game.getHolder().lockCanvas()
                    synchronized(game.getHolder()) {
                        if (canvas != null){
                            canvas.save()
                            game.update()
                            game.drawOnCanvas(canvas, paused)
                        }
                    }
                } finally {
                    if (canvas != null) {
                        canvas.restore()
                        game.getHolder().unlockCanvasAndPost(canvas)
                    }
                }

                sleepTime = ticksPS - (System.currentTimeMillis() - startTime)

                try {
                    if (sleepTime > 0) sleep(sleepTime) else sleep(DEFAULT_SLEEP_TIME)
                } catch (e: Exception) {
                    Console.error("Error in Game Thread $e", isAndroidEnvironment = true)
                }
            }
        }
    }

    fun isRunning(): Boolean {
        return running
    }

    fun setRunning(running: Boolean) {
        this.running = running
    }
}
