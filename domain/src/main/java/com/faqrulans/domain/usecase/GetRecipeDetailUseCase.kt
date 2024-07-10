package com.faqrulans.domain.usecase

import com.faqrulans.domain.entity.Recipe
import kotlinx.coroutines.flow.Flow

interface GetRecipeDetailUseCase {
    fun invoke(recipeId: String): Flow<Recipe>

}
