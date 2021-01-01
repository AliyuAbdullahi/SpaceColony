package com.lek.arcade

import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import com.lek.arcade.media.SoundManager
import com.lek.arcade.core.helper.ViewDimention
import com.lek.arcade.entities.dialpad.DialPadBox
import com.lek.arcade.entities.enemy.EnemiesStore
import com.lek.arcade.entities.ship.Exhaust
import com.lek.arcade.entities.ship.Ship
import com.lek.arcade.entities.ship.ShipBody
import com.lek.arcade.entities.ship.ShipController
import com.lek.arcade.game.IGameCallback
import com.lek.arcade.media.SoundBox
import com.lek.arcade.scenes.Scene

class GameScene(private val context: Context, private val soundManager: SoundManager) : Scene {

    private lateinit var callback: IGameCallback
    private lateinit var sound: SoundManager
    private val boxSize = context.resources.getDimensionPixelSize(R.dimen.dialpad_box_size)


    private val dialPadBox = DialPadBox(
        context,
        (ViewDimention.width - boxSize * 2).toFloat(),
        (ViewDimention.height - boxSize * 2).toFloat(),
        boxSize.toFloat()
    )

    private val playerBody = ShipBody(
        context,
        0F, 0F,
        ShipBody.bodyWidth(context),
        ShipBody.bodyHeight(context)
    )
    private val exhaust = Exhaust(
        context,
        0F, 0F, Exhaust.bodyWidth(context),
        Exhaust.bodyHeight(context)
    )

    private val player = Ship(
        context,
        0F,
        60F,
        Ship.shipWidth(context).toFloat(),
        Ship.shipHeight(context).toFloat(),
        playerBody,
        exhaust,
        soundManager
    )

    private val playerController = ShipController(player)

    init {
        EnemiesStore.initEnemies(context, 10)
    }

    override fun setGameLifecycleCallback(callback: IGameCallback) {
        this.callback = callback
    }

    override fun displayOn(canvas: Canvas) {
        player.draw(canvas)

        EnemiesStore.allOf { it.draw(canvas) }

        dialPadBox.draw(canvas)
    }

    override fun update() {
        playerController.update()
        EnemiesStore.clearHurtEnemy()
        EnemiesStore.allOf { it.update() }
    }

    override fun pause() {
        sound.pauseBackgroundSound()
        callback.onPause()
        soundManager.pauseBackgroundSound()
    }

    override fun resume() {
        sound.resume()
        callback.onResume()
        soundManager.resume()
    }

    override fun quit() {
        callback.onDestroy()
        sound.stopBackgroundSound()
        soundManager.stopBackgroundSound()
    }

    override fun onTouch(event: MotionEvent): Boolean {
        dialPadBox.registerTouchEvent(event, playerController)
        return true
    }

    override fun playBackgroundSound() {
        SoundBox.soundManager = SoundManager.instance(context)
        SoundBox.soundManager?.playLongTrackAsync(SoundManager.BackgroundSound.LEVEL1)
    }
}
