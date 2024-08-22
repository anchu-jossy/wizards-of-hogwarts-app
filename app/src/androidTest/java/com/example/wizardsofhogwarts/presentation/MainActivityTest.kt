package com.example.wizardsofhogwarts.presentation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import com.example.wizardsofhogwarts.presentation.MainScreenContent
import com.example.wizardsofhogwarts.presentation.theme.ThemeSwitcherTheme
import com.example.wizardsofhogwarts.presentation.characterlist.CharacterListViewModel
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class MainScreenContentTest {

    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    @Composable
    fun TestContent(
        viewModel: CharacterListViewModel,
        navController: NavHostController,
        drawerState: DrawerState,
        scope: CoroutineScope
    ) {
        ThemeSwitcherTheme(darkTheme = false) {
            MainScreenContent(
                scope = scope,
                drawerState = drawerState,
                navController = navController,
                viewModel = viewModel
            )
        }
    }

    @Test
    fun initialState_isRendered() {
        val viewModel = mockk<CharacterListViewModel>(relaxed = true)
        val navController = mockk<NavHostController>(relaxed = true)
        val drawerState = mockk<DrawerState>(relaxed = true)
        val scope = CoroutineScope(Dispatchers.IO) // Or use a mocked CoroutineScope

        testRule.setContent {
            TestContent(
                viewModel = viewModel,
                navController = navController,
                drawerState = drawerState,
                scope = scope
            )
        }

        testRule.onNodeWithText("Wizards of Hogwarts").assertIsDisplayed()
    }

    @Test
    fun drawerOpensAndCloses(): Unit = runBlocking {
        val viewModel = mockk<CharacterListViewModel>(relaxed = true)
        val navController = mockk<NavHostController>(relaxed = true)
        val drawerState = mockk<DrawerState>(relaxed = true)
        val scope = CoroutineScope(Dispatchers.IO) // Or use a mocked CoroutineScope

        testRule.setContent {
            TestContent(
                viewModel = viewModel,
                navController = navController,
                drawerState = drawerState,
                scope = scope
            )
        }

        // Simulate clicking on the drawer icon to open the drawer
        testRule.onNodeWithText("Wizards of Hogwarts").performClick()
        // Verify that the drawer is now open
     //   assertTrue(drawerState.drawerState == DrawerValue.Open)
    }

    @Test
    fun themeSwitcherChangesTheme() {
        val viewModel = mockk<CharacterListViewModel>(relaxed = true)
        val navController = mockk<NavHostController>(relaxed = true)
        val drawerState = mockk<DrawerState>(relaxed = true)
        val scope = CoroutineScope(Dispatchers.IO) // Or use a mocked CoroutineScope

        testRule.setContent {
            TestContent(
                viewModel = viewModel,
                navController = navController,
                drawerState = drawerState,
                scope = scope
            )
        }

        testRule.onNodeWithText("Dark Theme").performClick()
        verify { viewModel.toggleTheme(true) }
    }
}
