package com.faqrulans.base.di

import com.faqrulans.data.RecipeRepositoryImpl
import com.faqrulans.domain.repository.RecipeRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<RecipeRepository> { RecipeRepositoryImpl(get()) }
}
