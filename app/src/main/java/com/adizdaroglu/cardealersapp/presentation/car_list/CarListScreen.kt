package com.adizdaroglu.cardealersapp.presentation.car_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.adizdaroglu.cardealersapp.domain.model.Car
import com.adizdaroglu.cardealersapp.presentation.Screen
import com.adizdaroglu.cardealersapp.presentation.car_list.components.CarListItem
import java.util.*
import kotlin.collections.ArrayList

@Composable
fun CarListScreen(
    navController: NavController,
    viewModel: CarListViewModel = hiltViewModel()
) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val state = viewModel.state.value
    if (state.carList.isEmpty().not()) {
        Column {
            SearchView(state = textState)
            ItemList(state = textState, items = state.carList, navController = navController)
        }
    } else if(state.error.isNotBlank()) {
        Text(
            text = state.error
        )
    } else if(state.isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp),
                tint = Color.Black
            )
        },
        placeholder = {
            Text("Search", color = Color.Gray)
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            cursorColor = Color.Black,
            leadingIconColor = Color.Black,
            trailingIconColor = Color.Black,
            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun ItemList(state: MutableState<TextFieldValue>, items:List<Car>, navController: NavController) {

    fun navigateToCarDetailsScreen(car: Car) {
        navController.currentBackStackEntry?.savedStateHandle?.apply { set("clickedCar", car) }
        navController.navigate(Screen.CarDetailsScreen.route)
    }

    var filteredItems: List<Car>
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val searchedText = state.value.text
        filteredItems = if (searchedText.isEmpty()) {
            items
        } else {
            val resultList: ArrayList<Car> = arrayListOf()
            for (item in items) {
                if ((item.description?.lowercase(Locale.getDefault())
                        ?.contains(searchedText.lowercase(Locale.getDefault())) == true) ||
                    (item.make?.lowercase(Locale.getDefault())
                        ?.contains(searchedText.lowercase(Locale.getDefault())) == true)) {
                    resultList.add(item)
                }
            }
            resultList
        }
        itemsIndexed(filteredItems) { index, car ->
            CarListItem(
                car = car,
                onItemClick = {
                    navigateToCarDetailsScreen(it)
                }
            )
            if (index < filteredItems.lastIndex)
                Divider(
                    color = Color.White, thickness = 1.dp, modifier = Modifier
                        .padding(horizontal = 20.dp)
                )
        }

    }
}