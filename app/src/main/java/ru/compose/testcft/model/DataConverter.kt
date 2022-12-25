package ru.compose.testcft.model

import ru.compose.testcft.model.local.Bank
import ru.compose.testcft.model.local.Country
import ru.compose.testcft.model.local.Number
import ru.compose.testcft.model.local.Response
import ru.compose.testcft.model.network.ResponseDto

class DataConverter {

    fun fromDtoToLocal(responseDto: ResponseDto, number: String): Response {
        return Response(
            bank = Bank(
                city = responseDto.bankDto?.city,
                name = responseDto.bankDto?.name,
                phone = responseDto.bankDto?.phone,
                url = responseDto.bankDto?.url
            ),
            numbers = number,
            brand = responseDto.brand?.replaceFirstChar { it.uppercaseChar() },
            country = Country(
                alpha2 = responseDto.countryDto?.alpha2,
                currency = responseDto.countryDto?.currency,
                emoji = responseDto.countryDto?.emoji,
                latitude = responseDto.countryDto?.latitude,
                longitude = responseDto.countryDto?.longitude,
                name = responseDto.countryDto?.name,
                numeric = responseDto.countryDto?.numeric
            ),
            number = Number(
                length = responseDto.numberDto?.length,
                luhn = responseDto.numberDto?.luhn
            ),
            prepaid = responseDto.prepaid,
            scheme = responseDto.scheme?.replaceFirstChar { it.uppercaseChar() },
            type = responseDto.type?.replaceFirstChar { it.uppercaseChar() }
        )
    }
}
