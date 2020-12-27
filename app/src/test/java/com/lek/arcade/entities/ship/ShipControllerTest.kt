package com.lek.arcade.entities.ship

import com.lek.arcade.entities.dialpad.BaseTest
import com.lek.arcade.entities.dialpad.Direction
import com.lek.arcade.entities.dialpad.DirectionButton
import io.mockk.mockk
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.Test

class ShipControllerTest : BaseTest() {

    private val ship: Ship = mockk()

    private fun mockDirection() {
        every { ship.updateDirection(any()) } just runs
    }

    @Test
    fun `when ShipController-onUpSelected() - selected direction is UP`() {
        mockDirection()
        val controller = ShipController(ship)
        controller.onUpSelected()
        controller.update()
        verify { ship.updateDirection(Direction.UP) }
    }

    @Test
    fun `when ShipController-onDownSelected() - selected direction is DOWN`() {
        mockDirection()
        val controller = ShipController(ship)
        controller.onDownSelected()
        controller.update()
        verify { ship.updateDirection(Direction.DOWN) }
    }

    @Test
    fun `when ShipController-onLeftSelected() - selected direction is LEFT`() {
        mockDirection()
        val controller = ShipController(ship)
        controller.onLeftSelected()
        controller.update()
        verify { ship.updateDirection(Direction.LEFT) }
    }

    @Test
    fun `when ShipController-onRightSelected() - selected direction is RIGHT`() {
        mockDirection()
        val controller = ShipController(ship)
        controller.onRightSelected()
        controller.update()
        verify { ship.updateDirection(Direction.RIGHT) }
    }

    @Test
    fun `when ShipController-onTwoInputSelected(up, left) - result diagonal is left-up`() {
        mockDirection()
        val controller = ShipController(ship)
        val firstButton: DirectionButton = mockk()
        every { firstButton.direction } returns Direction.UP
        val secondButton: DirectionButton = mockk()
        every { secondButton.direction } returns Direction.LEFT
        controller.onTwoInputSelected(firstButton, secondButton)
        controller.update()
        verify { ship.updateDirection(Direction.DIAGONAL_LEFT_UP) }
    }

    @Test
    fun `when ShipController-onTwoInputSelected(down, left) - result diagonal is down-left`() {
        mockDirection()
        val controller = ShipController(ship)
        val firstButton: DirectionButton = mockk()
        every { firstButton.direction } returns Direction.DOWN
        val secondButton: DirectionButton = mockk()
        every { secondButton.direction } returns Direction.LEFT
        controller.onTwoInputSelected(firstButton, secondButton)
        controller.update()
        verify { ship.updateDirection(Direction.DIAGONAL_LEFT_DOWN) }
    }

    @Test
    fun `when ShipController-onTwoInputSelected(right, up) - result diagonal is right-up`() {
        mockDirection()
        val controller = ShipController(ship)
        val firstButton: DirectionButton = mockk()
        every { firstButton.direction } returns Direction.UP
        val secondButton: DirectionButton = mockk()
        every { secondButton.direction } returns Direction.RIGHT
        controller.onTwoInputSelected(firstButton, secondButton)
        controller.update()
        verify { ship.updateDirection(Direction.DIAGONAL_RIGHT_UP) }
    }

    @Test
    fun `when ShipController-onTwoInputSelected(right, down) - result diagonal is right-down`() {
        mockDirection()
        val controller = ShipController(ship)
        val firstButton: DirectionButton = mockk()
        every { firstButton.direction } returns Direction.DOWN
        val secondButton: DirectionButton = mockk()
        every { secondButton.direction } returns Direction.RIGHT
        controller.onTwoInputSelected(firstButton, secondButton)
        controller.update()
        verify { ship.updateDirection(Direction.DIAGONAL_RIGHT_DOWN) }
    }
}
