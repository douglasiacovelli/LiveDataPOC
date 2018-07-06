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


@RunWith(AndroidJUnit4::class)
class MainScreenTest {
    private val VALID_EMAIL = "address@mail.com"
    private val VALID_PASSWORD = "12345678"

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val activityActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java, false, false)

    @Test
    fun checkFormFilledCorrectlyPasses() {
        activityActivityTestRule.launchActivity(null)

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
}