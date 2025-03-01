package com.example.pregnacyvitalstrack.presentance.designSystem

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.pregnacyvitalstrack.presentance.ui.theme.poppinsSemiBold

@Composable
fun CustomTextField(
    value:String,
    onValueChange:(String) -> Unit,
    placeHolder:String?,
    keyBoardType : KeyboardType = KeyboardType.Number,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value=value,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = TextStyle(
            fontFamily = poppinsSemiBold
        ),
        placeholder = {
            Text(text = placeHolder ?: "",
                color = MaterialTheme.colorScheme.onSurface.copy(0.4f))
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyBoardType
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
    )

}