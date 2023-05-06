@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.wallpaper_anime_app.ui.components


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wallpaper_anime_app.ui.theme.Wallpaper_anime_appTheme

@Composable
fun MySearchBar(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (it: String) -> Unit = {},
    onSearch: () -> Unit = {},
    placeholder: String = "Nhập tên gì đó..",
) {

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
    ) {
        TextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    // do something on search
                    onSearch()
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = "search",
                    tint = MaterialTheme.colorScheme.onSurface.copy(0.6f)
                )
            },
            placeholder = {
                Text(text = placeholder, style = MaterialTheme.typography.bodyMedium)
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = MaterialTheme.colorScheme.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                placeholderColor = MaterialTheme.colorScheme.onSurface.copy(0.6f)
            )
        )
    }

}

@Preview
@Composable
fun PrevMySearchBar() {
    Wallpaper_anime_appTheme {
        MySearchBar()
    }
}

@Preview
@Composable
fun PrevMySearchBarDark() {
    Wallpaper_anime_appTheme(darkTheme = true) {
        MySearchBar()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TextBox() {
    Wallpaper_anime_appTheme() {
        Surface(
            color = MaterialTheme.colorScheme.surface
        ) {
            BasicTextField(value = "123", onValueChange = {})
        }
    }
}

