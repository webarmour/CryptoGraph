package ru.webarmour.cryptograph.crypto.presentation.coin_list

import ru.webarmour.cryptograph.crypto.presentation.models.CoinUIModel






sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUIModel) : CoinListAction
}