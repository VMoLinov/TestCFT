package ru.compose.testcft.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.compose.testcft.model.local.Bank
import ru.compose.testcft.model.local.Country
import ru.compose.testcft.model.local.Response

@Database(entities = [Response::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getDao(): DbDao
}
