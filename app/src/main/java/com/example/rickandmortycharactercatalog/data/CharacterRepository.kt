package com.example.rickandmortycharactercatalog.data

import com.example.rickandmortycharactercatalog.api.CharacterService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class CharacterRepository(
    private val service: CharacterService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun loadCharacters(): Result<Characters> {
        return withContext(ioDispatcher) {
            try {
                Result.success(service.getAllCharacters())
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}