package com.example.testhero.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.testhero.view.HeroAll
import com.example.testhero.view.HeroDetail
import com.example.testhero.viewmodel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Navigate(
    navController: NavHostController,
    startDestination: String) {
    NavHost(navController, startDestination = startDestination,
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition = { fadeOut(animationSpec = tween(0)) },
        popEnterTransition = { fadeIn(animationSpec = tween(0)) },
        popExitTransition = { fadeOut(animationSpec = tween(0)) }) {
        composable("HeroAll") { HeroAll(navController) }
        composable("HeroDetail/{heroId}") { backStackEntry ->
            val heroId = backStackEntry.arguments?.getString("heroId")?.toIntOrNull()
            val hero = viewModel<MainViewModel>().heros.value?.find { it.id == heroId }

            if (hero != null) {
                HeroDetail(
                    hero = hero,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}