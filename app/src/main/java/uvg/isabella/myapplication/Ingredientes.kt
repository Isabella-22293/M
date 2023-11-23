package uvg.isabella.myapplication

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.w3c.dom.Text

fun LazyColumn(modifier: Modifier) {
    TODO("Not yet implemented")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Ingredientes(
    navController: NavController,
    Ingrediente: String
    viewModel: RecetasVM = viewModel
){
    viewModel.getRecetas()
    Scaffold(
        topBar={
            AppBar(
                title = "Recipe",
                navController = navController)
        }
    ){
        if (viewModel.RecipesUiState.loading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.Width(64.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
        } else {
            LazyColumn(
                Text(
                    text = "Ingredientes: $Ingrediente",
                    modifier= Modifier.padding(it)
                )
            )
        }
    }
}
