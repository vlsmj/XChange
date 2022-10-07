package com.blueberryprojects.xchange.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun InputTextField(
    modifier: Modifier,
    hint: String,
    isEnabled: Boolean? = true,
    onValueChange: (input: String) -> Unit,
) {
    var textState by remember {
        mutableStateOf("")
    }

    BasicTextField(
        modifier = modifier,
        value = textState,
        textStyle = TextStyle(
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            autoCorrect = false
        ),
        onValueChange = {
            textState = it
            onValueChange(textState)
        },
        enabled = isEnabled ?: true,
        singleLine = true,
        cursorBrush = SolidColor(Color.Black),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.9f)
                ) {
                    if (textState.isEmpty()) {
                        Text(
                            modifier = Modifier
                                .alpha(0.5f)
                                .fillMaxWidth(),
                            text = hint,
                            color = Color.Black,
                            textAlign = TextAlign.Center,
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}