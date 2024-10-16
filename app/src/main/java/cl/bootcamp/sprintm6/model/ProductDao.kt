package cl.bootcamp.sprintm6.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: Product)

    @Query("SELECT * FROM product ORDER BY id")
    fun getAll(): Flow<List<Product>>

    @Query("SELECT * FROM product WHERE id = :productId")
    fun getProductById(productId: Int): Flow<Product>
}