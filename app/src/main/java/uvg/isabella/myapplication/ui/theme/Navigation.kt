package uvg.isabella.myapplication.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uvg.isabella.myapplication.Recetas

fun composable(route: Any, any: Any) {

}

@Composable
fun Navigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationState.Recetas.route,
        modifier = modifier
    ) {
        composable(route = NavigationState.Recetas.route + "/{Recetas}") { backStackEntry ->) {
            Recetas(navController)
        }
            composable(route = NavigationState.Ingredientes.route + "/{Ingredientes}") { backStackEntry ->
                Ingredientes(navController, backStackEntry.arguments?.getString("Ingredientes") ?:"")
            }
        }
    }
}