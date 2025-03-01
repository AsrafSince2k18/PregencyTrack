package com.example.pregnacyvitalstrack.presentance.designSystem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pregnacyvitalstrack.R
import com.example.pregnacyvitalstrack.data.vitalsData.VitalsData
import com.example.pregnacyvitalstrack.presentance.ui.theme.PregnacyVitalsTrackTheme
import com.example.pregnacyvitalstrack.presentance.ui.theme.Primary
import com.example.pregnacyvitalstrack.presentance.ui.theme.Secondary
import com.example.pregnacyvitalstrack.presentance.ui.theme.poppinsSemiBold
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun VitalsComponent(
    vitalsData: VitalsData,
    homeAction : () -> Unit
) {



    Card(
        onClick = {
            homeAction()
        },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 5.dp
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(
            width = 2.dp,
            color = Primary
        ),

    ) {
        Column(
            modifier=Modifier
                .fillMaxWidth()
                .background(Secondary.copy(0.8f))
                .padding(top = 12.dp)
                .padding(bottom = 8.dp),
            horizontalAlignment = Alignment.Start
        ){
            Row (
                modifier=Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ){

                VitalsItem(
                    icon = painterResource(R.drawable.heart),
                    value = "${vitalsData.bpm} bmp"
                )

                VitalsItem(
                    icon = painterResource(R.drawable.arm),
                    value = "${vitalsData.mmhg} mmHg"
                )

            }

            Spacer(modifier=Modifier
                .height(12.dp))

            Row (
                modifier=Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ){

                VitalsItem(
                    icon = painterResource(R.drawable.wscale),
                    value = "${vitalsData.kg} kgs"
                )

                VitalsItem(
                    icon = painterResource(R.drawable.newbornb),
                    value = "${vitalsData.kicks} kicks"
                )

            }
        }

        Box(modifier=Modifier
            .fillMaxWidth()
            .height(35.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Primary.copy(0.7f),
                        MaterialTheme.colorScheme.surface.copy(0.3f))
                )
            )
            .padding(4.dp),
            contentAlignment = Alignment.TopEnd){
            Text(text = dateTimeFormat(vitalsData.dateTime),
                style = TextStyle(
                    fontFamily = poppinsSemiBold,
                    fontSize = 16.sp
                ),
                color = MaterialTheme.colorScheme.onSurface.copy(0.6f)
            )
        }

    }

}

@Composable
fun VitalsItem(
    icon : Painter,
    value : String
) {

    Row (
        modifier=Modifier
            .height(IntrinsicSize.Max),
        verticalAlignment = Alignment.CenterVertically,
    ){

        Icon(
            painter = icon,
            contentDescription = value,
            modifier=Modifier
                .size(25.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier=Modifier
            .width(6.dp))


        Text(text = value,
            fontFamily = poppinsSemiBold,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurface)

    }

}

@Preview
@Composable
private fun VitalsComponentPreview() {
    PregnacyVitalsTrackTheme {
        VitalsComponent(
            vitalsData = VitalsData(
                id = 12,
                bpm = "212",
                mmhg = "023",
                kg = "99",
                kicks = "43",
                dateTime = System.currentTimeMillis(),
            ),
            homeAction = {
                
            }
        )
    }
}


fun dateTimeFormat(time:Long):String{
    val sdf= SimpleDateFormat("EEE, dd MMM yyyy hh:mm a", Locale.getDefault())
    val date = Date(time)
    return sdf.format(date)
}