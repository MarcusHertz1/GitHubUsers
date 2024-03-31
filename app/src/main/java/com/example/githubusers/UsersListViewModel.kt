package com.example.githubusers

import androidx.lifecycle.ViewModel
import com.example.githubusers.UsersListViewModel.Companion.MAIN_URL
import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class UsersListViewModel: ViewModel() {

    companion object{
        const val MAIN_URL = "https://api.github.com/"
    }
    fun getUsersDate() = RetrofitClient.apiClient.getUsersDateList()
    fun getSingleUserDate(login: String) = RetrofitClient.apiClient.getSingleUserDateList(login)

}

class UsersDataClass(
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("login")
    var login: String,
    @SerializedName("id")
    var id: Int
)

class SingleUserDataClass(
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("email")
    var email: String,
    @SerializedName("company")
    var company: String,
    @SerializedName("following")
    var following: Int,
    @SerializedName("followers")
    var followers: Int,
    @SerializedName("created_at")
    var createdAt: String,
)

object RetrofitClient {
    val apiClient: RetrofitServices by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(MAIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return@lazy retrofit.create(RetrofitServices::class.java)
    }
}

interface RetrofitServices {
    @GET("users")
    fun getUsersDateList(): Single<MutableList<UsersDataClass>>
    @GET("users/{login}")
    fun getSingleUserDateList(@Path("login") login: String): Single<SingleUserDataClass>
}