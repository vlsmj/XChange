package com.blueberryprojects.xchange.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blueberryprojects.xchange.R

@Composable
fun MessageDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(text = title,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 20.dp))
        },
        text = {
            Text(text = message)
        },
        confirmButton = {

        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }, modifier = Modifier
                .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.dark_navy_blue))) {
                Text(text = "Dismiss",
                    fontSize = 16.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center)
            }
        },
    )
}