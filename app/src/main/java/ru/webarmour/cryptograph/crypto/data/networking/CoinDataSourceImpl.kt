package ru.webarmour.cryptograph.crypto.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import ru.webarmour.cryptograph.crypto.core.data.networking.constructUrl
import ru.webarmour.cryptograph.crypto.core.data.networking.saveCall
import ru.webarmour.cryptograph.crypto.core.domain.util.NetworkError
import ru.webarmour.cryptograph.crypto.core.domain.util.Result
import ru.webarmour.cryptograph.crypto.core.domain.util.map
import ru.webarmour.cryptograph.crypto.data.mappers.toCoin
import ru.webarmour.cryptograph.crypto.data.mappers.toCoinPrice
import ru.webarmour.cryptograph.crypto.data.networking.dto.CoinHistoryDto
import ru.webarmour.cryptograph.crypto.data.networking.dto.CoinsResponseDto
import ru.webarmour.cryptograph.crypto.domain.CoinDataSource
import ru.webarmour.cryptograph.crypto.domain.CoinModel
import ru.webarmour.cryptograph.crypto.domain.CoinPrice
import java.time.ZoneId
import java.time.ZonedDateTime

class CoinDataSourceImpl(

    private val httpClient: HttpClient,

    ) : CoinDataSource {

    override suspend fun getCoins(): Result<List<CoinModel>, NetworkError> {
        return saveCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime,
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        return saveCall<CoinHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter(key = "interval", value = "h6")
                parameter(key = "start", startMillis)
                parameter(key = "end", endMillis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }
}