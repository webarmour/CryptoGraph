package ru.webarmour.cryptograph.crypto.domain

import java.time.ZonedDateTime
import java.util.stream.DoubleStream.DoubleMapMultiConsumer


data class CoinPrice(
    val priceUsd: Double,
    val dateTime: ZonedDateTime
)
