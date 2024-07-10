package com.faqrulans.domain.entity

data class Recipe(
    val id: String,
    val name: String,
    val image: String,
    val description: String,
    val headline: String,
    val calories: String,
    val carbos: String,
    val fats: String,
    val proteins: String,
    val thumb: String
)
