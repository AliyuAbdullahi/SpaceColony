package com.lek.arcade.entities.dialpad

import com.lek.arcade.R

class DialPadResources {
    private val resources = mutableMapOf<Direction, Int>()

    init {
        resources[Direction.LEFT] = R.drawable.left
        resources[Direction.RIGHT] = R.drawable.right
        resources[Direction.UP] = R.drawable.up
        resources[Direction.DOWN] = R.drawable.down
    }

    fun resources(direction: Direction) = resources[direction]
}
