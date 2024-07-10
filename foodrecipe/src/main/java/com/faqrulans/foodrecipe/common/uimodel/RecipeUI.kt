package com.faqrulans.foodrecipe.common.uimodel

import com.faqrulans.domain.entity.Recipe

data class RecipeUI(
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

fun Recipe.toUIModel(): RecipeUI {
    return RecipeUI(
        id = this.id,
        name = this.name,
        headline = this.headline,
        description = this.description,
        calories = this.calories.ifEmpty { "0 kcal" },
        carbos = this.carbos,
        fats = this.fats,
        proteins = this.proteins,
        thumb = this.thumb,
        image = this.image
    )
}
