package com.example.forecasttask.presentation.forecastScreen.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.example.forecasttask.presentation.forecastScreen.domian.model.CitiesModel

@Composable
fun CitiesDropDownMenu(
    citiesList: List<CitiesModel>,
    onSearchClicked: (CitiesModel) -> Unit,
    onCityChanged : (CitiesModel) -> Unit,
) {
    var selectedCityModel by remember { mutableStateOf(citiesList.first()) }

    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(selectedCityModel) {
        onSearchClicked(selectedCityModel)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .height(60.dp)
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(5.dp))
                .clickable { expanded = expanded.not() },
            color = Color.White,
            border = BorderStroke(0.5.dp, Color.Black),
            shape = RoundedCornerShape(5.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Outlined.Search,
                    contentDescription = null,
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = selectedCityModel.cityNameEn,
                    modifier = Modifier
                        .weight(1f)

                )

            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .background(Color.White),
                properties = PopupProperties(focusable = false)
            ) {
                citiesList.forEach { city ->
                    DropdownMenuItem(
                        text = { Text(text = city.cityNameEn, fontWeight = FontWeight.Medium) },
                        onClick = {
                            selectedCityModel = city
                            onCityChanged.invoke(city)
                            expanded = false
                        },
                        leadingIcon = {
                            Icon(
                                Icons.Outlined.Search,
                                contentDescription = null
                            )
                        })

                }
            }
        }
    }
}

@Preview
@Composable
fun CitiesDropDownMenuPrvivew() {
    CitiesDropDownMenu(
        listOf(),{},{}
    )
}