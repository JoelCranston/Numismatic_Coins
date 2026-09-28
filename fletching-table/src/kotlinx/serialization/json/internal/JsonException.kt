package kotlinx.serialization.json.internal

import kotlinx.serialization.SerializationException

internal open class JsonException(message: String) : SerializationException(message)
