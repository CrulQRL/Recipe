package com.faqrulans.foodrecipe.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.faqrulans.foodrecipe.common.uimodel.RecipeUI
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(recipeId: String, viewModel: DetailViewModel = koinViewModel()) {

    val uiState: DetailUIState by viewModel.uiState.collectAsStateWithLifecycle(
        initialValue = DetailUIState.Loading
    )

    LaunchedEffect(key1 = Unit) {
        viewModel.getRecipeDetail(recipeId)
    }

    Scaffold { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (uiState is DetailUIState.Success) {
                RecipeDetail((uiState as DetailUIState.Success).data)
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
internal fun RecipeDetail(recipeUI: RecipeUI) {
    Column(
//        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .weight(0.7f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = recipeUI.image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }

        Column(modifier = Modifier
            .weight(1f)
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
        ) {
            Text(
                text = recipeUI.name,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Nutrition",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Calories: ${recipeUI.calories} | Carbos: ${recipeUI.carbos} \nProtein: ${recipeUI.proteins} | Fats: ${recipeUI.fats}",
                fontSize = 16.sp,
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Description",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = recipeUI.description,
                fontSize = 16.sp,
            )

        }
    }
}

@Preview
@Composable
fun Preview() {
    Scaffold { padding ->
        RecipeDetail(
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
        )
    }
}
