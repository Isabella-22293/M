package uvg.isabella.myapplication.ui.theme

data class RecetasResponse(val recetas: List<Recetas>)

data class Meals(
    @SerializedName("idRecetas") val id: String,
    @SerializedName("strRecetas") val name: String,
    @SerializedName("strRecetas") val imageUrl: String
    @SerializedName("strDescription") val description: String
)

annotation class SerializedName(val value: String)
