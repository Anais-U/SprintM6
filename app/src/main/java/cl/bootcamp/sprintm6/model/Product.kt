package cl.bootcamp.sprintm6.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(
   @PrimaryKey(autoGenerate = true) val id: Int,
   @ColumnInfo(name = "name") val name: String,
   @ColumnInfo(name = "price") val price: Int,
   @ColumnInfo(name = "image") val image: String,
   @ColumnInfo(name = "description") val description: String? = null,
   @ColumnInfo(name = "last_price") val lastPrice: Int? = null,
   @ColumnInfo(name = "credit") val credit: Boolean? = null

)