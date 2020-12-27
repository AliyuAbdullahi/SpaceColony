package com.lek.arcade.entities.dialpad

import android.content.Context
import com.lek.arcade.entities.Entity

class DirectionButton(
    val direction: Direction,
    private val context: Context,
    val dialPadX: Float,
    internal val dialPadY: Float,
    private val size: Float
) : Entity(context, dialPadX, dialPadY, size, size, true) {

    private val padding = 2

    internal var clickArea: Float = size + padding

    private val resources by lazy { DialPadResource.dialPadResources.resources(direction) }

    override fun image(): Int? {
        return resources
    }

    override fun background(): Int? {
        return null
    }

    override fun printMetrics() {
        println("Direction: $direction")
    }
}
