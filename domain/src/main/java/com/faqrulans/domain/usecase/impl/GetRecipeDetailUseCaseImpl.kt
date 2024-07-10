package com.faqrulans.domain.usecase.impl

import com.faqrulans.domain.entity.Recipe
import com.faqrulans.domain.repository.RecipeRepository
import com.faqrulans.domain.usecase.GetRecipeDetailUseCase
import kotlinx.coroutines.flow.Flow

class GetRecipeDetailUseCaseImpl(
    private val recipeRepository: RecipeRepository
): GetRecipeDetailUseCase {

    override fun invoke(recipeId: String): Flow<Recipe> {
        return recipeRepository.getRecipeDetail(recipeId)
    }
}
