package com.lek.arcade.entities.dialpad

import io.mockk.*
import org.junit.Before
import org.junit.Test

class DialpadDirectionProcessorTest: BaseTest() {

    @Test
    fun `when DialpadDirectionProcessor-processDirection is invoked - DialpadDirectionListener listens to direction`() {
        mockkObject(DialpadDirectionProcessor)
        val listener: DialpadDirectionListener = mockk()
        every { listener.onDownSelected() } just runs

        DialpadDirectionProcessor.processDirection(Direction.DOWN, listener)
        verify { listener.onDownSelected() }
    }
}
