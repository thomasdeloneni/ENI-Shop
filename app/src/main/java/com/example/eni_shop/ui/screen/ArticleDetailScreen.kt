package com.example.eni_shop.ui.screen


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.eni_shop.bo.Article
import com.example.eni_shop.ui.common.BackButton
import com.example.eni_shop.ui.common.EniShopTopBar
import com.example.eni_shop.utils.toFrenchDate
import com.example.eni_shop.viewModel.ArticleDetailViewModel

@Composable
fun ArticleDetailScreen(
    onBackClick: () -> Unit,
    articleId: Long,
    articleViewModel: ArticleDetailViewModel = viewModel(factory = ArticleDetailViewModel.Factory)
) {
    val article by articleViewModel.article.collectAsState()

    LaunchedEffect(Unit) {
        articleViewModel.getArticleById(articleId)
    }

    Scaffold(topBar = { EniShopTopBar(backButton = { BackButton(onBackClick) }) }) { it ->
        Column(modifier = Modifier.padding(it)) {
            article?.let { article ->
                ArticleDetail(article = article)
            }
        }
    }
}

@Composable
fun ArticleDetail(article: Article, modifier: Modifier = Modifier) {
    val context = LocalContext.current


    val isChecked = remember { mutableStateOf(false) }
    Column {
        Text(
            text = article.name,
            fontSize = 30.sp,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/search?q=${article.name}+eni+shop")
                    ).also { intent ->
                        context.startActivity(intent)
                    }
                }
                .testTag("ArticleName"),
            lineHeight = 1.em,
            textAlign = TextAlign.Justify
        )
        Surface(
            color = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = article.urlImage,
                contentDescription = article.name,
                modifier = Modifier.size(200.dp)
            )
        }
        Text(
            text = article.description,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyMedium
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Prix ${article.price}")
            Text(text = "Date de sortie: ${article.date.toFrenchDate()}")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Checkbox(checked = isChecked.value, onCheckedChange = { isChecked.value = it })
            Text(text = "Favoris ?")
        }
    }
}
