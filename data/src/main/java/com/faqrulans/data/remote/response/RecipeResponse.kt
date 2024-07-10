package com.faqrulans.data.remote.response

data class RecipeResponse(
    val id: String,
    val name: String,
    val headline: String,
    val description: String,
    val calories: String,
    val carbos: String,
    val fats: String,
    val proteins: String,
    val thumb: String,
    val image: String
)
