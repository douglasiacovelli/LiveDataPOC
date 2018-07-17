package com.iacovelli.livedatapoc

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainScreenTest {
    private val VALID_EMAIL = "address@mail.com"
    private val VALID_PASSWORD = "12345678"
    private val INVALID_EMAIL = "address.com"
    private val INVALID_PASSWORD = "1234"

    private val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)
    @get:Rule
    val testRules: RuleChain = RuleChain
            .outerRule(RetryTestRule(3))
            .around(activityTestRule)
            .around(InstantTaskExecutorRule())

    @Test
    fun checkFormFilledCorrectlyPasses() {
        onView(withId(R.id.email_field))
                .check(matches(isDisplayed()))
                .perform(typeText(VALID_EMAIL))

        onView(withId(R.id.password_field))
                .check(matches(isDisplayed()))
                .perform(typeText(VALID_PASSWORD))

        onView(withId(R.id.submit_button))
                .check(matches(isDisplayed()))
                .perform(click())

        onView(withId(R.id.list))
                .check(matches(isDisplayed()))
    }

    @Test
    fun whenFormIsFilledIncorrectlyItDoesNotPass() {
        onView(withId(R.id.email_field))
                .check(matches(isDisplayed()))
                .check(matches(withText("")))
                .perform(typeText(INVALID_EMAIL))

        onView(withId(R.id.password_field))
                .check(matches(isDisplayed()))
                .check(matches(withText("")))
                .perform(typeText(INVALID_PASSWORD))

        onView(withId(R.id.submit_button))
                .check(matches(isDisplayed()))
                .perform(click())

        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(R.string.invalid_form)))
                .check(matches(isDisplayed()))

        onView(withText(R.string.invalid_password))
                .check(matches(isDisplayed()))

        onView(withText(R.string.invalid_email))
                .check(matches(isDisplayed()))

    }

}