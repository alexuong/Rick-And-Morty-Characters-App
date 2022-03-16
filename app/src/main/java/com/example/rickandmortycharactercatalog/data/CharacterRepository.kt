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
    suspend fun loadCharacters(includeOnlyAlive: Boolean): Result<Characters> {
        return withContext(ioDispatcher) {
            try {
                val status = if (includeOnlyAlive) "alive" else null
                Result.success(service.getAllCharacters(status))
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}