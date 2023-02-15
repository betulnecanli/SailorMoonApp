package com.betulnecanli.sailormoonapp.network

import com.betulnecanli.sailormoonapp.data.remote.model.ApiResponse
import com.betulnecanli.sailormoonapp.data.remote.model.SailorMoon
import com.google.gson.annotations.SerializedName
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FakeSailorMoonAPI {

    private val mockApi = mockk<SailorMoonAPI>()

    @Test
    fun `getCharacters with default page`() = runBlocking {
        // Arrange
        val expectedApiResponse = ApiResponse(success = true,
            message = "OK",
            prevPage = 0,
            nextPage = 2,
            sailorMoon = listOf(
                SailorMoon(
                    id = 1,
                    name ="Sailor Moon" ,
                    image = "/images/moon.jpg",
                    realName = "Usagi Tsukino",
                    birthday = "June 30th",
                    age = 16,
                    species = "Human"
                ),
                SailorMoon(
                    id = 2,
                    name ="Sailor Mars" ,
                    image = "/images/mars.jpg",
                    realName = "Rei Hino",
                    birthday = "April 17",
                    age = 16,
                    species = "Human"

                ),
                SailorMoon(
                    id = 3,
                    name ="Sailor Mercury" ,
                    image = "/images/mercury.jpg",
                    realName = "Ami Mizuno",
                    birthday = "September 10",
                    age = 16,
                    species = "Human"

                )
            ))
        coEvery { mockApi.getCharacters() } returns expectedApiResponse

        // Act
        val actualApiResponse = mockApi.getCharacters()

        // Assert
        assertEquals(expectedApiResponse, actualApiResponse)
    }

    @Test
    fun `getCharacters with specific page`() = runBlocking {
        // Arrange
        val expectedPage = 2
        val expectedApiResponse = ApiResponse( success = true,
            message = "OK",
            prevPage = 1,
            nextPage = 3,
            sailorMoon = listOf(
                SailorMoon(
                    id = 4,
                    name ="Sailor Jupiter" ,
                    image = "/images/jupiter.jpg",
                    realName = "Makoto Kino",
                    birthday = "December 5",
                    age = 15,
                    species = "Human"

                ),
                SailorMoon(
                    id = 5,
                    name ="Sailor Pluto" ,
                    image = "/images/pluto.jpg",
                    realName = "Setsuna Meiou",
                    birthday = "October 29",
                    age = 19,
                    species = "Human"

                ),
                SailorMoon(
                    id = 6,
                    name ="Sailor Saturn" ,
                    image = "/images/saturn.jpg",
                    realName = "Hotaru Tomoe",
                    birthday = "January 6th",
                    age = 14,
                    species = "Human"

                )
            ))
        coEvery { mockApi.getCharacters(expectedPage) } returns expectedApiResponse

        // Act
        val actualApiResponse = mockApi.getCharacters(expectedPage)

        // Assert
        assertEquals(expectedApiResponse, actualApiResponse)
    }


}