package ru.webarmour.cryptograph.crypto.domain

import ru.webarmour.cryptograph.crypto.core.domain.util.NetworkError
import ru.webarmour.cryptograph.crypto.core.domain.util.Result

interface CoinDataSource {

    suspend fun getCoins(): Result<List<CoinModel>, NetworkError>
}