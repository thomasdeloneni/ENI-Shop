package com.example.eni_shop

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.eni_shop.bo.Article
import com.example.eni_shop.repository.ArticleRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ArticleDetailScreen(article = ArticleRepository.getArticleById(2)!!)
        }

        val article = ArticleRepository.getArticleById(1)
        Log.i(TAG, article.toString())

        val id = ArticleRepository.addArticle(Article(0, "Souris", "Souris blutooth", 40.0, "newUrl", "Périphériques", Date()))

        Log.i(TAG, "Article ajouté avec l'id $id")
        Log.i(TAG, ArticleRepository.getArticleById(id).toString())

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ArticleDetailScreen(article: Article, modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { AppTopBar() },
        content = {paddingValues ->
            Column(modifier = modifier.fillMaxHeight().padding(paddingValues),
                verticalArrangement = Arrangement.SpaceBetween) {
                ArticleContent(article)
            }
        }
    )
}

@Composable
private fun ArticleContent(article: Article) {
    val isChecked = remember { mutableStateOf(false) }
    Text(
        text = "${article.name}!",
        fontSize = 28.sp, modifier = Modifier.padding(16.dp)
    )
    AsyncImage(
        model = article.urlImage,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
    )
    Text("Description: ${article.description}", modifier = Modifier.padding(16.dp))
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text("Prix: ${article.price} €", modifier = Modifier.padding(16.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text("Date de Sortie: ${article.date.formatDateToFR()}", modifier = Modifier.padding(16.dp))
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Checkbox(checked = isChecked.value, onCheckedChange = { checked ->  isChecked.value = checked })
        Spacer(modifier = Modifier.width(16.dp))
        Text("Favoris", modifier = Modifier.clickable { })
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun AppTopBar() {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ShoppingCart,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "ENI Shop")
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    )
}


//@Preview(showBackground = true)
//@Composable
//fun Preview(){
//    val article = ArticleRepository.getArticleById(2)
//    if (article != null) {
//        ArticleDetailScreen(article = article)
//    }
//}

fun Date.formatDateToFR(): String {
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.FRENCH)
    return dateFormat.format(this)
}