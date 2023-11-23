package uvg.isabella.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RecetasVM (private val repository: RecetasRepository = RecetasRepository()): ViewModel() {
        var MealsUiState by mutableStateOf(MealsUiState(emptyList()))
        private set

        fun getRecetas() {
            MealsUiState = MealsUiState(emptyList(), loading = true)
            viewModelScope.launch {
                MealsUiState = MealsUiState(
                    Categories = repository.getMeals().Meals,
                    loading = false
                )
            }
        }
    }
}