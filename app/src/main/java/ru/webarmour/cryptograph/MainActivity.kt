package ru.webarmour.cryptograph

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.koinViewModel
import ru.webarmour.cryptograph.crypto.core.presentation.util.ObserveAsEvents
import ru.webarmour.cryptograph.crypto.core.presentation.util.toStringError
import ru.webarmour.cryptograph.crypto.presentation.coin_list.CoinListEvent
import ru.webarmour.cryptograph.crypto.presentation.coin_list.CoinListScreen
import ru.webarmour.cryptograph.crypto.presentation.coin_list.CoinListViewModel
import ru.webarmour.cryptograph.theme.CryptoTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CryptoTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewModel = koinViewModel<CoinListViewModel>()
                    val state by viewModel.state.collectAsStateWithLifecycle()
                    val context = LocalContext.current
                    ObserveAsEvents(events = viewModel.events) {event ->
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
                    CoinListScreen(
                        state = state,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
