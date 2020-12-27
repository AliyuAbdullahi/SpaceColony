package com.lek.arcade.entities

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.annotation.DrawableRes
import com.lek.arcade.core.helper.ViewDimention
import com.lek.arcade.core.helper.scaleBitMap

abstract class Entity(
    private val context: Context,
    var x: Float,
    var y: Float,
    var width: Float,
    var height: Float,
    val clickable: Boolean = true,
    var speedX: Float = 0F,
    var speedY: Float = 0F
) {

    open fun update(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    var limitedHeight: Float = ViewDimention.height.toFloat()
    var limitedWidth: Float = ViewDimention.width.toFloat()

    fun setLimitedWidthAndHeight(limitedWidth: Float, limitedHeight: Float) {
        this.limitedHeight = limitedHeight
        this.limitedWidth = limitedWidth
    }

    open fun render(canvas: Canvas) {
        background()?.let {
            paint.color = it
        }
        image()?.let { theImage ->
            val bitmap = BitmapFactory.decodeResource(context.resources, theImage)
            val scaledBitmap = scaleBitMap(bitmap, width, height)
            canvas.drawBitmap(scaledBitmap, x, y, paint)
        }
    }

    open val paint: Paint = Paint().apply {
        this.isAntiAlias = true
    }

    fun intersect(entity: Entity): Boolean =
        RectF(x, y, x + width, y + height).intersect(
            RectF(
                entity.x,
                entity.y,
                entity.x + entity.width,
                entity.y + entity.height
            )
        )

    fun getBound() = RectF(x, y, x + width, y + height)

    open fun draw(canvas: Canvas) {
        render(canvas)
    }

    @DrawableRes
    abstract fun image(): Int?

    abstract fun background(): Int?

    open fun printMetrics() {
        println("X: $x \nY: $y \n Width: $width \n height: $height \n Clikable: $clickable")
    }

    open fun moveUp() {

    }

    open fun moveDown() {

    }
    open fun moveLeft() {

    }
    open fun moveRight() {

    }
    open fun moveDiagonalLeftUp() {
        moveUp()
        moveLeft()
    }
    open fun moveDiagonalLeftDown() {
        moveLeft()
        moveDown()
    }
    open fun moveDiagonalRightUp() {
        moveRight()
        moveUp()
    }
    open fun moveDiagonalRightDown() {
        moveRight()
        moveDown()
    }
}
