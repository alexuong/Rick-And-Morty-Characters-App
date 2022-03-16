package com.example.rickandmortycharactercatalog.api

import com.example.rickandmortycharactercatalog.data.Characters
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface CharacterService {
    @GET("character")
    suspend fun getAllCharacters(
        @Query("status") status: String?
    ): Characters

    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"

        /**
         * This method is used to create an instance of the [CharacterService] interface.
         */
        fun create() : CharacterService {
            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(CharacterService::class.java)
        }
    }
}