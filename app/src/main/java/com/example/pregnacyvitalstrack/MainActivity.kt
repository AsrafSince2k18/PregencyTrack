package com.example.pregnacyvitalstrack

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.pregnacyvitalstrack.presentance.naviagation.NavGraph
import com.example.pregnacyvitalstrack.presentance.screen.onBoardScreen.OnBoardViewModel
import com.example.pregnacyvitalstrack.presentance.ui.theme.PregnacyVitalsTrackTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

     private val viewModel: OnBoardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        //TODO we don't use setTheme avoid blank screen before app open
        setTheme(R.style.Theme_PregnacyVitalsTrack)
        installSplashScreen().let {
           it.setKeepOnScreenCondition {
                viewModel.state.value
            }
        }
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            PregnacyVitalsTrackTheme {
                Surface {
                   SinglePermission {
                       NavGraph(
                           startDestination = viewModel.root
                       )
                   }
                }
            }
        }
    }
}

@SuppressLint("InlinedApi")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SinglePermission(
    onHome : @Composable () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current
    val postNotification = rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)
    Scaffold(
        modifier= Modifier
            .fillMaxSize(),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {paddingValue->
        when(postNotification.status){
            PermissionStatus.Granted -> {
                onHome()
            }else ->{
            //shouldShowRationale don't allow kudatha
            // ithulea erukura condition work yagum
            if(postNotification.status.shouldShowRationale){
                LaunchedEffect(key1 = Unit) {
                    val result = snackbarHostState.showSnackbar(
                        message = "Permission denied",
                        actionLabel = "Go to setting"
                    )
                    if(SnackbarResult.ActionPerformed == result){
                        val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.fromParts("package",context.packageName,null)
                        )
                        context.startActivity(intent)
                    }
                }
            }else{
                //1st time dialog box show painnum
                postNotification.launchPermissionRequest()
            }
        }
        }
    }
}
