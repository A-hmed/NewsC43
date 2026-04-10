package com.route.newsc43

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.route.newsc43.ui.screens.home.HomeScreen
import com.route.newsc43.ui.screens.maps.MapScreenWrapper
import com.route.newsc43.ui.screens.splash.SplashScreen
import com.route.newsc43.ui.utils.HomeRoute
import com.route.newsc43.ui.utils.MapRoute
import com.route.newsc43.ui.utils.SplashRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MapRoute,
    ) {
        composable<HomeRoute> {
            HomeScreen(navController)
        }
        composable<SplashRoute> {
            SplashScreen(navController)
        }
        composable<MapRoute> {
            MapScreenWrapper()
        }

    }

}

@Composable
@Preview
fun AppPreview() {
    App()
}

//Http Request :
//Link: BaseUrl/Api(EndPoint)?QueryParameters
//Body: Json(JAVA SCRIPT OBJECT NOTATION) - xml - html
//Headers:Json
// -Type(Get - Post - Update - Put - Patch - Delete)

//Http Response:
//Status code (Int)
//Body: Json
//data class Person(val name: String, val age: Int,val freinds: List<Person>? = null)
//val test = Person("mohamed", 11)
//val p1 = Person("ahmed", 10, listOf(test))
////Json
//{"String": String - num - boolean - null - json - <Json>[]}
