package com.example.cities.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cities.R
import com.example.cities.domain.model.City

@Composable
fun CardComponent(
    city: City,
    onClick: (City?) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = {
            onClick(city)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.padding(25.dp)
        ) {
            LogoComponent(city)

            Column(
                modifier = modifier.padding(horizontal = 25.dp)
            ) {
                Text(
                    text = stringResource(
                        R.string.city_display,
                        city.name ?: "",
                        city.country ?: ""
                    ),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(
                        R.string.city_coordinates,
                        city.coordinates?.lat ?: "",
                        city.coordinates?.lon ?: ""
                    )
                )
            }
        }
    }
}