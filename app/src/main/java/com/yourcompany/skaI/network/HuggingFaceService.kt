package com.yourcompany.skaI.network

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface HuggingFaceService {
    @GET("/api/models")
    fun listModels(
        @Query("filter") filter: String? = null,
        @Query("search") search: String? = null,
        @Query("limit") limit: Int? = null
    ): Call<List<HuggingFaceModel>>
}