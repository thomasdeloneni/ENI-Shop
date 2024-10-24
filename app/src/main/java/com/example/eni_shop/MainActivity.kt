package com.example.eni_shop

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.eni_shop.bo.Article
import com.example.eni_shop.repository.ArticleRepository
import com.example.eni_shop.ui.screen.ArticleFormScreen
import com.example.eni_shop.ui.theme.ENIShopTheme
import java.util.Date


private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ENIShopTheme {

                ArticleFormScreen()
            }
        }

        val article = ArticleRepository.getArticleById(1)
        Log.i(TAG, article.toString())

        val id = ArticleRepository.addArticle(Article(0, "Souris", "Souris blutooth", 40.0, "newUrl", "Périphériques", Date()))

        Log.i(TAG, "Article ajouté avec l'id $id")
        Log.i(TAG, ArticleRepository.getArticleById(id).toString())

    }
}