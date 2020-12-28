package com.lek.arcade.entities.ship

import android.content.Context
import android.graphics.Canvas
import com.lek.arcade.R
import com.lek.arcade.media.SoundManager
import com.lek.arcade.entities.Entity
import com.lek.arcade.entities.dialpad.Direction
import com.lek.arcade.entities.missile.MissileLauncher
import com.lek.arcade.entities.missile.MissileTracker

class Ship(
    private val shipContext: Context,
    private var shipX: Float,
    private var shipY: Float,
    private val shipWidth: Float,
    private val shipHeight: Float,
    private val body: PlayerBody,
    private val exhaust: Exhaust,
    private val soundManager: SoundManager
) : Entity(
    shipContext,
    shipX,
    shipY,
    shipWidth,
    shipHeight,
    false,
    speedX = 10F,
    speedY = 15F
) {

    init {
        updateBodyParts(shipX, shipY)
    }

    private fun updateBodyParts(x: Float, y: Float) {
        exhaust.x = x
        exhaust.y = (shipHeight / 2 + y) - (exhaust.exhaustHeight / 2)
        body.x = exhaust.x + exhaust.exhaustWidth / 3
        body.y = (shipHeight / 2 + y) - (body.bodyHeight / 2)
    }

    private var rockeInterval = 0
    override fun update(x: Float, y: Float) {
        shipX = x
        shipY = y
        MissileTracker.clearGoneMissiles()
        MissileTracker.allMissiles { this.update() }
        if (rockeInterval == 20) {
            MissileLauncher.launch(shipContext, shipX, shipY, shipWidth)
//            soundManager.playShortSound(SoundManager.ShortSound.DAMAGE, SoundManager.Loop.DONT_LOOP)
            rockeInterval = 0
        }
        rockeInterval++
        updateBodyParts(x, y)
    }

    override fun draw(canvas: Canvas) {
        update(shipX, shipY)
        body.draw(canvas)
        exhaust.draw(canvas)
        MissileTracker.allMissiles { this.draw(canvas) }
    }

    override fun image(): Int? {
        return null
    }

    override fun background(): Int? {
        return null
    }

    override fun moveUp() {
        shipY = 0F.coerceAtLeast(shipY - speedY)
    }

    override fun moveDown() {
        shipY = (shipY + speedY).coerceAtMost(limitedHeight - shipWidth)
    }

    override fun moveLeft() {
        shipX = 0F.coerceAtLeast(shipX - speedX)
    }

    override fun moveRight() {
        shipX = (shipX + speedX).coerceAtMost(limitedWidth - shipWidth)
    }

    fun updateDirection(selectedDirection: Direction) {
        when (selectedDirection) {
            Direction.UP -> moveUp()
            Direction.DOWN -> moveDown()
            Direction.LEFT -> moveLeft()
            Direction.RIGHT -> moveRight()
            Direction.DIAGONAL_RIGHT_UP -> moveDiagonalRightUp()
            Direction.DIAGONAL_RIGHT_DOWN -> moveDiagonalRightDown()
            Direction.DIAGONAL_LEFT_UP -> moveDiagonalLeftUp()
            Direction.DIAGONAL_LEFT_DOWN -> moveDiagonalLeftDown()
            Direction.NONE -> {
                /** Do notthing **/
            }
        }
    }

    companion object {
        fun shipWidth(context: Context) =
            context.resources.getDimensionPixelSize(R.dimen.player_width)

        fun shipHeight(context: Context) =
            context.resources.getDimensionPixelSize(R.dimen.player_height)
    }
}
