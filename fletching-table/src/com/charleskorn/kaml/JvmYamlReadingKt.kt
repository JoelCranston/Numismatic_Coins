package com.charleskorn.kaml

import java.io.InputStream
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.MagicApiIntrinsics
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.modules.SerializersModule
import okio.Okio

public fun <T> Yaml.decodeFromStream(deserializer: DeserializationStrategy<T>, source: InputStream): T {
   return (T)`$this$decodeFromStream`.decodeFromSource(deserializer, Okio.source(source));
}

@JvmSynthetic
public inline fun <reified T> Yaml.decodeFromStream(stream: InputStream): T {
   val var3: SerializersModule = `$this$decodeFromStream`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (T)`$this$decodeFromStream`.decodeFromSource(SerializersKt.serializer(var3, null), Okio.source(stream));
}

public fun Yaml.parseToYamlNode(source: InputStream): YamlNode {
   return `$this$parseToYamlNode`.parseToYamlNode$kaml(Okio.source(source));
}
