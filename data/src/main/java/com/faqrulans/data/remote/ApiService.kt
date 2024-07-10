package com.faqrulans.data.remote

import com.faqrulans.data.remote.response.RecipeResponse
import retrofit2.http.GET

interface ApiService {

    @GET("recipes.json")
    suspend fun getRecipes(): List<RecipeResponse>

}
