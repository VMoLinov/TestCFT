package ru.compose.testcft.network

import com.google.gson.stream.MalformedJsonException
import retrofit2.HttpException
import ru.compose.testcft.model.DataConverter
import ru.compose.testcft.ui.mainscreen.MainScreenState
import java.net.UnknownHostException

class MainNetworkSourceImpl(private val api: NetworkApi) :
    MainNetworkSource {

    private val converter = DataConverter()

    override suspend fun getData(number: String): MainScreenState {
        return try {
            MainScreenState.Success(converter.fromDtoToLocal(api.getData(number)))
        } catch (e: Exception) {
            val message = when (e) {
                is UnknownHostException -> "Connection"
                is HttpException -> "Need more numbers"
                is MalformedJsonException -> "Not empty"
                else -> "Unknown exception"
            }
            MainScreenState.Error(message)
        }
    }
}
