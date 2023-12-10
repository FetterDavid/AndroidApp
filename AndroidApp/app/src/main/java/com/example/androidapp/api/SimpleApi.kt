package com.example.androidapp.api

import com.example.androidapp.data.CatFact
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("fact")
    suspend fun getPost(): Response<CatFact>

}