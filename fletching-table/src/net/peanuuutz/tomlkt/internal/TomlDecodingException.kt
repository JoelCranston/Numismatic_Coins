package net.peanuuutz.tomlkt.internal

import kotlinx.serialization.SerializationException

internal sealed class TomlDecodingException protected constructor(message: String) : SerializationException(message)
