package com.lek.arcade.entities.ship

import android.content.Context
import com.lek.arcade.R
import com.lek.arcade.entities.Entity

class Exhaust(
    context: Context,
    exhaustX: Float,
    exhaustY: Float,
   val exhaustWidth: Float,
   val exhaustHeight: Float
) : Entity(context, exhaustX, exhaustY, exhaustWidth, exhaustHeight) {
    private var currentIndex = 0
    private val powerLevelOne = ExhaustResource.PowerOneResource()

    override fun image(): Int {
        if (currentIndex == 3) {
            currentIndex = 0
        }
        val image = powerLevelOne.resources[currentIndex % powerLevelOne.resources.size]
        currentIndex++
        return image
    }

    override fun background(): Int? {
        return null
    }

    companion object {
        fun bodyWidth(context: Context) = context.resources.getDimensionPixelSize(R.dimen.exhaust_width).toFloat()
        fun bodyHeight(context: Context) = context.resources.getDimensionPixelSize(R.dimen.exhaust_height).toFloat()
    }
}
