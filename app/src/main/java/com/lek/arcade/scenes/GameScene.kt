package com.lek.arcade

import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import com.lek.arcade.media.SoundManager
import com.lek.arcade.core.helper.ViewDimention
import com.lek.arcade.entities.dialpad.DialPadBox
import com.lek.arcade.entities.ship.Exhaust
import com.lek.arcade.entities.ship.Ship
import com.lek.arcade.entities.ship.PlayerBody
import com.lek.arcade.entities.ship.ShipController
import com.lek.arcade.game.IGameCallback
import com.lek.arcade.media.SoundBox
import com.lek.arcade.scenes.Scene

class GameScene(private val context: Context, private val soundManager: SoundManager) : Scene {

    private lateinit var callback: IGameCallback
    private lateinit var sound: SoundManager

    override fun setGameLifecycleCallback(callback: IGameCallback) {
        this.callback = callback
    }

    private val boxSize = context.resources.getDimensionPixelSize(R.dimen.dialpad_box_size)

    private val dialPadBox = DialPadBox(
        context,
        (ViewDimention.width - boxSize * 2).toFloat(),
        (ViewDimention.height - boxSize * 2).toFloat(),
        boxSize.toFloat()
    )

    private val playerBody = PlayerBody(
        context,
        0F, 0F,
        PlayerBody.bodyWidth(context),
        PlayerBody.bodyHeight(context)
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

    override fun displayOn(canvas: Canvas) {
        dialPadBox.draw(canvas)
        player.draw(canvas)
    }

    override fun update() {
        playerController.update()
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
