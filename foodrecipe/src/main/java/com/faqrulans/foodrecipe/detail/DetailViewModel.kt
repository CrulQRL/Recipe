package com.faqrulans.foodrecipe.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faqrulans.domain.usecase.GetRecipeDetailUseCase
import com.faqrulans.foodrecipe.common.uimodel.RecipeUI
import com.faqrulans.foodrecipe.common.uimodel.toUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<DetailUIState>(DetailUIState.Loading)
    val uiState: Flow<DetailUIState> = _uiState

    fun getRecipeDetail(recipeId: String) = viewModelScope.launch(Dispatchers.IO) {

        getRecipeDetailUseCase.invoke(recipeId).collect {
            _uiState.value = DetailUIState.Success(it.toUIModel())
        }

    }
}

sealed interface DetailUIState {
    data object Loading: DetailUIState
    data class Success(val data: RecipeUI): DetailUIState
}
