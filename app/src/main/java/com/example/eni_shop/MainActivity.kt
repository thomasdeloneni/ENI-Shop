package com.example.eni_shop

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.DaoFactory
import com.example.eni_shop.dao.DaoType
import com.example.eni_shop.repository.ArticleRepository
import com.example.eni_shop.ui.theme.ENIShopTheme
import java.util.Date
import java.util.logging.Logger


private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ENIShopTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        val article = ArticleRepository.getArticleById(1)
        Log.i(TAG, article.toString())

        val id = ArticleRepository.addArticle(Article(0, "Souris", "Souris blutooth", 40.0, "newUrl", "Périphériques", Date()))

        Log.i(TAG, "Article ajouté avec l'id $id")
        Log.i(TAG, ArticleRepository.getArticleById(id).toString())


    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ENIShopTheme {
        Greeting("Android")
    }
}