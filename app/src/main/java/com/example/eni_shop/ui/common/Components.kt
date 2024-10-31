package com.example.eni_shop.ui.common

import android.provider.ContactsContract.Data
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eni_shop.datastore.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun EniShopScaffold(
    navigationIcon: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {

    Scaffold(
        topBar = { EniShopTopBar(navigationIcon = navigationIcon) },
        floatingActionButton = floatingActionButton
    ) {
        Column(modifier = Modifier.padding(it)) {
            content()
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EniShopTopBar(
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {}
) {
    TopAppBar(
        title = { EniShopTopBarTitle() },
        navigationIcon = navigationIcon,
        actions = { SettingsMenu() }
    )
}

@Composable
fun SettingsMenu() {

    var expanded by rememberSaveable{
        mutableStateOf(false)
    }

    var isActivated by rememberSaveable{
        mutableStateOf(false)
    }

    val context = LocalContext.current
    val coroutine = rememberCoroutineScope()


    LaunchedEffect(Unit){
        DataStoreManager.isDarkModeActivated(context).collect{
            isActivated = it
        }
    }


    IconButton(onClick = {
        expanded = !expanded
    }) {
        Icon(imageVector = Icons.Filled.Menu, contentDescription = "Settings")
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            expanded = false
        }) {
        DropdownMenuItem(
            text = { Text(text = "Dark Theme") },
            onClick = {},
            trailingIcon = {
                Switch(checked = isActivated, onCheckedChange = {
                    isActivated = it
                    coroutine.launch(Dispatchers.IO) {
                        DataStoreManager.setDarkMode(context, isActivated)
                    }
                })
            }
        )
    }


}


@Composable
fun EniShopTopBarTitle(modifier: Modifier = Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "Shop",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "ENI-SHOP",
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 40.sp
        )
    }

}


@Composable
fun EniShopTextField(
    modifier: Modifier = Modifier,
    label: String,
    value: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    placeholder: @Composable () -> Unit = {},
    enabled: Boolean = true,
    trailingIcon: @Composable () -> Unit = {},
    onValueChange: (String) -> Unit
) {

    Surface(
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(10),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(all = 8.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelLarge,
                fontSize = 24.sp

            )
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = keyboardOptions,
                enabled = enabled,
                placeholder = placeholder,
                trailingIcon = trailingIcon
            )
        }

    }
}

@Composable
fun NavigationBackIcon(onClickToBack: () -> Unit) {
    IconButton(onClick = onClickToBack) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back to the future"
        )
    }
}


@Composable
@Preview
fun Preview() {
    EniShopTopBar()

}




