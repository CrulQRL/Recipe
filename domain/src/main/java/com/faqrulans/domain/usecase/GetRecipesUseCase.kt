package com.faqrulans.domain.usecase

import com.faqrulans.domain.entity.Recipe
import kotlinx.coroutines.flow.Flow

interface GetRecipesUseCase {
    fun invoke(): Flow<List<Recipe>>
}
