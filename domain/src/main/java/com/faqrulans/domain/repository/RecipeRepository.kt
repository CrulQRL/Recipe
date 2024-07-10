package com.faqrulans.domain.repository

import com.faqrulans.domain.entity.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun geRecipes(): Flow<List<Recipe>>

    fun getRecipeDetail(recipeId: String): Flow<Recipe>

}
