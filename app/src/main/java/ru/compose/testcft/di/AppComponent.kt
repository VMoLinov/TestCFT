package ru.compose.testcft.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.compose.testcft.db.AppDataBase
import ru.compose.testcft.db.DbDao
import ru.compose.testcft.interactor.HistoryInteractorImpl
import ru.compose.testcft.interactor.HistoryScreenInteractor
import ru.compose.testcft.interactor.MainInteractorImpl
import ru.compose.testcft.interactor.MainScreenInteractor
import ru.compose.testcft.network.MainNetworkSource
import ru.compose.testcft.network.MainNetworkSourceImpl
import ru.compose.testcft.network.NetworkApi

@Module
@InstallIn(SingletonComponent::class)
object AppComponent {

    @Provides
    fun mainNetwork(api: NetworkApi): MainNetworkSource = MainNetworkSourceImpl(api)

    @Provides
    fun networkApi(): NetworkApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NetworkApi::class.java)
    }

    @Provides
    fun localBase(@ApplicationContext context: Context): DbDao {
        return Room.databaseBuilder(context, AppDataBase::class.java, LOCAL_BASE_NAME).build()
            .getDao()
    }

    @Provides
    fun mainScreenInteractor(
        mainNetworkSource: MainNetworkSource,
        localBase: DbDao
    ): MainScreenInteractor = MainInteractorImpl(mainNetworkSource, localBase)

    @Provides
    fun historyScreenInteractor(
        localBase: DbDao
    ): HistoryScreenInteractor = HistoryInteractorImpl(localBase)

    private const val LOCAL_BASE_NAME = "base"
    private const val BASE_URL = "https://lookup.binlist.net/"
}
