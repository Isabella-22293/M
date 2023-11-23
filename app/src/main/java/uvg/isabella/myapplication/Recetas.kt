package uvg.isabella.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AppBar(title: String, navController: NavController) {

}

fun LazyHorizontalGrid(Column: GridCells.Fixed, contentePadding: PaddingValues, content: LazyGridScope.() -> Unit) {

}

fun <MealsViewModel> viewModesl(): MealsViewModel {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun <MealsViewModel> Recetas(
        navController: NavController,
        Receta: String
        viewModel: MealsViewModel = viewModesl()
    ){
        viewModel.getmeals()
        Scaffold(
            topBar={
                AppBar(
                    title = "Meals",
                    navController = navController)
            }
        ){
            if (viewModel.MealsUiState.loading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                LazyHorizontalGrid(
                    Column = GridCells.Fixed(1),
                    contentePadding = it
                ) {
                    items(viewModel.MealsUiState.Meals) { meal ->
                        Card(
                            shape = RoundedCornerShape(8.dp),
                            elevation = 2.dp,
                            modifier = Modifier.filMaxWidth().padding(top = 16.dp)
                                    onClick = {
                                navController.navigate("${NavigationState.Meals.route} /${meal.name}")
                            }
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                                VerticalAligment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Meals Screen : $Meals",
                                    modifier= androidx.compose.ui.Modifier.padding(it)
                                )
                                AsyncImage(
                                    model = meal.imageUrl,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }


}