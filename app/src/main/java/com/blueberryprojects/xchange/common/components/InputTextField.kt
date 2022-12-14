package com.blueberryprojects.xchange.common.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
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
    text: String = "",
    maxValue: Double = 0.00,
    onValueChange: (input: String) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    if (text.isNotBlank() && text.toDouble() > maxValue) {
        onValueChange(text.substring(0, text.length - 1))
    }

    if (text.isNotBlank() && text.substring(0, 1) == "0") {
        onValueChange("")
    }

    BasicTextField(
        modifier = modifier,
        value = text,
        textStyle = TextStyle(
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            autoCorrect = false
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        onValueChange = {
            val input = it.filter { initial ->
                initial.isDigit()
            }

            onValueChange(input)
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
                    if (text.isEmpty()) {
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