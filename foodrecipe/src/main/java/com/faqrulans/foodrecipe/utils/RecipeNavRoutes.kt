package com.faqrulans.foodrecipe.utils

object RecipeNavRoutes {

    const val HOME_SCREEN = "home"
    const val DETAIL_SCREEN = "detail/{${Params.RECIPE_ID}}"

    fun getDetailRoute(recipeId: String): String {
        return "detail/$recipeId"
    }

    object Params {
        const val RECIPE_ID = "recipeId"
    }

}
