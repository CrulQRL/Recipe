package com.faqrulans.foodrecipe.home.uimodel

sealed interface SortType {
    data object Name: SortType
    data object Calories: SortType
}
