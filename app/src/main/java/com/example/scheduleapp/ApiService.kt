package com.example.scheduleapp

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.Body
import retrofit2.http.POST


// Data class representing a course.
data class Course(
    val id: Int,
    val name: String,
    val code: String,
    val professorId: Int,
    val dayOfWeek: Int,
    val startTime: String,
    val endTime: String,
    val durationWeeks: Int,
    val startDate: String
)

// Data class matching the API response JSON structure.
data class CoursesResponse(
    @SerializedName("\$id")
    val id: String,
    @SerializedName("\$values")
    val courses: List<Course>
)

data class LoginRequest(val username: String, val password: String)

data class LoginResponse(
    val token: String,
    val username: String,
    val role: Int,
    // Add other fields as returned by your API if needed.
)



// Retrofit API service interface.
interface ApiService {

    @POST("api/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
    @GET("api/course")
    suspend fun getCourses(): CoursesResponse
}

interface AuthApi {
    @POST("api/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse
}




// A singleton object to build the Retrofit client.
object ApiClient {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Increase timeout values here
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)  // Time allowed to establish connection
        .readTimeout(30, TimeUnit.SECONDS)     // Time allowed to read the server response
        .writeTimeout(30, TimeUnit.SECONDS)    // Time allowed to send data to the server
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://ams-sz8c.onrender.com/")  // Ensure the base URL ends with a '/'
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
