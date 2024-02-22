package com.cion318.arktracker.data.character

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CharacterDao {
    @Insert
    suspend fun insertCharacter(character: Character)

    @Update
    suspend fun updateCharacter(character: Character)

    @Delete
    suspend fun deleteCharacter(character: Character)

    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<Character>
}