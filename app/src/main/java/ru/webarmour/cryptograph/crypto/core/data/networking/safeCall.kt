package ru.webarmour.cryptograph.crypto.core.data.networking

import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import ru.webarmour.cryptograph.crypto.core.domain.util.NetworkError
import ru.webarmour.cryptograph.crypto.core.domain.util.Result
import kotlin.coroutines.coroutineContext


suspend inline fun <reified T> saveCall(
    execute:() -> HttpResponse
): Result<T,NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException){
        return Result.Error(NetworkError.NO_INTERNET)
    } catch (e:SerializationException) {
        return Result.Error(NetworkError.SERIALIZATION_ERROR)
    } catch (e:Exception) {
        coroutineContext.ensureActive()
        return Result.Error(NetworkError.UNKNOWN)
    }
    return responseToResult(response)
}