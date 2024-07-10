package com.faqrulans.foodrecipe.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.faqrulans.foodrecipe.common.RecipeCard
import com.faqrulans.foodrecipe.common.theme.Grey1
import com.faqrulans.foodrecipe.common.uimodel.RecipeUI
import com.faqrulans.foodrecipe.home.uimodel.SortType
import com.faqrulans.foodrecipe.utils.RecipeNavRoutes
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier,
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel()
) {

    val sortType: SortType by viewModel.selectedSortType.collectAsStateWithLifecycle(
        initialValue = SortType.Name
    )
    val query: String by viewModel.query.collectAsStateWithLifecycle(initialValue = "")
    val homeState: HomeUIState by viewModel.uiState.collectAsStateWithLifecycle(
        initialValue = HomeUIState.Loading
    )

    Scaffold { padding ->

        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (homeState is HomeUIState.Success) {
                ListScreen(
                    data = (homeState as HomeUIState.Success).data,
                    selectedType = sortType,
                    query = query,
                    navController = navController,
                    onQueryChange = { query -> viewModel.updateQuery(query) }
                ) { sortType ->
                    viewModel.updateSortType(sortType)
                }
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
    }

}

@Composable
internal fun ListScreen(
    data: List<RecipeUI>,
    navController: NavController,
    selectedType: SortType,
    query: String,
    onQueryChange: (String) -> Unit,
    onSortTypeSelected: (SortType) -> Unit
) {

    val showSortDialog = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (showSortDialog.value) {
            HomeSortDialog(selectedType) {
                showSortDialog.value = false
                onSortTypeSelected(it)
            }
        }

        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SearchField(modifier = Modifier.weight(1f), query, onQueryChange)
            SortIcon { showSortDialog.value = true }
        }

        LazyColumn {
            items(items = data) { data ->
                RecipeCard(data) {
                    navController.navigate(RecipeNavRoutes.getDetailRoute(data.id))
                }
            }
        }
    }
}

@Composable
internal fun SearchField(
    modifier: Modifier,
    query: String,
    onChange: (String) -> Unit
) {

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Grey1)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(start = 12.dp)
                .height(38.dp)
                .width(38.dp),
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Icon"
        )
        TextField(
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            ),
            placeholder = { Text(text = "Search by recipe name") },
            value = query,
            onValueChange = {
                onChange(it)
            }
        )
    }
}

@Composable
internal fun SortIcon(onClick: () -> Unit) {
    Icon(
        modifier = Modifier
            .width(48.dp)
            .height(48.dp)
            .clickable { onClick() }
        ,
        imageVector = Icons.Filled.ArrowDropDown,
        contentDescription = "Sort icon"
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {

    Scaffold { padding ->
        ListScreen(
            listOf(
                RecipeUI(
                    id = "73821",
                    name = "Crispy Fish Goujons",
                    headline = "with Sweet Potato Wedges and Minted Snap Peas",
                    description = "Close your eyes, open up your Ras El Hanout and inhale deeply. You are no longer standing in your kitchen",
                    calories = "516 kcal",
                    carbos = "47 g",
                    fats = "8 g",
                    proteins = "43 g",
                    thumb = "https://img.hellofresh.com/f_auto,q_auto,w_300/hellofresh_s3/image/533143aaff604d567f8b4571.jpg",
                    image = "https://img.hellofresh.com/f_auto,q_auto/hellofresh_s3/image/533143aaff604d567f8b4571.jpg"
                )
            ),
            rememberNavController(),
            SortType.Name,
            "",
            {}, {}
        )
    }
}
