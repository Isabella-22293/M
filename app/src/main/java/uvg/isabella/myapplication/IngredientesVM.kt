package uvg.isabella.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class IngredientesVM (private val repository: RecetasRepository = RecetassRepository()): ViewModel() {
    var recetasUi by mutableStateOf(IngredientesUi(emptyList()))
        private set

    fun getIngredientes() {
        IngredientesUi = IngredientesUi(emptyList(), loading = true)
        viewModelScope.launch {
            IngredientesUi = IngredientesUi(
                Tipo = repository.getRecipes().Recipes,
                loading = false
            )
        }
    }

    private fun IngredientesUi(Tipo: Any, loading: Boolean): IngredientesUi {

    }
}

