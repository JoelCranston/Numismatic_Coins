package net.peanuuutz.tomlkt

import java.util.Arrays
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.MagicApiIntrinsics
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.modules.SerializersModule

public inline fun Toml(from: Toml = Toml.Default as Toml, config: (TomlConfigBuilder) -> Unit): Toml {
   val var3: TomlConfigBuilder = new TomlConfigBuilder(from.getConfig());
   config.invoke(var3);
   return new TomlImpl(var3.build());
}

@JvmSynthetic
fun `Toml$default`(from: Toml, config: Function1, `$i$f$Toml`: Int, var3: Any): Toml {
   if ((`$i$f$Toml` and 1) != 0) {
      from = Toml.Default;
   }

   var3 = new TomlConfigBuilder(from.getConfig());
   config.invoke(var3);
   return new TomlImpl(var3.build());
}

@JvmSynthetic
public inline fun <reified T> Toml.encodeToWriter(value: T, writer: TomlWriter) {
   val var4: SerializersModule = `$this$encodeToWriter`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   `$this$encodeToWriter`.encodeToWriter(SerializersKt.serializer(var4, null), value, writer);
}

@JvmSynthetic
public inline fun <reified T> Toml.encodeToTomlElement(value: T): TomlElement {
   val var3: SerializersModule = `$this$encodeToTomlElement`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return `$this$encodeToTomlElement`.encodeToTomlElement(SerializersKt.serializer(var3, null), value);
}

@JvmSynthetic
public inline fun <reified T> Toml.decodeFromString(string: String, vararg keys: Any?): T {
   val var4: SerializersModule = `$this$decodeFromString`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (T)`$this$decodeFromString`.decodeFromString(SerializersKt.serializer(var4, null), string, Arrays.copyOf(keys, keys.length));
}

@JvmSynthetic
public inline fun <reified T> Toml.decodeFromReader(reader: TomlReader): T {
   val var3: SerializersModule = `$this$decodeFromReader`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (T)`$this$decodeFromReader`.decodeFromReader(SerializersKt.serializer(var3, null), reader);
}

@JvmSynthetic
public inline fun <reified T> Toml.decodeFromReader(reader: TomlReader, vararg keys: Any?): T {
   val var4: SerializersModule = `$this$decodeFromReader`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (T)`$this$decodeFromReader`.decodeFromReader(SerializersKt.serializer(var4, null), reader, Arrays.copyOf(keys, keys.length));
}

@JvmSynthetic
public inline fun <reified T> Toml.decodeFromTomlElement(element: TomlElement): T {
   val var3: SerializersModule = `$this$decodeFromTomlElement`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (T)`$this$decodeFromTomlElement`.decodeFromTomlElement(SerializersKt.serializer(var3, null), element);
}
