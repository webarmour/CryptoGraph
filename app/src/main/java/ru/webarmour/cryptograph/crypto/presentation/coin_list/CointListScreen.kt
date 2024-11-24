package ru.webarmour.cryptograph.crypto.presentation.coin_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import ru.webarmour.cryptograph.crypto.presentation.coin_list.components.CoinListItem
import ru.webarmour.cryptograph.crypto.presentation.coin_list.components.previewCoin
import ru.webarmour.cryptograph.theme.CryptoTrackerTheme


@Composable
fun CoinListScreen(
    state: CoinListState,
    modifier: Modifier = Modifier,
) {
    if (state.isLoading) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.coins) {
                CoinListItem(
                    coinUIModel = it, onClick = {},
                    modifier = modifier.fillMaxWidth()
                )
                HorizontalDivider()
            }
        }
    }

}

@PreviewLightDark
@Composable
private fun CoinListScreenPreview() {
    CryptoTrackerTheme {
        CoinListScreen(state = CoinListState(
            coins = (1..100).map {
                previewCoin.copy(id = it.toString())
            }
        ),
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}