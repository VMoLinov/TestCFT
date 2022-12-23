package ru.compose.testcft.interactor

import ru.compose.testcft.db.DbDao
import ru.compose.testcft.model.local.Response

class HistoryInteractorImpl(private val dbDao: DbDao) : HistoryScreenInteractor {

    override suspend fun getAll(): List<Response> = dbDao.getAll()

    override suspend fun deleteAll() = dbDao.deleteAll()
}
