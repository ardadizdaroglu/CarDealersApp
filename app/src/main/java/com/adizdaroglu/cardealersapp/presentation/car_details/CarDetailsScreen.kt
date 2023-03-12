package com.adizdaroglu.cardealersapp.presentation.car_details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.adizdaroglu.cardealersapp.domain.model.Car

@Composable
fun CarDetailsScreen(
    navController: NavController,
) {
    val carDetails = remember {
        mutableStateOf(navController.previousBackStackEntry?.savedStateHandle?.get<Car>("clickedCar")!!)
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            carDetails.value.images?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    AsyncImage(
                        model = it[0].url,
                        contentDescription = null
                    )
                }
            }.run {}
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${carDetails.value.make} ${carDetails.value.model}",
                    style = MaterialTheme.typography.body1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
                carDetails.value.modelline?.let {
                    Text(
                        text = " $it",
                        style = MaterialTheme.typography.body1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                }.run {}
            }
            carDetails.value.firstRegistration?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "(" + it.split("-")[1] + ")",
                        style = MaterialTheme.typography.body1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        fontSize = 20.sp
                    )
                }
            }.run {}
            carDetails.value.mileage?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "$it km",
                        style = MaterialTheme.typography.body1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                }
            }.run {}
            carDetails.value.price?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "$it €",
                        style = MaterialTheme.typography.body1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                }
            }.run {}
            Divider(
                color = Color.White, thickness = 1.dp, modifier = Modifier
                    .padding(horizontal = 20.dp)
            )
            carDetails.value.description?.let {
                Column(
                    modifier = Modifier
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.body1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }.run { }
            Button(
                modifier = Modifier
                    .width(250.dp)
                    .height(70.dp)
                    .padding(top = 20.dp),
                onClick = {},
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Contact with seller!",
                    color = Color.White,
                    fontSize = 15.sp,
                )
            }
        }
    }
}