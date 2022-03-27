package com.example.poplibexamapp

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.poplibexamapp.presentations.ListFragment
import com.example.poplibexamapp.presenters.ListRVAdapter
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentListTest {

    private lateinit var scenario: FragmentScenario<ListFragment>

    @Before
    fun setup() {
        scenario = launchFragmentInContainer()
    }

    @Test
    fun fragment_RecyclerTest() {
        Espresso.onView(withId(R.id.top_button)).perform(ViewActions.click())

        Thread.sleep(3000)

//        Espresso.onView(withId(R.id.rv_List))
//            .perform(
//                RecyclerViewActions
//                    .actionOnItemAtPosition<ListRVAdapter.ViewHolder>(
//                        2,
//                        ViewActions.click()
//                    )
//            )
    }
}