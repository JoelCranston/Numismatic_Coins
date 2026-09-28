package io.ktor.serialization.kotlinx.json

import io.ktor.serialization.kotlinx.json.JsonExtensionsJvmKt.deserializeSequence.2
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json

internal suspend fun deserializeSequence(format: Json, content: ByteReadChannel, typeInfo: TypeInfo): Sequence<Any?>? {
   return BuildersKt.withContext(Dispatchers.getIO(), new 2(content, typeInfo, format, null), `$completion`);
}
