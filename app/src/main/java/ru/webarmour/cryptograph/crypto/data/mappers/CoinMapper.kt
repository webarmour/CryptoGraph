package ru.webarmour.cryptograph.crypto.data.mappers

import ru.webarmour.cryptograph.crypto.data.networking.dto.CoinDto
import ru.webarmour.cryptograph.crypto.domain.CoinModel

fun CoinDto.toCoin(): CoinModel {
    return CoinModel(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}