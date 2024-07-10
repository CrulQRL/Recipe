package com.faqrulans.data.mapper

import com.faqrulans.data.remote.response.RecipeResponse
import com.faqrulans.domain.entity.Recipe

fun RecipeResponse.toDomain(): Recipe {
    return Recipe(
        id = this.id,
        name = this.name,
        headline = this.headline,
        description = this.description,
        calories = this.calories,
        carbos = this.carbos,
        fats = this.fats,
        proteins = this.proteins,
        thumb = this.thumb,
        image = this.image
    )
}
