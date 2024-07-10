package com.faqrulans.foodrecipe.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.faqrulans.foodrecipe.common.uimodel.RecipeUI

@Composable
fun RecipeCard(recipe: RecipeUI, onClick: () -> Unit) {

    Card(
        modifier = Modifier
            .height(160.dp)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp, vertical = 12.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {

                AsyncImage(
                    modifier = Modifier
                        .width(132.dp)
                        .height(150.dp),
                    model = recipe.thumb,
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Food Image"
                )

            }

            Column(
                modifier = Modifier.padding(start = 8.dp),
            ) {
                Text(
                    text = recipe.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2
                )

                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = recipe.headline,
                    fontSize = 16.sp,
                    maxLines = 2
                )
            }


        }
    }
}


@Preview
@Composable
private fun Preview() {
    Scaffold {  padding ->
        RecipeCard(
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
        ) {}
    }
}
