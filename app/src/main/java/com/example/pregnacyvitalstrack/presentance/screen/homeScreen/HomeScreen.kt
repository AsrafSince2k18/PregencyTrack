package com.example.pregnacyvitalstrack.presentance.screen.homeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pregnacyvitalstrack.presentance.designSystem.CustomTextField
import com.example.pregnacyvitalstrack.presentance.designSystem.CustomVitalsBtn
import com.example.pregnacyvitalstrack.presentance.designSystem.VitalsComponent
import com.example.pregnacyvitalstrack.presentance.screen.homeScreen.stateEvent.HomeAction
import com.example.pregnacyvitalstrack.presentance.screen.homeScreen.stateEvent.HomeState
import com.example.pregnacyvitalstrack.presentance.screen.homeScreen.stateEvent.HomeStateFlow
import com.example.pregnacyvitalstrack.presentance.ui.theme.PregnacyVitalsTrackTheme
import com.example.pregnacyvitalstrack.presentance.ui.theme.Primary
import com.example.pregnacyvitalstrack.presentance.ui.theme.Secondary
import com.example.pregnacyvitalstrack.presentance.ui.theme.Surface
import com.example.pregnacyvitalstrack.presentance.ui.theme.SurfaceVerient
import com.example.pregnacyvitalstrack.presentance.ui.theme.poppinsSemiBold
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun HomeScreenRoot(
    homeViewModel: HomeViewModel = hiltViewModel()
) {


    HomeScreen(
        homeState = homeViewModel.state,
        homeStateFlow = homeViewModel.vitalsState.collectAsState().value,
        onAction = homeViewModel::onAction,
        resetAValue = {
            homeViewModel.resetAValue()
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeState: HomeState,
    homeStateFlow: HomeStateFlow,
    resetAValue: () -> Unit,
    onAction: (HomeAction) -> Unit
) {
    var alertDialog by remember {
        mutableStateOf(false)
    }


    if (alertDialog) {
        AlertDialog(
            onDismissRequest = {
                resetAValue()
                alertDialog = false
            },
            shape = RoundedCornerShape(8.dp),
            confirmButton = {},
            containerColor = SurfaceVerient,
            title = {
                Text(
                    text = "Vitals track",
                    fontFamily = poppinsSemiBold,
                    fontSize = 18.sp
                )
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CustomTextField(
                            value = homeState.bpm,
                            onValueChange = {
                                onAction(HomeAction.Bpm(it))
                            },
                            placeHolder = "Sys Bp",
                            keyBoardType = KeyboardType.Number,
                            modifier = Modifier
                                .weight(1f)
                        )
                        Spacer(
                            modifier = Modifier
                                .width(12.dp)
                        )
                        CustomTextField(
                            value = homeState.mmHg,
                            onValueChange = {
                                onAction(HomeAction.MmHg(it))
                            },
                            placeHolder = "Dia Bp",
                            keyBoardType = KeyboardType.Number,
                            modifier = Modifier
                                .weight(1f)
                        )
                    }

                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )

                    CustomTextField(
                        value = homeState.kg,
                        onValueChange = {
                            onAction(HomeAction.Kg(it))
                        },
                        placeHolder = "Weight (in kg)",
                        keyBoardType = KeyboardType.Number,
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )

                    CustomTextField(
                        value = homeState.kicks,
                        onValueChange = {
                            onAction(HomeAction.Kicks(it))
                        },
                        placeHolder = "Baby kicks",
                        keyBoardType = KeyboardType.Number,
                        modifier = Modifier
                            .fillMaxWidth()
                    )


                    Spacer(
                        modifier = Modifier
                            .height(12.dp)
                    )

                    CustomVitalsBtn(
                        enable = homeState.required,
                        btnText = "Submit",
                        onClick = {
                            onAction(HomeAction.SubmitBtn)
                            alertDialog = false
                        },
                        color = Primary.copy(0.7f),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)

                    )

                }
            }
        )
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Track My Pregnancy",
                        color = MaterialTheme.colorScheme.surface,
                        fontFamily = poppinsSemiBold,
                        fontSize = 22.sp,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Primary
                ),
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    alertDialog = !alertDialog
                },
                containerColor = Surface,
                contentColor = MaterialTheme.colorScheme.onSurface.copy(0.7f),
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 2.dp,

                    )
            ) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }

        }
    ) { paddingValues ->
        if (homeStateFlow.vitalsItem.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Primary.copy(0.8f),
                                Secondary.copy(0.4f)
                            ),
                        ),
                    )
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Empty, Add new data",
                    fontFamily = poppinsSemiBold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Primary.copy(0.8f),
                                Secondary.copy(0.4f)
                            ),
                        )
                    )
                    .padding(paddingValues),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items = homeStateFlow.vitalsItem, key = {
                    it.id!!
                }) { vitalsItem ->
                    SwipeToDelete(item = vitalsItem,
                        onDelete = { deleteData ->
                            onAction(HomeAction.OnDeleteClick(deleteData))
                        }) { vitalsData ->
                        VitalsComponent(vitalsData = vitalsData,
                            homeAction = {
                                onAction(HomeAction.CardClick(vitalsData.id.toString()))
                                alertDialog = true
                            })
                    }

                }
            }
        }
    }

}

@Composable
fun <T> SwipeToDelete(
    item: T,
    onDelete: (T) -> Unit,
    animationDuration: Int = 1000,
    content: @Composable (T) -> Unit
) {

    var isRemoved by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val state = rememberSwipeToDismissBoxState(
        confirmValueChange = { dismiss ->
            if (dismiss == SwipeToDismissBoxValue.EndToStart) {
                scope.launch {
                    isRemoved = true
                    delay(1000)
                    onDelete(item)
                }
                true
            } else {
                false
            }
        }
    )




    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut(),
    ) {
        SwipeToDismissBox(
            state = state,
            backgroundContent = {
                DeleteBackground(swipeDelete = state)
            },
            content = {
                content(item)
            }
        )
    }


}


@Composable
fun DeleteBackground(
    swipeDelete: SwipeToDismissBoxState
) {


    val color =
        if (swipeDelete.dismissDirection == SwipeToDismissBoxValue.EndToStart) Color.Red.copy(0.7f) else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Rounded.Delete,
            contentDescription = null,
            tint = Color.White
        )
    }

}

@Preview
@Composable
private fun HomeScreenPreview() {
    PregnacyVitalsTrackTheme {
        HomeScreen(
            homeState = HomeState(),
            homeStateFlow = HomeStateFlow(),
            onAction = {},
            resetAValue = {}
        )
    }
}