package cl.bootcamp.sprintm6.state

data class ProductState(
    val name: String = "",
    val price: Int = 0,
    val image: String = "",
    val description: String? = null,
    val lastPrice: Int? = null,
    val credit: Boolean? = null
)


