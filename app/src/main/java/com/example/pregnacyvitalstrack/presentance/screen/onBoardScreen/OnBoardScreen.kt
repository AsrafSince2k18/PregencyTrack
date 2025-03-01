package com.example.pregnacyvitalstrack.presentance.screen.onBoardScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pregnacyvitalstrack.R
import com.example.pregnacyvitalstrack.presentance.designSystem.CustomActionButton
import com.example.pregnacyvitalstrack.presentance.ui.theme.PregnacyVitalsTrackTheme
import com.example.pregnacyvitalstrack.presentance.ui.theme.bloodPressure
import com.example.pregnacyvitalstrack.presentance.ui.theme.heart
import com.example.pregnacyvitalstrack.presentance.ui.theme.heartBackground
import com.example.pregnacyvitalstrack.presentance.ui.theme.poppinsLight
import com.example.pregnacyvitalstrack.presentance.ui.theme.poppinsSemiBold
import com.example.pregnacyvitalstrack.presentance.ui.theme.roboto
import com.example.pregnacyvitalstrack.presentance.ui.theme.weight
import com.example.pregnacyvitalstrack.presentance.ui.theme.weightBackground
import kotlinx.coroutines.launch


@Composable
fun OnBoardScreenRoot(
    onBoardViewModel: OnBoardViewModel = hiltViewModel(),
    onHome: () -> Unit
) {
    OnBoardScreen(onBoardAction = onBoardViewModel::onEvent,
        onHome = {onHome()})

}

@Composable
fun OnBoardScreen(
    onHome :() -> Unit,
    onBoardAction: (OnBoardAction) -> Unit
) {
    val scope = rememberCoroutineScope()

    val onBoardDataItem: List<OnBoardData> = listOf(
        OnBoardData(
            image = R.drawable.blood_pressure,
            color = bloodPressure,
            primaryText = stringResource(R.string.blood_pressere),
            secondaryText = stringResource(R.string.blood_pressere_sendary),
            txColor = bloodPressure
        ),
        OnBoardData(
            image = R.drawable.hear_rate,
            color = heartBackground,
            primaryText = stringResource(R.string.heart_rate),
            secondaryText = stringResource(R.string.heart_rate_secondary),
            txColor = heart
        ),
        OnBoardData(
            image = R.drawable.weight,
            color = weightBackground,
            primaryText = stringResource(R.string.weight),
            secondaryText = stringResource(R.string.weight_track),
            txColor = weight
        )

    )

    val pagerState = rememberPagerState {
        onBoardDataItem.size
    }


    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .background(onBoardDataItem[pagerState.currentPage].color)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
        ) {
            OnBoardItem(
                list = onBoardDataItem,
                pagerState = pagerState
            )
        }


        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 100.dp))
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.TopCenter
        ) {

            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 22.dp)
                    .fillMaxWidth()
            ) {
                Indicator(
                    pagerState = pagerState,
                    item = onBoardDataItem
                )

                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                )

                Text(
                    text = onBoardDataItem[pagerState.currentPage].primaryText,
                    fontFamily = poppinsSemiBold,
                    color = onBoardDataItem[pagerState.currentPage].txColor,
                    textAlign = TextAlign.End,
                    fontSize = 22.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )

                Text(
                    text = onBoardDataItem[pagerState.currentPage].secondaryText,
                    fontFamily = poppinsLight,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                )

                val screenEnd = pagerState.currentPage == onBoardDataItem.size-1
                if(screenEnd){
                    AnimatedVisibility(visible = true,
                        enter = fadeIn() + expandHorizontally(),
                        exit = fadeOut() + shrinkOut()
                    ){
                        CustomActionButton(
                            btnText = "Get started",
                            onClick = {
                                onBoardAction(OnBoardAction.GetStarted)
                                onHome()
                            },
                            color = onBoardDataItem[pagerState.currentPage].txColor,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxWidth()
                        )

                    }

                } else{
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        TextButton(
                            onClick = {
                                scope.launch {
                                    pagerState.scrollToPage(page = onBoardDataItem.size)
                                }
                            }
                        ) {
                            Text(
                                text = "Skip Now",
                                color = MaterialTheme.colorScheme.onSurface,
                                fontSize = 16.sp,
                                fontFamily = roboto,
                                fontWeight = FontWeight.SemiBold
                            )


                        }


                        Box(
                            modifier = Modifier
                                .size(55.dp)
                                .clip(CircleShape)
                                .border(
                                    width = 12.dp,
                                    color = onBoardDataItem[pagerState.currentPage].txColor,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        pagerState.scrollToPage(page = pagerState.targetPage+1)
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                                    contentDescription = "next",
                                    tint = onBoardDataItem[pagerState.currentPage].txColor
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun OnBoardItem(
    list: List<OnBoardData>,
    pagerState: PagerState
) {


    Image(
        painter = painterResource(id = list[pagerState.currentPage].image),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxSize()
    )


}


@Composable
private fun Indicator(
    pagerState: PagerState,
    item: List<OnBoardData>
) {


    Row(
        modifier = Modifier
            .padding(top = 18.dp)
            .padding(bottom = 12.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(item.size) { page ->
            Box(
                modifier = Modifier
                    .width(if (pagerState.currentPage == page) 38.dp else 12.dp)
                    .height(8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        if (pagerState.currentPage == page) item[pagerState.currentPage].color else item[pagerState.currentPage].color.copy(
                            0.6f
                        )
                    )
            )
            Spacer(
                modifier = Modifier
                    .width(7.dp)
            )
        }

    }


}


@Preview
@Composable
private fun OnBoardPreview() {
    PregnacyVitalsTrackTheme {
        OnBoardScreen(
            onBoardAction = {},
            onHome = {}
        )
    }
}