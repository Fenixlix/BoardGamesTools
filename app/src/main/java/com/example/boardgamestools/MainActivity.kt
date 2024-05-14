package com.example.boardgamestools

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.boardgamestools.core.data.model.GameTools
import com.example.boardgamestools.core.presentation.theme.BGTAppTheme
import com.example.boardgamestools.main_screen.view.screens.HomeScreen
import com.example.boardgamestools.main_screen.view.screens.SplashScreen
import com.example.boardgamestools.tool_chess_clock.view.screens.ChessClockScreenRoot
import com.example.boardgamestools.tool_coin_toss.view.screens.CoinTossScreenRoot
import com.example.boardgamestools.tool_game_craps.view.screens.CrapsScreenRoot
import com.example.boardgamestools.tool_points_counters.view.screens.PointsCounterScreenRoot
import com.example.boardgamestools.tool_triomino.view.screens.TriominoScreenRoot
import dagger.hilt.android.AndroidEntryPoint

const val home = "HOME_SCREEN"
const val splash = "SPLASH_SCREEN"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BGTAppTheme {
                Surface {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = splash
                    ) {
                        composable(route = splash) {
                            SplashScreen {
                                navController.popBackStack()
                                navController.navigate(home)
                            }
                        }
                        composable(route = home) {
                            HomeScreen {
                                navController.navigate(it)
                            }
                        }
                        composable(route = GameTools.TRIOMINO.name) {
                            TriominoScreenRoot()
                        }
                        composable(route = GameTools.CHESS_CLOCK.name) {
                            ChessClockScreenRoot()
                        }
                        composable(route = GameTools.CRAPS.name) {
                            CrapsScreenRoot()
                        }
                        composable(route = GameTools.COIN_TOSS.name) {
                            CoinTossScreenRoot()
                        }
                        composable(route = GameTools.CHARACTERS.name) {
                            // todo : make compose screen and components
                        }
                        composable(route = GameTools.POINT_COUNTERS.name) {
                            PointsCounterScreenRoot()
                        }
                    }
                }
            }
        }
    }
}