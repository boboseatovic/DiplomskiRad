package hr.bornaseatovic.myapplication

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import hr.bornaseatovic.myapplication.main.features.calculation.map.MapScreen
import hr.bornaseatovic.myapplication.main.features.calculator.CalculatorScreen
import hr.bornaseatovic.myapplication.main.features.calculator.FourthScreen
import hr.bornaseatovic.myapplication.main.features.home.HomeScreen
import hr.bornaseatovic.myapplication.main.navigation.NavigationManager
import hr.bornaseatovic.myapplication.main.navigation.destinations.HomeDestinations
import hr.bornaseatovic.myapplication.main.navigation.events.NavigatorEvent
import hr.bornaseatovic.myapplication.ui.theme.MyApplicationTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupScreenForCompose()

        setContent {
            MyApplicationTheme(darkTheme = false) {
                val systemUiController = rememberSystemUiController()

                DisposableEffect(key1 = systemUiController, effect = {
                    systemUiController.isStatusBarVisible = false
                    systemUiController.systemBarsBehavior =
                        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    onDispose {}
                })

                val navController = rememberAnimatedNavController()

                LaunchedEffect(key1 = navController) {
                    navigationManager.navigationEvents.collect { event ->
                        when (event) {
                            is NavigatorEvent.Directions -> navController.navigate(
                                event.destination,
                                event.builder
                            )
                            NavigatorEvent.GoBack -> navController.popBackStack()
//                            is NavigatorEvent.NavigateBackTo -> {
//                                navController.backQueue.find { entry -> entry.destination.route == event.destination }
//                                    ?.let {
//                                        navController.popBackStack(it.destination.id, false)
//                                    }
//                            }
                        }
                    }
                }
                AnimatedNavHost(
                    navController = navController,
                    startDestination = "pager"
                ) {
                    composable("pager") {
                        CalculatorScreen {
                            navigationManager.navigate("fourth_screen")
                        }
                    }
                    composable(HomeDestinations.homeScreen()) {
                        HomeScreen()
//                        CalculatorScreen {
//                            navController.navigate("fourth_screen")
//                        }
                    }
                    composable(
                        "fourth_screen",
                        enterTransition = {
                            EnterTransition.None
                        }
                    ) {
                        FourthScreen {
                            navController.popBackStack("home_route", true, true)
                        }
                    }
                    composable(
                        route = HomeDestinations.mapScreen(),
                        enterTransition = {
                            slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(300, easing = EaseInOut))
                        },
                        exitTransition = {
                            slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down, tween(300, easing = EaseInOut))
                        }
                    ) {
                        MapScreen()
                    }
                }
            }
        }
    }

    private fun setupScreenForCompose() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.attributes.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}