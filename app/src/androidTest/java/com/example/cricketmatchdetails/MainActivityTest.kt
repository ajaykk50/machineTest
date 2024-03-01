package com.example.cricketmatchdetails

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testLoadFragments() {

        // Launch the MainActivity
        ActivityScenario.launch(MainActivity::class.java)

        //Thread.sleep(30000)

        // Wait for the MainActivity to launch
        onView(withId(R.id.clLayout)).check(matches(isDisplayed()))

        // Wait for the MatchSummeryFragment to be visible
        onView(withId(R.id.container)).check(matches(isDisplayed()))

        // Click on an element in the MatchSummeryFragment to load the ScorecardFragment
        onView(withId(R.id.cv_container)).perform(click())

        // Wait for the ScorecardFragment to be visible
        onView(withId(R.id.scorecard_layout)).check(matches(isDisplayed()))
    }

}