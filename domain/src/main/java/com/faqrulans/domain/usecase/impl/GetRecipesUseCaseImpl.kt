package com.faqrulans.domain.usecase.impl

import com.faqrulans.domain.entity.Recipe
import com.faqrulans.domain.repository.RecipeRepository
import com.faqrulans.domain.usecase.GetRecipesUseCase
import kotlinx.coroutines.flow.Flow

class GetRecipesUseCaseImpl(
    private val recipeRepository: RecipeRepository
): GetRecipesUseCase {
    override fun invoke(): Flow<List<Recipe>> {
        return recipeRepository.geRecipes()
    }
}
