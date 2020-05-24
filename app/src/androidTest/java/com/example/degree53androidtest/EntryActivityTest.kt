package com.example.degree53androidtest

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.example.degree53androidtest.presentation.EntryActivity
import com.example.degree53androidtest.utils.EspressoIdlingResource
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
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
    fun whenTypeAStringAndSubmitSearchBtn_DisplayItems() {
        onView(withId(R.id.repo_search_view)).perform(SearchViewActionExtension.submitText("retrofit"))


        onView(withId(R.id.search_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(withText("square/retrofit")), click()))
    }
}

class SearchViewActionExtension {

    companion object {
        fun submitText(text: String): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
                }

                override fun getDescription(): String {
                    return "Set text and submit"
                }

                override fun perform(uiController: UiController, view: View) {
                    (view as SearchView).setQuery(text, true) //submit=true will fire search
                }
            }
        }
        fun typeText(text: String): ViewAction {
            return object : ViewAction {
                override fun getConstraints(): Matcher<View> {
                    return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
                }

                override fun getDescription(): String {
                    return "Set text"
                }

                override fun perform(uiController: UiController, view: View) {
                    (view as SearchView).setQuery(text, false)
                }
            }
        }
    }
}