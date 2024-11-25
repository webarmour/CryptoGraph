package ru.webarmour.cryptograph

import android.app.Application
import io.ktor.http.ContentType
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.webarmour.cryptograph.crypto.di.appModule

class CryptoGraphApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@CryptoGraphApp)
            androidLogger()

            modules(appModule)
        }
    }
}