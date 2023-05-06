package com.example.wallpaper_anime_app.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.wallpaper_anime_app.ui.theme.Wallpaper_anime_appTheme

@Composable
fun MyAlertDialog(
    title: String = "title",
    content: @Composable (() -> Unit)? = null,
    isShow: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            Button(onClick = { isShow()  }) {
                Text(text = "CANCEL")
            }
        },
        title = {
            Text(text = title)
        },
        text = {
            if (content != null) {
                content()
            }
        },
    )
}

@Preview
@Composable
fun PrevMyAlertDialog() {
    Wallpaper_anime_appTheme {
        MyAlertDialog()
    }
}
