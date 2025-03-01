package com.example.pregnacyvitalstrack.presentance.naviagation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pregnacyvitalstrack.presentance.screen.homeScreen.HomeScreenRoot
import com.example.pregnacyvitalstrack.presentance.screen.onBoardScreen.OnBoardScreenRoot

@Composable
fun NavGraph(
    startDestination: NavRoot,
    navHostController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ){

        composable<NavRoot.OnBoardScreen>{
            OnBoardScreenRoot(
                onHome = {
                    navHostController.popBackStack()
                    navHostController.navigate(NavRoot.HomeScreen)
                }
            )
        }
        composable<NavRoot.HomeScreen>{
            HomeScreenRoot()
        }
    }

}