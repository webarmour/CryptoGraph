@file:OptIn(ExperimentalMaterial3AdaptiveApi::class)

package ru.webarmour.cryptograph.crypto.core.navigation

import android.widget.Toast
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.webarmour.cryptograph.crypto.core.presentation.util.ObserveAsEvents
import ru.webarmour.cryptograph.crypto.core.presentation.util.toStringError
import ru.webarmour.cryptograph.crypto.presentation.coin_detail.CoinDetailScreen
import ru.webarmour.cryptograph.crypto.presentation.coin_list.CoinListAction
import ru.webarmour.cryptograph.crypto.presentation.coin_list.CoinListEvent
import ru.webarmour.cryptograph.crypto.presentation.coin_list.CoinListScreen
import ru.webarmour.cryptograph.crypto.presentation.coin_list.CoinListViewModel


@Composable
fun AdaptiveCoinListDetailPane(
    modifier: Modifier = Modifier,
    viewModel: CoinListViewModel = koinViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    ObserveAsEvents(events = viewModel.events) { event ->
        when (event) {
            is CoinListEvent.Error -> {
                Toast.makeText(
                    context,
                    event.message.toStringError(context),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                CoinListScreen(state = state, onAction = {action ->
                    viewModel.onAction(action)
                    when(action){
                        is CoinListAction.OnCoinClick -> {
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail
                            )
                        }
                    }
                })
            }
        },
        detailPane = {
            AnimatedPane {
                CoinDetailScreen(state = state)
            }
        },
        modifier = modifier
    )
}









