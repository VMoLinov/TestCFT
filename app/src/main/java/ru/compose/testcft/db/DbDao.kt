package ru.compose.testcft.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.compose.testcft.model.local.Response

@Dao
interface DbDao {

    @Query("SELECT * FROM response")
    suspend fun getAll(): List<Response>

    @Query("DELETE FROM response")
    suspend fun deleteAll()

    @Insert
    suspend fun insert(response: Response)
}
