package cl.bootcamp.sprintm6.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cl.bootcamp.sprintm6.view.DetailsView
import cl.bootcamp.sprintm6.view.HomeView
import cl.bootcamp.sprintm6.viewmodel.ProductViewModel

@Composable
fun NavManager(
    viewModel: ProductViewModel
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "Home") {
        composable("Home") {
            HomeView(viewModel, navController)
        }
        composable("DetailsView/{id}", arguments = listOf(
            navArgument("id") { type = NavType.IntType }
        )) {
            val id = it.arguments?.getInt("id") ?: 0
            DetailsView(viewModel, navController, id)
        }
    }
}