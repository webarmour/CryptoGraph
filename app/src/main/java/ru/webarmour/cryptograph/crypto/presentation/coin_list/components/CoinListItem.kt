package ru.webarmour.cryptograph.crypto.presentation.coin_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.webarmour.cryptograph.crypto.domain.CoinModel
import ru.webarmour.cryptograph.crypto.presentation.models.CoinUIModel
import ru.webarmour.cryptograph.crypto.presentation.models.toCoinUi
import ru.webarmour.cryptograph.theme.CryptoGraphTheme

@Composable
fun CoinListItem(
    coinUIModel: CoinUIModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }
    Row(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),

        ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = coinUIModel.iconRes),
            contentDescription = coinUIModel.name,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(85.dp)
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = coinUIModel.symbol,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = contentColor
            )
            Text(
                text = coinUIModel.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = contentColor
            )
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$ ${coinUIModel.priceUsd.formatted}",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = contentColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            PriceChange(change = coinUIModel.changePercent24Hr)
        }
    }
}

@PreviewLightDark
@Composable
fun CoinListItemPreview(modifier: Modifier = Modifier) {
    CryptoGraphTheme {
        CoinListItem(
            coinUIModel = previewCoin,
            onClick = { /*TODO*/ },
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}

internal val previewCoin = CoinModel(
    id = "bitcoin",
    rank = 1,
    name = "Bitcoin",
    symbol = "BTC",
    marketCapUsd = 1231273958896.75,
    priceUsd = 62828.15,
    changePercent24Hr = 0.1
).toCoinUi()
















