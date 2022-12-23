package ru.compose.testcft.interactor

import ru.compose.testcft.db.DbDao
import ru.compose.testcft.model.local.Response
import ru.compose.testcft.network.MainNetworkSource
import ru.compose.testcft.ui.mainscreen.MainScreenState

class MainInteractorImpl(
    private val networkSource: MainNetworkSource,
    private val localSource: DbDao
) : MainScreenInteractor {

    override suspend fun getNetworkData(number: String): MainScreenState =
        networkSource.getData(number)

    override suspend fun insertData(response: Response) = localSource.insert(response)
}
