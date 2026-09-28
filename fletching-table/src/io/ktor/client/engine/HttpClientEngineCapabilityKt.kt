@file:SourceDebugExtension(["SMAP\nHttpClientEngineCapability.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpClientEngineCapability.kt\nio/ktor/client/engine/HttpClientEngineCapabilityKt\n+ 2 Attributes.kt\nio/ktor/util/AttributesKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,78:1\n21#2:79\n69#3:80\n84#3,8:81\n*S KotlinDebug\n*F\n+ 1 HttpClientEngineCapability.kt\nio/ktor/client/engine/HttpClientEngineCapabilityKt\n*L\n14#1:79\n14#1:80\n14#1:81,8\n*E\n"])

package io.ktor.client.engine

import io.ktor.util.AttributeKey
import kotlin.jvm.internal.SourceDebugExtension

internal final val ENGINE_CAPABILITIES_KEY: AttributeKey<MutableMap<HttpClientEngineCapability<*>, Any>>
public final val DEFAULT_CAPABILITIES: Set<HttpClientEngineCapability<*>>
