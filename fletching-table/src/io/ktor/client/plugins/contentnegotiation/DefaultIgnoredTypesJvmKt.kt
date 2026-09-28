package io.ktor.client.plugins.contentnegotiation

import java.io.InputStream
import kotlin.reflect.KClass

internal final val DefaultIgnoredTypes: Set<KClass<*>> = SetsKt.mutableSetOf(new KClass[]{InputStream::class})
