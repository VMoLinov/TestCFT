package ru.compose.testcft.navigation

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import ru.compose.testcft.model.local.Response

class ResponseNav : NavType<Response>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): Response? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Response {
        return Gson().fromJson(value, Response::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Response) {
        bundle.putParcelable(key, value)
    }
}
