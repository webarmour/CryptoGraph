package ru.webarmour.cryptograph.crypto.di

import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.webarmour.cryptograph.crypto.core.data.networking.HttpClientFactory
import ru.webarmour.cryptograph.crypto.data.networking.CoinDataSourceImpl
import ru.webarmour.cryptograph.crypto.domain.CoinDataSource
import ru.webarmour.cryptograph.crypto.presentation.coin_list.CoinListViewModel


val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::CoinDataSourceImpl).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)

}