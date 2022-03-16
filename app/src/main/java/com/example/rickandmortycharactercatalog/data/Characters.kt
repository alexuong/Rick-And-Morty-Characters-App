package com.example.rickandmortycharactercatalog.data


import com.squareup.moshi.Json

data class Characters(
    @Json(name = "results")
    val results: List<Character>
)