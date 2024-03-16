package com.example.forecasttask.presentation.forecastScreen.presentation

import android.os.Build
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.forecasttask.R
import com.example.forecasttask.presentation.components.AppLoading
import com.example.forecasttask.presentation.forecastScreen.domian.model.CitiesModel.Companion.citiesList
import com.example.forecasttask.presentation.forecastScreen.presentation.components.CitiesDropDownMenu
import com.example.forecasttask.presentation.forecastScreen.presentation.components.ForecastComponent
import com.example.forecasttask.presentation.forecastScreen.presentation.components.ForecastEvent
import com.example.forecasttask.presentation.forecastScreen.presentation.components.WeatherComponent
import com.example.forecasttask.utils.Utils.convertUnixTimestampToReadableDate
import com.example.forecasttask.utils.Utils.kelvinToCelsius
import com.example.forecasttask.utils.Utils.toFormattedDate
import org.koin.androidx.compose.koinViewModel


@Composable
fun ForecastScreen(
    viewModel: ForecastViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ForecastScreenContent(
        state = state,
        event = viewModel::onEvent
    )
}

@Composable
private fun ForecastScreenContent(
    state: ForecastState,
    event: (ForecastEvent) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CitiesDropDownMenu(
            citiesList = citiesList,
            onSearchClicked = { city ->
                event.invoke(
                    ForecastEvent.CityChanged(
                        city.lat.toString(),
                        city.lon.toString()
                    )
                )
            },
            onCityChanged = { city ->
                event.invoke(
                    ForecastEvent.CityChanged(
                        city.lat.toString(),
                        city.lon.toString(),
                        isChanged = true
                    )
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (state.weatherData != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    text = state.weatherData.city?.name.orEmpty(),
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = state.weatherData.forecastList?.get(1)?.dtTxt?.toFormattedDate()
                        .orEmpty(),
                    style = MaterialTheme.typography.bodyLarge
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_placeholder),
                    contentDescription = null,
                    modifier = Modifier.size(64.dp),
                    contentScale = ContentScale.FillBounds,
                )

                Text(
                    text = stringResource(
                        R.string.celsius,
                        state.weatherData.forecastList?.first()?.main?.temp?.kelvinToCelsius()
                            .toString()
                    ),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                )

                Text(
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                    text = state.weatherData.forecastList?.first()?.weather?.first()?.description.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_sunrise),
                        contentDescription = null
                    )
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = state.weatherData.city?.sunrise?.convertUnixTimestampToReadableDate()
                                .orEmpty(),
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Image(
                        painter = painterResource(id = R.drawable.ic_sunset),
                        contentDescription = null
                    )
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = state.weatherData.city?.sunset?.convertUnixTimestampToReadableDate()
                                .orEmpty(),
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }


                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherComponent(
                        modifier = Modifier.weight(1f),
                        weatherLabel = stringResource(R.string.wind_speed_label),
                        weatherValue = state.weatherData.forecastList?.first()?.wind?.speed.toString(),
                        weatherUnit = stringResource(R.string.wind_speed_unit),
                        icon = R.drawable.ic_wind,
                    )

                    WeatherComponent(
                        modifier = Modifier.weight(1f),
                        weatherLabel = stringResource(R.string.humidity_label),
                        weatherValue = state.weatherData.forecastList?.first()?.main?.humidity.toString(),
                        weatherUnit = stringResource(R.string.humidity_unit),
                        icon = R.drawable.ic_humidity,
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(top = 8.dp, start = 16.dp),
                ) {
                    items(state.weatherData.forecastList?.size ?: 0) { index ->
                        val item = state.weatherData.forecastList?.get(index)
                        ForecastComponent(
                            date = item?.dtTxt.toString(),
                            minTemp = item?.main?.tempMin?.kelvinToCelsius().orEmpty(),
                            maxTemp = item?.main?.tempMax?.kelvinToCelsius().orEmpty(),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
                if (state.isCachedData) {
                    Text(
                        text = state.warningMessage.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }

                // Show retry button
                if (state.isCacheError) {
                    Column {
                        Text(
                            text = state.warningMessage.toString(),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                        )
                        Button(onClick = {
                            event.invoke(
                                ForecastEvent.CityChanged(
                                    citiesList.first().lat.toString(),
                                    citiesList.first().lon.toString()
                                )
                            )
                        }, modifier = Modifier.padding(horizontal = 8.dp)) {
                            Text(text = stringResource(R.string.search))
                        }
                    }
                }
            }
        }

    }

    if (state.isLoading) {
        AppLoading()
    } else if (state.errorMessage.isNotBlank()) {
        Toast.makeText(
            context,
            state.errorMessage,
            Toast.LENGTH_SHORT
        ).show()
    }

}

@Preview
@Composable
fun ForecastScreenPreview() {
    ForecastScreen()
}