package org.lniranjan.beautifulbottomsheeet

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class BottomSheetActivityTest
{

    @get:Rule
    val activityRule = ActivityScenarioRule(BottomSheetActivity::class.java)

    @Test
    fun test_activityisLaunched() {
        onView(withId(R.id.bottomsheetactivity)).check(matches(isDisplayed()))
    }

    @Test
    fun test_buttonIsVissible() {
        onView(withId(R.id.showButtonSheet)).check(matches(isDisplayed()))
    }

    @Test
    fun test_bottomsheetIsVisisble()
    {
        onView(withId(R.id.showButtonSheet)).perform(click())
        onView(withId(R.id.bottomSheet)).check(matches(isDisplayed()))
    }
}