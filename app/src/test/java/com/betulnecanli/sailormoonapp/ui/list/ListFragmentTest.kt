package com.betulnecanli.sailormoonapp.ui.list

import com.betulnecanli.sailormoonapp.data.remote.model.SailorMoon


import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import com.betulnecanli.sailormoonapp.ui.list.ListAdapter
import com.betulnecanli.sailormoonapp.ui.list.ListFragment
import com.betulnecanli.sailormoonapp.ui.list.ListViewModel

class ListFragmentTest {

    private val mockViewModel = mock<ListViewModel>()

    @Test
    fun displayListItems() {
        // Arrange
        val characters = listOf(
            SailorMoon( id = 1,
                name ="Sailor Moon" ,
                image = "/images/moon.jpg",
                realName = "Usagi Tsukino",
                birthday = "June 30th",
                age = 16,
                species = "Human"),
            SailorMoon( id = 2,
                name ="Sailor Mars" ,
                image = "/images/mars.jpg",
                realName = "Rei Hino",
                birthday = "April 17",
                age = 16,
                species = "Human"),
            SailorMoon(  id = 3,
                name ="Sailor Mercury" ,
                image = "/images/mercury.jpg",
                realName = "Ami Mizuno",
                birthday = "September 10",
                age = 16,
                species = "Human")
        )
        val characterFlow = MutableStateFlow(characters.asFlow())
        whenever(mockViewModel.characters).thenReturn(characterFlow)

        val scenario = launchFragmentInContainer<ListFragment>()
        scenario.onFragment {
            it.viewModel = mockViewModel
        }

        // Act
        onView(withId(R.id.sailorRecyc)).perform(actionOnItemAtPosition<ListAdapter.ViewHolder>(0, click()))

        // Assert
        onView(withId(R.id.sailorRecyc)).check(matches(isDisplayed()))
        onView(withText(characters[0].name)).check(matches(isDisplayed()))
        onView(withText(characters[1].name)).check(matches(isDisplayed()))
        onView(withText(characters[2].name)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateToDetailScreen() {
        // Arrange
        val character = SailorMoon(/* ... */)
        val characterFlow = MutableStateFlow(listOf(character))
        whenever(mockViewModel.characters).thenReturn(characterFlow)

        val scenario = launchFragmentInContainer<ListFragment>()
        scenario.onFragment {
            it.viewModel = mockViewModel
        }

        // Act
        onView(withId(R.id.sailorRecyc)).perform(actionOnItemAtPosition<ListAdapter.ViewHolder>(0, click()))

        // Assert
        val action = ListFragmentDirections.actionListFragmentToDetailFragment(character)
        verify(mockViewModel).openCharacterDetails(character)
        onView(withText(character.name)).check(matches(isDisplayed()))
    }
}