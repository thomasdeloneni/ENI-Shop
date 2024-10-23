package com.example.eni_shop.ui.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.memory.ArticleDaoMemoryImpl
import com.example.eni_shop.repository.ArticleRepository
import com.example.eni_shop.ui.common.EniShopTopBar
import java.util.Date

private const val TAG = "FormScreen"

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FormScreen(modifier: Modifier = Modifier) {
    val articles = ArticleRepository.getAllArticles()
    val categories = mutableListOf("electronics", "jewelery", "men's clothing", "women's clothing")
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    Scaffold(topBar = { EniShopTopBar() }) {
        Column(modifier = Modifier.fillMaxWidth().padding(it), horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = { Text(text = "Titre") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(8.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
            )
            TextField(
                value = description,
                onValueChange = {
                    description = it
                },
                label = { Text(text = "Description") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(8.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))

            )
            TextField(value = price, onValueChange = {
                price = it
            }, label = { Text(text = "Prix") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(8.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp)))
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(8.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
            ) {
                TextField(
                    value = selectedCategory,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Choisir une Catégorie") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category) },
                            onClick = {
                                selectedCategory = category
                                expanded = false
                            }
                        )
                    }
                }
            }
            Button(
                onClick = {
                    // Handle save logic here
                    val article = Article(
                        id = articles.size.toLong() + 1,
                        name = name,
                        description = description,
                        price = price.toDouble(),
                        urlImage = "Sample URL",
                        category = selectedCategory,
                        date = Date()
                    )
                    Log.i(TAG, "Saving article: $article")
                    ArticleRepository.addArticle(article)
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Enregistrer")
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    FormScreen()
}