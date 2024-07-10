package com.faqrulans.data

import com.faqrulans.data.mapper.toDomain
import com.faqrulans.data.remote.ApiService
import com.faqrulans.domain.entity.Recipe
import com.faqrulans.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeRepositoryImpl(private val apiService: ApiService): RecipeRepository {

    override fun geRecipes(): Flow<List<Recipe>> {
        return flow {
            val response = apiService.getRecipes()

            emit(response.map { it.toDomain() })
        }
    }

    override fun getRecipeDetail(recipeId: String): Flow<Recipe> {
        return flow {
            val response = apiService.getRecipes().firstOrNull { recipe -> recipe.id == recipeId }

            if (response != null) {
                emit(response.toDomain())
            }
        }
    }
}
