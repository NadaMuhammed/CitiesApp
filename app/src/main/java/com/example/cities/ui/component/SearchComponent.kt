package com.example.cities.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cities.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TopAppBar(
        colors = TopAppBarColors(
            containerColor = Color.LightGray,
            scrolledContainerColor = Color.LightGray,
            navigationIconContentColor = Color.LightGray,
            titleContentColor = Color.LightGray,
            actionIconContentColor = Color.LightGray
        ),
        title = {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = 18.dp)
                    .height(47.dp),
                placeholder = {
                    Text(
                        stringResource(R.string.search_field_hint),
                        style = TextStyle(
                            fontSize = 13.sp,
                            color = Color.LightGray
                        )
                    )
                },
                singleLine = true,
                textStyle = TextStyle.Default.copy(fontSize = 13.sp),
                shape = RoundedCornerShape(13.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.Gray,
                ),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            keyboardController?.hide()
                        },
                        modifier = Modifier.padding(end = 3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search),
                            tint = Color.Blue,
                            modifier = Modifier.size(23.dp)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                )
            )
        }
    )
}