package ru.webarmour.cryptograph.crypto.data.mappers

import ru.webarmour.cryptograph.crypto.data.networking.dto.CoinDto
import ru.webarmour.cryptograph.crypto.data.networking.dto.CoinPriceDto
import ru.webarmour.cryptograph.crypto.domain.CoinModel
import ru.webarmour.cryptograph.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

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

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant.ofEpochMilli(time)
            .atZone(ZoneId.systemDefault())
    )
}