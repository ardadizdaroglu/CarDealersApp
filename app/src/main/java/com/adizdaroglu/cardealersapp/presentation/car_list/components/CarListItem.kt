package com.adizdaroglu.cardealersapp.presentation.car_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(car) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (car.images != null) {
                AsyncImage(
                    model = car.images[0].url,
                    contentDescription = null
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "No Picture",
                    modifier = Modifier
                        .width(137.dp)
                        .height(50.dp)
                        .padding(vertical = 10.dp)
                        .fillMaxWidth()
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${car.description}",
                textAlign = TextAlign.Left,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${car.make} ${car.model}",
                    textAlign = TextAlign.Left,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "${car.price}€",
                    textAlign = TextAlign.Left,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}