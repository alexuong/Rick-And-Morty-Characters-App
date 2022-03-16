package com.example.rickandmortycharactercatalog.data

import com.squareup.moshi.Json
import java.io.Serializable

data class Origin(
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
) : Serializable