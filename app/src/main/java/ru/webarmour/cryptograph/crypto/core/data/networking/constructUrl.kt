package ru.webarmour.cryptograph.crypto.core.data.networking

import ru.webarmour.cryptograph.BuildConfig


fun constructUrl(url:String): String{
    return when{
        url.contains(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1)
        else -> BuildConfig.BASE_URL + url
    }
}