package com.example.eni_shop.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eni_shop.ui.common.EniShopScaffold
import com.example.eni_shop.ui.common.EniShopTextField
import com.example.eni_shop.ui.common.EniShopTopBar

@Composable
fun ArticleFormScreen(
    navigationIcon : @Composable () -> Unit
) {

    val context = LocalContext.current

    var title by rememberSaveable {
        mutableStateOf("")
    }
    EniShopScaffold(navigationIcon = navigationIcon) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally ,
            modifier = Modifier
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            ArticleForm(title = title, onTitleChange = {
                title = it
            })
            Button(onClick = {
                Toast.makeText(context, "$title ajouté", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "Enregistrer")
            }
        }
    }
}

@Composable
fun ArticleForm(
    modifier: Modifier = Modifier,
    title: String,
    onTitleChange: (String) -> Unit
) {


    var description by rememberSaveable {
        mutableStateOf("")
    }
    var price by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        EniShopTextField(
            label = "Titre",
            value = title,
            onValueChange = {
                onTitleChange(it)
            })
        EniShopTextField(
            label = "Description",
            value = description,
            onValueChange = {
                description = it
            })
        EniShopTextField(
            label = "Prix",
            value = price,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                price = if (it.isNotEmpty() && it.toDoubleOrNull() != null) {
                    it
                } else {
                    ""
                }
            })
        DropDownMenuCategories()

    }
}

@Composable
fun DropDownMenuCategories() {

    val categories = listOf("electronics", "jewelery", "men's clothing", "women's clothing");
    var expanded by remember {
        mutableStateOf(false)
    }
    var category by remember {
        mutableStateOf("")
    }
    Column() {

        EniShopTextField(
            modifier = Modifier.clickable {
                expanded = !expanded
            },
            label = "Categorie",
            value = category,
            onValueChange = {},
            enabled = false,
            placeholder = { Text("Choisir une catégorie") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "DropDown"
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            categories.forEach {
                DropdownMenuItem(
                    text = { Text(text = it) },
                    onClick = {
                        category = it
                        expanded = false
                    }
                )
            }
        }
    }

}


@Composable
@Preview
fun FormPreview() {
    //ArticleFormScreen()
}

