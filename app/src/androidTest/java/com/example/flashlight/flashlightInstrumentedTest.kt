package com.example.flashlight

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId


import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private lateinit var scenario: ActivityScenario<FlashlightActivity>

    @Before
    fun setUp() {
        scenario = launch(FlashlightActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun testFlashlightSwitch() {
        onView(withId(R.id.flashlightSwitch)).check(matches(isDisplayed()))
        onView(withId(R.id.flashlightSwitch)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.flashlightSwitch)).perform(click())
        Thread.sleep(2000)
    }


    @Test
    fun testEditText() {
        onView(withId(R.id.textInput)).check(matches(isDisplayed()))
        onView(withId(R.id.textInput)).perform(typeText("ON"))
        onView(withId(R.id.button)).perform(click())
    }

}