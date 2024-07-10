package com.faqrulans.base.di

import com.faqrulans.domain.usecase.GetRecipeDetailUseCase
import com.faqrulans.domain.usecase.GetRecipesUseCase
import com.faqrulans.domain.usecase.impl.GetRecipeDetailUseCaseImpl
import com.faqrulans.domain.usecase.impl.GetRecipesUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetRecipesUseCase> { GetRecipesUseCaseImpl(get()) }
    factory<GetRecipeDetailUseCase> { GetRecipeDetailUseCaseImpl(get()) }
}
