package com.example.eni_shop.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EniShopTopBar(modifier : Modifier = Modifier) {
    TopAppBar(title = { EniShopTopBarTitle()})
}

@Composable
fun EniShopTopBarTitle(modifier : Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()){
        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Shop", modifier=Modifier.size(40.dp))
        Spacer(modifier= Modifier.width(8.dp))
        Text(
            text = "ENI-SHOP",
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 40.sp)
    }
}

@Preview
@Composable
fun Preview() {
    EniShopTopBar()
}