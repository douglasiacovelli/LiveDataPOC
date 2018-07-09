package com.iacovelli.livedatapoc

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.SmallTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.iacovelli.livedatapoc.login.LoginFragment
import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.Rule
import org.junit.Test
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.Espresso.onView
import org.hamcrest.CoreMatchers.*


@RunWith(AndroidJUnit4::class)
class MainScreenTest {
    private val VALID_EMAIL = "address@mail.com"
    private val VALID_PASSWORD = "12345678"
    private val INVALID_EMAIL = "address.com"
    private val INVALID_PASSWORD = "1234"

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

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

    }

    private fun getString(resId: Int) = activityTestRule.activity.getString(resId)

}