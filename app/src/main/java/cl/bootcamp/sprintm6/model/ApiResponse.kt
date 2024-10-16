package cl.bootcamp.sprintm6.model

data class ApiResponse(
    val results: ArrayList<DetailsProduct>
)

data class DetailsProduct(
    val id: Int,
    val name: String,
    val price: Int,
    val image: String,
    val description: String?,
    val lastPrice: Int?,
    val credit: Boolean?
)
