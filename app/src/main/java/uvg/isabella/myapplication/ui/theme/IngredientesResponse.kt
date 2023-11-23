package uvg.isabella.myapplication.ui.theme

data class IngredientesResponse(val ingredientes: List<Ingredientes>)

data class Ingredientes(
    @SerializedName("idIngredient") val id: String,
)

