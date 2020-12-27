package com.lek.arcade.entities.dialpad

object DialpadDirectionProcessor {
    fun processDirection(direction: Direction, directionListener: DialpadDirectionListener) {
        when(direction) {
            Direction.UP -> directionListener.onUpSelected()
            Direction.DOWN -> directionListener.onDownSelected()
            Direction.LEFT -> directionListener.onLeftSelected()
            Direction.RIGHT -> directionListener.onRightSelected()
            else -> {}
        }
    }
}
