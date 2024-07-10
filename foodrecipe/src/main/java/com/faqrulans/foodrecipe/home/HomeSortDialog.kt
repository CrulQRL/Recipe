package com.faqrulans.foodrecipe.home

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.faqrulans.foodrecipe.home.uimodel.SortType

@Composable
fun HomeSortDialog(
    selectedSortType: SortType,
    onSelected: (type: SortType) -> Unit
) {
    Dialog(onDismissRequest = {  }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 18.dp)
                ,
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {

                SortSelection(
                    label = "Recipe Name",
                    isSelected = selectedSortType == SortType.Name,
                    onClicked = { onSelected(SortType.Name) }
                )
                SortSelection(
                    label = "Calories",
                    isSelected = selectedSortType == SortType.Calories,
                    onClicked = { onSelected(SortType.Calories) }
                )
            }
        }
    }
}

@Composable
internal fun SortSelection(
    label: String,
    isSelected: Boolean,
    onClicked: () -> Unit
) {

    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .clickable { onClicked() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            modifier = Modifier.padding(start = 16.dp, top = 20.dp, bottom = 20.dp),
            selected = isSelected,
            onClick = null
        )

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = label, fontSize = 24.sp
        )
    }

}

@Preview
@Composable
private fun Preview() {
    HomeSortDialog(SortType.Name) {}
}
