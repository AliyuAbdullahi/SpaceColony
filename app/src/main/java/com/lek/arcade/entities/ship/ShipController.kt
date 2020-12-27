package com.lek.arcade.entities.ship

import com.lek.arcade.entities.dialpad.DialpadDirectionListener
import com.lek.arcade.entities.dialpad.Direction
import com.lek.arcade.entities.dialpad.DirectionButton

class ShipController(private val ship: Ship) : DialpadDirectionListener {

    private var selectedDirection = Direction.NONE

    override fun onUpSelected() {
        selectedDirection = Direction.UP
    }

    override fun onDownSelected() {
        selectedDirection = Direction.DOWN
    }

    override fun onLeftSelected() {
        selectedDirection = Direction.LEFT
    }

    override fun onRightSelected() {
        selectedDirection = Direction.RIGHT
    }

    fun update() {
        ship.updateDirection(selectedDirection)
    }

    override fun onTwoInputSelected(first: DirectionButton, second: DirectionButton) {
        when {
            diagonalLeftUp(first, second) -> {
                selectedDirection = Direction.DIAGONAL_LEFT_UP
            }
            diagonalLeftDown(first, second) -> {
                selectedDirection = Direction.DIAGONAL_LEFT_DOWN
            }
            diagonalRightUp(first, second) -> {
                selectedDirection = Direction.DIAGONAL_RIGHT_UP
            }
            diagonalRightDown(first, second) -> {
                selectedDirection = Direction.DIAGONAL_RIGHT_DOWN
            }
        }
    }

    override fun clear() {
        selectedDirection = Direction.NONE
    }

    private fun diagonalLeftUp(first: DirectionButton, second: DirectionButton) =
        first.direction == Direction.UP && second.direction == Direction.LEFT
                || second.direction == Direction.UP && first.direction == Direction.LEFT

    private fun diagonalRightUp(first: DirectionButton, second: DirectionButton) =
        first.direction == Direction.UP && second.direction == Direction.RIGHT
                || first.direction == Direction.RIGHT && second.direction == Direction.UP

    private fun diagonalLeftDown(first: DirectionButton, second: DirectionButton) =
        first.direction == Direction.DOWN && second.direction == Direction.LEFT
                || first.direction == Direction.LEFT && second.direction == Direction.DOWN

    private fun diagonalRightDown(first: DirectionButton, second: DirectionButton) =
        first.direction == Direction.DOWN && second.direction == Direction.RIGHT
                || first.direction == Direction.RIGHT && second.direction == Direction.DOWN
}
