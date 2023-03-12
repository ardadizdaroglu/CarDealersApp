package com.adizdaroglu.cardealersapp.presentation.car_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.adizdaroglu.cardealersapp.domain.model.Car

@Composable
fun CarListItem(
    car: Car,
    onItemClick: (Car) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onItemClick(car) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = car.images?.get(0)?.url,
                contentDescription = null
            )
            Text(
                text = "${car.description}",
                textAlign = TextAlign.Left,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}