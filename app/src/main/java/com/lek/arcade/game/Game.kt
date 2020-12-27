package com.lek.arcade.game

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.lek.arcade.GameScene
import com.lek.arcade.scenes.Scene

class Game private constructor(
    context: Context,
    private val scene: Scene
) : SurfaceView(context), SurfaceHolder.Callback, IGameCallback {

    private var gameThread: GameThread? = GameThread(this)

    init {
        holder.addCallback(this)
        scene.setGameLifecycleCallback(this)
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        onResume()
    }

    override fun update() {
        scene.update()
    }

    public override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.BLACK)
        scene.displayOn(canvas)
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        onDestroy()
        onResume()
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        onDestroy()
    }

    override fun onPause() {
        gameThread?.stopRunning()
        gameThread = null
    }

    // TODO Investigate this, I mean, I am not sure if this solution is necessary
    fun weakPause() {
        gameThread?.pause()
    }

    // TODO: Investigate this. I am not sure if this solution is necessary
    fun weakResume() {
        gameThread?.resumeGame()
    }

    override fun onResume() {
        if (gameThread == null) {
            gameThread = GameThread(this)
            gameThread?.startRunning()
        } else {
            gameThread?.let {
                if (!it.isRunning()) {
                    it.setRunning(true)
                }
            }
        }
    }

    override fun onDestroy() {
        gameThread?.stopRunning()
        gameThread = null
    }

    override fun drawOnCanvas(canvas: Canvas, paused: Boolean) {
        if (!paused) {
            scene.update()
        }
        onDraw(canvas)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        scene.onTouch(event)
        invalidate()
        return true
    }

    companion object {
        fun instance(context: Context) = Game(context, GameScene(context))
    }
}
