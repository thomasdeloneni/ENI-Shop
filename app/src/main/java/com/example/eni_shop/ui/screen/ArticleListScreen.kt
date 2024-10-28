package com.example.eni_shop.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.eni_shop.bo.Article
import com.example.eni_shop.ui.common.EniShopTopBar
import com.example.eni_shop.viewModel.ArticleListViewModel


@Composable
fun ArticleListScreen(
    viewModel: ArticleListViewModel = viewModel(factory = ArticleListViewModel.Factory)
) {
    val categories by viewModel.categories.collectAsState()
    val articles by viewModel.articles.collectAsState()
    var category by remember { mutableStateOf("") }

    val filteredArticles = if (category != "") {
        articles.filter { it.category == category }
    } else {
        articles
    }

    Scaffold(topBar = { EniShopTopBar() }) {
        Column(modifier = Modifier
            .padding(it)
            .padding(horizontal = 8.dp)) {
            CategoryFilterChip(categories = categories,
                selectedCategory = category,
                onCategoryChange = {selectedCategory ->
                    category = selectedCategory
                })
            ArticleList(articles = filteredArticles)
        }
    }

}

@Composable
fun CategoryFilterChip(
    categories: List<String>,
    selectedCategory: String,
    onCategoryChange: (String) -> Unit
) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(categories) {
            FilterChip(
                selected = selectedCategory == it,
                onClick = {
                    if (selectedCategory != it) {
                        onCategoryChange(it)
                    } else {
                        onCategoryChange("")
                    }
                },
                label = { Text(text = it) },
                leadingIcon = if (selectedCategory == it) {
                    { Icon(imageVector = Icons.Default.Done, contentDescription = "Checked") }
                } else {
                    null
                },
            )
        }
    }
}

@Composable
fun ArticleList(articles: List<Article>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(articles) { article ->
            ArticleItem(article = article)
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ArticleItem(article: Article, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            AsyncImage(
                model = article.urlImage,
                contentDescription = article.name,
                modifier = Modifier
                    .size(80.dp)
                    .border(1.dp, MaterialTheme.colorScheme.inverseSurface, CircleShape)
                    .padding(8.dp)
            )
            Text(
                text = article.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(8.dp)
            )
            Text(text = "${String.format("%.2f", article.price) } €")
        }
    }
}

@Composable
@Preview
fun ArticleListScreenPreview() {
    ArticleListScreen()
}
