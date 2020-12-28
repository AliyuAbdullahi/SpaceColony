package com.lek.arcade.entities.dialpad

import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before

open class BaseTest {

    @Before
    open fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun afterTests() {
        unmockkAll()
        // or unmockkObject(MockObj)
    }
}
