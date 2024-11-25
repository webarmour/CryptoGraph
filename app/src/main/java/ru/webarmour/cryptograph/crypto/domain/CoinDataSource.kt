package ru.webarmour.cryptograph.crypto.domain

import ru.webarmour.cryptograph.crypto.core.domain.util.NetworkError
import ru.webarmour.cryptograph.crypto.core.domain.util.Result
import java.time.ZonedDateTime

interface CoinDataSource {

    suspend fun getCoins(): Result<List<CoinModel>, NetworkError>

    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>,NetworkError>
}
