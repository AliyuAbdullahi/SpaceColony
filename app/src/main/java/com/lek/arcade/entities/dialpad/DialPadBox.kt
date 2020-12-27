package com.lek.arcade.entities.dialpad

import android.content.Context
import android.graphics.Canvas
import android.graphics.RectF
import android.view.MotionEvent
import com.lek.arcade.R
import com.lek.arcade.entities.Entity

class DialPadBox(
    context: Context,
    private val boxX: Float,
    boxY: Float,
    private val boxSize: Float
) : Entity(context, boxX, boxY, boxSize, boxSize) {

    private val dialpadSize = context.resources.getDimensionPixelSize(R.dimen.button_width)

    private val up = DirectionButton(
        Direction.UP,
        context,
        (boxSize / 2) + boxX,
        boxY,
        dialpadSize.toFloat()
    ).apply {
        println("BOX SIZE : $boxSize BOxX: $boxX")
        println("DIALPADSIZE: $dialpadSize")
        printMetrics()
    }

    private val left = DirectionButton(
        Direction.LEFT,
        context,
        boxX,
        boxSize / 2 + boxY,
        dialpadSize.toFloat()
    ).apply { printMetrics() }

    private val down = DirectionButton(
        Direction.DOWN,
        context,
        boxSize / 2 + boxX,
        boxY + boxSize,
        dialpadSize.toFloat()
    ).apply { printMetrics() }

    private val right = DirectionButton(
        Direction.RIGHT,
        context,
        boxX + boxSize,
        boxSize / 2 + boxY,
        dialpadSize.toFloat()
    ).apply { printMetrics() }

    override fun draw(canvas: Canvas) {
        left.draw(canvas)
        right.draw(canvas)
        up.draw(canvas)
        down.draw(canvas)
    }

    override fun image(): Int? {
        return null
    }

    override fun background(): Int? {
        return null
    }

    fun registerTouchEvent(event: MotionEvent, dialpadDirectionListener: DialpadDirectionListener) {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                processTouched(up, x, y, dialpadDirectionListener)
                processTouched(down, x, y, dialpadDirectionListener)
                processTouched(left, x, y, dialpadDirectionListener)
                processTouched(right, x, y, dialpadDirectionListener)

                processTwoInputTouched(up, left, x, y, dialpadDirectionListener)
                processTwoInputTouched(up, right, x, y, dialpadDirectionListener)
                processTwoInputTouched(down, left, x, y, dialpadDirectionListener)
                processTwoInputTouched(down, right, x, y, dialpadDirectionListener)
            }
            MotionEvent.ACTION_UP -> {
                dialpadDirectionListener.clear()
            }
        }
    }

    private fun processTwoInputTouched(
        first: DirectionButton,
        second: DirectionButton,
        x: Float,
        y: Float,
        dialpadDirectionListener: DialpadDirectionListener
    ) {
        if (RectF(
                first.dialPadX,
                first.dialPadY,
                first.dialPadX + first.clickArea,
                first.dialPadY + first.clickArea
            ).contains(x, y) && RectF(
                second.dialPadX,
                second.dialPadY,
                second.dialPadX + second.clickArea,
                second.dialPadY + second.clickArea
            ).contains(x, y)
        ) {
            dialpadDirectionListener.onTwoInputSelected(first, second)
            println("Two Input Clicked ${first.direction} and ${second.direction}")
        }
    }


    private fun processTouched(
        pad: DirectionButton,
        x: Float,
        y: Float,
        dialpadDirectionListener: DialpadDirectionListener
    ) {
        if (RectF(
                pad.dialPadX,
                pad.dialPadY,
                pad.dialPadX + pad.clickArea,
                pad.dialPadY + pad.clickArea
            ).contains(x, y)
        ) {
            DialpadDirectionProcessor.processDirection(pad.direction, dialpadDirectionListener)
            println("Clicked ${pad.direction} \n dialX: ${pad.dialPadX}, dialY: ${pad.dialPadY}, clickedX: $x, clickedY: $y")
        }
    }
}
