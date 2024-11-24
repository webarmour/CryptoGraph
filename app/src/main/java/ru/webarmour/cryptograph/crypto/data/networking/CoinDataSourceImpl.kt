package ru.webarmour.cryptograph.crypto.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import ru.webarmour.cryptograph.crypto.core.data.networking.constructUrl
import ru.webarmour.cryptograph.crypto.core.data.networking.saveCall
import ru.webarmour.cryptograph.crypto.core.domain.util.NetworkError
import ru.webarmour.cryptograph.crypto.core.domain.util.Result
import ru.webarmour.cryptograph.crypto.core.domain.util.map
import ru.webarmour.cryptograph.crypto.data.mappers.toCoin
import ru.webarmour.cryptograph.crypto.data.networking.dto.CoinsResponseDto
import ru.webarmour.cryptograph.crypto.domain.CoinDataSource
import ru.webarmour.cryptograph.crypto.domain.CoinModel

class CoinDataSourceImpl(

    private val httpClient: HttpClient

): CoinDataSource {

    override suspend fun getCoins(): Result<List<CoinModel>, NetworkError> {
        return saveCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }
}