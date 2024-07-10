package com.faqrulans.base.di

import com.faqrulans.foodrecipe.detail.DetailViewModel
import com.faqrulans.foodrecipe.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
