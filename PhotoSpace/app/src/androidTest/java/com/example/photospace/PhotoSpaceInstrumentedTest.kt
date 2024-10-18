package com.example.photospace

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.photospace.ui.theme.PhotoSpaceTheme

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PhotoSpaceInstrumentedTest {

    /**
     * Use [createComposeRule] to create a test rule which provides a test dispatcher
     */
    @get:Rule
    val composeTestRule = createComposeRule()

    /**
     * Context of the app under test.
     */
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.photospace", appContext.packageName)
    }

    /**
     * Show action buttons with text "Previous" and "Next" as button label.
     */
    @Test
    fun showActionButtons() {
        composeTestRule.setContent {
            PhotoSpaceTheme {
                ActionButtons({}, {})
            }
        }
        composeTestRule.onNodeWithText("Previous").assertExists()
        composeTestRule.onNodeWithText("Next").assertExists()
    }
}