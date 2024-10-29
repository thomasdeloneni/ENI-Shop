package com.example.eni_shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eni_shop.nav.ArticleDetailDestination
import com.example.eni_shop.nav.ArticleFormDestination
import com.example.eni_shop.nav.ArticleListDestination
import com.example.eni_shop.ui.screen.ArticleDetailScreen
import com.example.eni_shop.ui.screen.ArticleFormScreen
import com.example.eni_shop.ui.screen.ArticleListScreen
import com.example.eni_shop.ui.theme.ENIShopTheme


private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ENIShopTheme {
                EniShopApp()
            }
        }
    }
}


@Composable
fun EniShopApp() {
    val navController = rememberNavController()

    EniShopNavHost(navController = navController)
}

@Composable
fun EniShopNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = ArticleListDestination.route) {
        composable(route = ArticleListDestination.route) {
            ArticleListScreen(
                onNavigateToArticleDetail = { articleId ->
                    navController.navigate("${ArticleDetailDestination.route}/$articleId")
                },
                onNavigateToAddArticle = {
                    navController.navigate(ArticleFormDestination.route)
                }
            )
        }
        composable(
            route = ArticleDetailDestination.routeWithArgs,
            arguments = ArticleDetailDestination.args
        ) { backStackEntry ->
            val articleId = backStackEntry.arguments?.getLong(ArticleDetailDestination.argName)
            if (articleId != null) {
                ArticleDetailScreen(
                    articleId = articleId,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
        composable(route = ArticleFormDestination.route) {

            ArticleFormScreen(onBackClick = {
                if(navController.previousBackStackEntry != null){
                    navController.popBackStack()
                }
            })
        }
    }
}