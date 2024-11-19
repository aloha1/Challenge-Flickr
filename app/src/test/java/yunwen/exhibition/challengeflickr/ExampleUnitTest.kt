package yunwen.exhibition.challengeflickr

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@ExperimentalComposeUiApi
class ExampleUnitTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun addition_isCorrect() {
        composeTestRule.setContent {
            ProductDetails("title", "author", "image","desc", "publish")
        }
        composeTestRule.onNodeWithText("title").assertIsDisplayed()
        composeTestRule.onNodeWithText("author").assertIsDisplayed()
        composeTestRule.onNodeWithText("image").assertIsDisplayed()
        composeTestRule.onNodeWithText("desc").assertIsDisplayed()
        composeTestRule.onNodeWithText("publish").assertIsDisplayed()
    }
}