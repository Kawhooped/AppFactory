package com.appfactory.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import kotlinx.serialization.Serializable
import java.util.concurrent.TimeUnit

// Data Models
@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val token: String,
    val userId: String,
    val userName: String
)

@Serializable
data class ProductResponse(
    val id: String,
    val name: String,
    val price: Float,
    val description: String,
    val imageUrl: String
)

@Serializable
data class OrderRequest(
    val userId: String,
    val productIds: List<String>,
    val totalAmount: Float
)

@Serializable
data class OrderResponse(
    val orderId: String,
    val status: String,
    val totalAmount: Float,
    val createdAt: Long
)

// API Services
interface AuthService {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("auth/signup")
    suspend fun signup(@Body request: LoginRequest): LoginResponse

    @POST("auth/logout")
    suspend fun logout(): Boolean
}

interface ProductService {
    @GET("products")
    suspend fun getProducts(): List<ProductResponse>

    @GET("products/{id}")
    suspend fun getProduct(@Path("id") id: String): ProductResponse

    @GET("products/search")
    suspend fun searchProducts(@Query("q") query: String): List<ProductResponse>
}

interface OrderService {
    @POST("orders")
    suspend fun createOrder(@Body request: OrderRequest): OrderResponse

    @GET("orders/{id}")
    suspend fun getOrder(@Path("id") id: String): OrderResponse

    @GET("users/{userId}/orders")
    suspend fun getUserOrders(@Path("userId") userId: String): List<OrderResponse>

    @PUT("orders/{id}")
    suspend fun updateOrder(
        @Path("id") id: String,
        @Body request: OrderRequest
    ): OrderResponse
}

// Retrofit Client
object ApiClient {
    private const val BASE_URL = "https://api.example.com/v1/"

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val authService: AuthService = retrofit.create(AuthService::class.java)
    val productService: ProductService = retrofit.create(ProductService::class.java)
    val orderService: OrderService = retrofit.create(OrderService::class.java)
}
