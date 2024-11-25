package ru.webarmour.cryptograph.crypto.presentation.coin_list

import ru.webarmour.cryptograph.crypto.core.domain.util.NetworkError

sealed interface CoinListEvent {

    data class Error(val message: NetworkError) : CoinListEvent

}