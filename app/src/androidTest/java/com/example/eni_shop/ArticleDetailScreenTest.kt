package com.example.eni_shop

import android.content.Intent
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.eni_shop.bo.Article
import com.example.eni_shop.ui.screen.ArticleDetailScreen
import com.example.eni_shop.viewModel.ArticleDetailViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date

@RunWith(AndroidJUnit4::class)
class ArticleDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun articleDetailScreenIsWorking() {


        composeTestRule.setContent {
            ArticleDetailScreen(
                articleId = 1
            )
        }

        composeTestRule
            .onNodeWithTag("ArticleName")
            .assertExists("Titre non trouvé")
            .assertTextEquals("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")
    }
}