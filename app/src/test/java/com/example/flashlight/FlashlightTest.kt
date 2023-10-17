package com.example.flashlight
import org.junit.Test
import org.junit.Before

import org.junit.Assert.*

class FlashlightTest {
    private lateinit var mainActivity: FlashlightActivity

    @Before
    fun setUp() {
        mainActivity = FlashlightActivity()
    }

    @Test
    fun emptyInput() {
        assertEquals(R.id.textInput, "")
    }
    @Test
    fun flashlightOFF() {
        assertEquals(mainActivity.isFlashlightOn, false)
    }
}