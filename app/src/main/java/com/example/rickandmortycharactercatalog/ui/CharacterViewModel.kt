package com.example.rickandmortycharactercatalog.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycharactercatalog.api.CharacterService
import com.example.rickandmortycharactercatalog.data.CharacterRepository
import com.example.rickandmortycharactercatalog.data.Characters
import com.example.rickandmortycharactercatalog.data.LoadingStatus
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {
    private val repository = CharacterRepository(CharacterService.create())

    /*
     * These fields hold the characters data displayed by the UI.
     */
    private val _characters = MutableLiveData<Characters?>(null)
    val characters: LiveData<Characters?> = _characters

    private val _loadingStatus = MutableLiveData(LoadingStatus.SUCCESS)
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus

    fun loadCharacters() {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.LOADING
            val result = repository.loadCharacters()
            _characters.value = result.getOrNull()
            _loadingStatus.value = when (result.isSuccess) {
                true -> LoadingStatus.SUCCESS
                false -> LoadingStatus.ERROR
            }
        }
    }
}