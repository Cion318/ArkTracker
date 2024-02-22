package com.cion318.arktracker.data.character

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterViewModel(private val characterDao: CharacterDao) : ViewModel() {
    suspend fun insertCharacter(character: Character) {
        withContext(Dispatchers.IO) {
            characterDao.insertCharacter(character)
        }
    }

    suspend fun updateCharacter(character: Character) {
        withContext(Dispatchers.IO) {
            characterDao.updateCharacter(character)
        }
    }

    suspend fun deleteCharacter(character: Character) {
        withContext(Dispatchers.IO) {
            characterDao.deleteCharacter(character)
        }
    }

    suspend fun getAllCharacters(): List<Character> {
        return withContext(Dispatchers.IO) {
            characterDao.getAllCharacters()
        }
    }
}