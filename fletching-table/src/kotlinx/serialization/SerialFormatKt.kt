package kotlinx.serialization

import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.MagicApiIntrinsics
import kotlinx.serialization.internal.InternalHexConverter
import kotlinx.serialization.modules.SerializersModule

@JvmSynthetic
public inline fun <reified T> StringFormat.encodeToString(value: T): String {
   val var3: SerializersModule = `$this$encodeToString`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return `$this$encodeToString`.encodeToString(SerializersKt.serializer(var3, null), value);
}

@JvmSynthetic
public inline fun <reified T> StringFormat.decodeFromString(string: String): T {
   val var3: SerializersModule = `$this$decodeFromString`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (T)`$this$decodeFromString`.decodeFromString(SerializersKt.serializer(var3, null), string);
}

public fun <T> BinaryFormat.encodeToHexString(serializer: SerializationStrategy<T>, value: T): String {
   return InternalHexConverter.INSTANCE.printHexBinary(`$this$encodeToHexString`.encodeToByteArray(serializer, value), true);
}

public fun <T> BinaryFormat.decodeFromHexString(deserializer: DeserializationStrategy<T>, hex: String): T {
   return (T)`$this$decodeFromHexString`.decodeFromByteArray(deserializer, InternalHexConverter.INSTANCE.parseHexBinary(hex));
}

@JvmSynthetic
public inline fun <reified T> BinaryFormat.encodeToHexString(value: T): String {
   val var3: SerializersModule = `$this$encodeToHexString`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return encodeToHexString(`$this$encodeToHexString`, SerializersKt.serializer(var3, null), value);
}

@JvmSynthetic
public inline fun <reified T> BinaryFormat.decodeFromHexString(hex: String): T {
   val var3: SerializersModule = `$this$decodeFromHexString`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (T)decodeFromHexString(`$this$decodeFromHexString`, SerializersKt.serializer(var3, null), hex);
}

@JvmSynthetic
public inline fun <reified T> BinaryFormat.encodeToByteArray(value: T): ByteArray {
   val var3: SerializersModule = `$this$encodeToByteArray`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return `$this$encodeToByteArray`.encodeToByteArray(SerializersKt.serializer(var3, null), value);
}

@JvmSynthetic
public inline fun <reified T> BinaryFormat.decodeFromByteArray(bytes: ByteArray): T {
   val var3: SerializersModule = `$this$decodeFromByteArray`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (T)`$this$decodeFromByteArray`.decodeFromByteArray(SerializersKt.serializer(var3, null), bytes);
}
