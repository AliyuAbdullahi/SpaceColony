package com.lek.arcade.entities.dialpad

import com.lek.arcade.R
import org.junit.Test

class DialPadResourcesTest : BaseTest() {

    @Test
    fun `when DialPadResources-resources is invoked - associated resource is returned for the direction passed`() {
        val dialPadResources = DialPadResources()

        val resource = dialPadResources.resources(Direction.DOWN)
        assert(resource == R.drawable.down)
    }
}
