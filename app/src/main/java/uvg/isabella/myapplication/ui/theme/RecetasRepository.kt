package uvg.isabella.myapplication.ui.theme

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecetasRepository (private val webService: RecetasWebService = RecetasWebService()){
    suspend fun getMeals(): RecetasResponse {
        return withContext(Dispatchers.IO) {
            webService.getmeals()
        }
    }
    suspend fun getRecipes(): IngredientesResponse {
        return withContext(Dispatchers.IO) {
            webService.getRecipes()
        }
    }
}

}