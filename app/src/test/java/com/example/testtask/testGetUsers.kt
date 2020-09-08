package com.example.testtask

import com.example.testtask.Di.*
import com.example.testtask.Network.ProfileService
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.junit.MockitoJUnitRunner


@RunWith(JUnit4::class)
class testGetUsers : KoinTest {


    val profileService: ProfileService by inject()


    @Before
    fun setup() {
        startKoin {
            modules(testModule)
        }

    }

    @Test
    fun testGetUsers() = runBlocking {
        val page = profileService.getProfiles(1).page.toString()
        assertThat(page, equalTo("1"))
    }

    @Test
    fun testFieldsNotNull() = runBlocking{
        val profiles = profileService.getProfiles(1).dataList
        for(profile in profiles){
            assertThat(profile.firstName, notNullValue())
            assertThat(profile.last_name, notNullValue())
            assertThat(profile.email, notNullValue())
        }

    }
    @After
    fun after() {
        stopKoin()
    }
}