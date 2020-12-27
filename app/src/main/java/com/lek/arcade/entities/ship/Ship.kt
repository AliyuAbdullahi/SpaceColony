package com.lek.arcade.entities.ship

import android.content.Context
import android.graphics.Canvas
import com.lek.arcade.R
import com.lek.arcade.entities.Entity
import com.lek.arcade.entities.dialpad.Direction

class Ship(
    playerContext: Context,
    private var shipX: Float,
    private var shipY: Float,
    private val playerWidth: Float,
    private val playerHeight: Float,
    private val body: PlayerBody,
    private val exhaust: Exhaust
) : Entity(
    playerContext,
    shipX,
    shipY,
    playerWidth,
    playerHeight,
    false,
    speedX = 10F,
    speedY = 15F
) {

    init {
        updateBodyParts(shipX, shipY)
    }

    private fun updateBodyParts(x: Float, y: Float) {
        exhaust.x = x
        exhaust.y = (playerHeight / 2 + shipY) - (exhaust.exhaustHeight / 2)
        body.x = exhaust.x + exhaust.exhaustWidth / 3
        body.y = (playerHeight / 2 + shipY) - (body.bodyHeight / 2)
    }

    override fun update(x: Float, y: Float) {
        shipX = x
        shipY = y
        updateBodyParts(x, y)
    }

    override fun draw(canvas: Canvas) {
        update(shipX, shipY)
        body.draw(canvas)
        exhaust.draw(canvas)
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
        shipY = (shipY + speedY).coerceAtMost(limitedHeight - playerWidth)
    }

    override fun moveLeft() {
        shipX = 0F.coerceAtLeast(shipX - speedX)
    }

    override fun moveRight() {
        shipX = (shipX + speedX).coerceAtMost(limitedWidth - playerWidth)
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
            Direction.NONE -> { /** Do notthing **/ }
        }
    }

    companion object {
        fun shipWidth(context: Context) = context.resources.getDimensionPixelSize(R.dimen.player_width)

        fun shipHeight(context: Context) = context.resources.getDimensionPixelSize(R.dimen.player_height)
    }
}
