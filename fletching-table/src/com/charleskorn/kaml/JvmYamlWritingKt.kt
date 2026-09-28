package com.charleskorn.kaml

import java.io.OutputStream
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.MagicApiIntrinsics
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.modules.SerializersModule
import okio.Okio

public fun <T> Yaml.encodeToStream(serializer: SerializationStrategy<T>, value: T, stream: OutputStream) {
   `$this$encodeToStream`.encodeToSink(serializer, value, Okio.sink(stream));
}

@JvmSynthetic
public inline fun <reified T> Yaml.encodeToStream(value: T, stream: OutputStream) {
   val var4: SerializersModule = `$this$encodeToStream`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   encodeToStream(`$this$encodeToStream`, SerializersKt.serializer(var4, null), value, stream);
}
