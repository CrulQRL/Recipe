package com.faqrulans.base.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.faqrulans.foodrecipe.detail.DetailScreen
import com.faqrulans.foodrecipe.home.HomeScreen
import com.faqrulans.foodrecipe.utils.RecipeNavRoutes

@Composable
fun MainNavigation(modifier: Modifier) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable(RecipeNavRoutes.HOME_SCREEN) {
            HomeScreen(modifier, navController)
        }

        composable(
            route = RecipeNavRoutes.DETAIL_SCREEN,
            arguments = listOf(navArgument(RecipeNavRoutes.Params.RECIPE_ID) {
                type = NavType.StringType
            })
        ) { backStackEntry ->

            val recipeId = backStackEntry.arguments?.getString(RecipeNavRoutes.Params.RECIPE_ID, "")

            if (!recipeId.isNullOrEmpty()) {
                DetailScreen(recipeId)
            }
        }
    }
}
