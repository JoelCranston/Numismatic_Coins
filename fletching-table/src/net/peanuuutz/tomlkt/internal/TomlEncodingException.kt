package net.peanuuutz.tomlkt.internal

import kotlinx.serialization.SerializationException

internal sealed class TomlEncodingException protected constructor(message: String) : SerializationException(message)
