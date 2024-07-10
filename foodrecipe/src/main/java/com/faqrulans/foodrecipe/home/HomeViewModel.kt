package com.faqrulans.foodrecipe.home

import androidx.lifecycle.ViewModel
import com.faqrulans.domain.usecase.GetRecipesUseCase
import com.faqrulans.foodrecipe.common.uimodel.RecipeUI
import com.faqrulans.foodrecipe.common.uimodel.toUIModel
import com.faqrulans.foodrecipe.home.uimodel.SortType
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class HomeViewModel(getRecipesUseCase: GetRecipesUseCase): ViewModel() {

    private val _query = MutableStateFlow("")
    val query: Flow<String> = _query

    private val _selectedSortType = MutableStateFlow<SortType>(SortType.Name)
    val selectedSortType: Flow<SortType> = _selectedSortType

    private val _uiState: Flow<HomeUIState> = getRecipesUseCase
        .invoke()
        .map { recipes ->
            HomeUIState.Success(recipes.map { it.toUIModel() })
        }
    @OptIn(FlowPreview::class)
    val uiState: Flow<HomeUIState> = combine(_uiState, _query, _selectedSortType) { state, query, sortType ->
        if (state is HomeUIState.Success)  {
            val filtered = filterRecipesByQuery(state.data, query)
            HomeUIState.Success(sortRecipesBySortType(filtered, sortType))
        } else {
            state
        }
    }
        .distinctUntilChanged()
        .debounce(timeoutMillis = 400)

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    private fun filterRecipesByQuery(recipes: List<RecipeUI>, query: String): List<RecipeUI> {
        return recipes.filter { recipe -> recipe.name.contains(query, true)}
    }

    fun updateSortType(sortType: SortType) {
        _selectedSortType.value = sortType
    }

    private fun sortRecipesBySortType(recipes: List<RecipeUI>, sortType: SortType): List<RecipeUI> {
        return when (sortType) {
            SortType.Name -> recipes.sortedBy { recipe -> recipe.name }
            SortType.Calories -> recipes.sortedBy { recipe -> recipe.calories.split(" ").first().toInt() }
        }
    }

}

sealed interface HomeUIState {
    data object Loading: HomeUIState
    data class Success(val data: List<RecipeUI>): HomeUIState
}
