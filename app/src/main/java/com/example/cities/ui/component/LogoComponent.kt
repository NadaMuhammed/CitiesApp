package com.example.cities.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cities.domain.model.City

@Composable
fun LogoComponent(
    city: City
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
            .background(Color.Gray),
    ) {
        Text(
            text = city.country ?: "",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}