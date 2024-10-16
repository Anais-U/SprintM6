package cl.bootcamp.sprintm6.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import cl.bootcamp.sprintm6.model.Product
import cl.bootcamp.sprintm6.model.ProductDao

@Database(
    entities = [Product::class],
    version = 1
)
abstract class DbDataSource: RoomDatabase() {
    abstract fun productDao(): ProductDao
}