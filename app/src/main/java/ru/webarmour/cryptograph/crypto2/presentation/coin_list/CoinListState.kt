package ru.webarmour.cryptograph.crypto2.presentation.coin_list

import androidx.compose.runtime.Immutable
import ru.webarmour.cryptograph.crypto2.presentation.models.CoinUIModel


@Immutable
data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<CoinUIModel> = emptyList(),
    val selectedCoin: CoinUIModel? = null
) {
}