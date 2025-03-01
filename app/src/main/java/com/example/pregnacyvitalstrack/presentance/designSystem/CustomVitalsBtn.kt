package com.example.pregnacyvitalstrack.presentance.designSystem

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pregnacyvitalstrack.presentance.ui.theme.poppinsSemiBold

@Composable
fun CustomVitalsBtn(
    enable:Boolean,
    btnText : String,
    onClick :() -> Unit,
    color: Color,
    modifier: Modifier = Modifier
) {


    Button(
        enabled = enable,
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = MaterialTheme.colorScheme.surface,
            disabledContentColor = Color.Gray.copy(0.4f)
        ),
        modifier=modifier
    ) {
        Text(
            text = btnText,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 16.sp,
            fontFamily = poppinsSemiBold,
            fontWeight = FontWeight.Normal
        )
    }

}
