package com.example.degree53androidtest

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.degree53androidtest.presentation.EntryActivity
import com.example.degree53androidtest.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class EntryActivityTest {

    @Rule
    @JvmField
    var activityRule: ActivityTestRule<EntryActivity> = ActivityTestRule(EntryActivity::class.java)

    @Before
    fun registerIdlingResource(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun whenTypeAStringAndHitSearchBtn_NavigatesToSearchFragment() {
        onView(withId(R.id.introduce_name_et))
            .perform(typeText("Hello world"))

        onView(withId(R.id.search_btn))
            .perform(click())

        onView(withId(R.id.search_recycler_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun whenTypeNothingAndHitSearchBtn_NavigatesToSearchFragment() {
        onView(withId(R.id.introduce_name_et))
            .perform(typeText(""))

        onView(withId(R.id.search_btn))
            .perform(click())

        onView(withId(R.id.introduce_name_et))
            .check(matches(hasErrorText("You should introduce something here")))


    }

    @Test
    fun navigatesToSearchFagment_NavigatesToDetailsFragment_Check_Readme_isDisplayed() {
        onView(withId(R.id.introduce_name_et))
            .perform(typeText("retrofit"))

        onView(withId(R.id.search_btn))
            .perform(click())

        onView(withId(R.id.search_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                hasDescendant(withText("square/retrofit")), click()))

        onView(withId(R.id.readme_layout)).check(matches(isDisplayed()))

    }

    @Test
    fun fromDetailsView_clickBackButtonTwoTimes_navigatesBackToFirstFragment() {
        onView(withId(R.id.introduce_name_et))
            .perform(typeText("retrofit"))

        onView(withId(R.id.search_btn))
            .perform(click())

        onView(withId(R.id.search_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText("square/retrofit")), click()))

        Espresso.pressBack()

        onView(withId(R.id.search_recycler_view))
            .check(matches(isDisplayed()))

        Espresso.pressBack()

        onView(withId(R.id.search_btn)).check(matches(isDisplayed()))
    }
}