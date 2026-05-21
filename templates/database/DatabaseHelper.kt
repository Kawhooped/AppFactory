package com.appfactory.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// User Entity
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val email: String,
    val createdAt: Long = System.currentTimeMillis()
)

// Product Entity
@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val price: Float,
    val description: String,
    val imageUrl: String
)

// Order Entity
@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey
    val id: String,
    val userId: String,
    val totalAmount: Float,
    val status: String,
    val createdAt: Long = System.currentTimeMillis()
)

// DAOs
@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): UserEntity?

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserEntity>>
}

@Dao
interface ProductDao {
    @Insert
    suspend fun insertProduct(product: ProductEntity)

    @Update
    suspend fun updateProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getProductById(productId: String): ProductEntity?

    @Query("SELECT * FROM products")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE name LIKE '%' || :query || '%'")
    fun searchProducts(query: String): Flow<List<ProductEntity>>
}

@Dao
interface OrderDao {
    @Insert
    suspend fun insertOrder(order: OrderEntity)

    @Update
    suspend fun updateOrder(order: OrderEntity)

    @Delete
    suspend fun deleteOrder(order: OrderEntity)

    @Query("SELECT * FROM orders WHERE id = :orderId")
    suspend fun getOrderById(orderId: String): OrderEntity?

    @Query("SELECT * FROM orders WHERE userId = :userId")
    fun getOrdersByUserId(userId: String): Flow<List<OrderEntity>>

    @Query("SELECT * FROM orders ORDER BY createdAt DESC")
    fun getAllOrders(): Flow<List<OrderEntity>>
}

// Database
@Database(
    entities = [UserEntity::class, ProductEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun productDao(): ProductDao
    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}
