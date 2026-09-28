package net.peanuuutz.tomlkt

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.Closeable
import java.io.Reader
import java.io.Writer
import java.util.Arrays
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.MagicApiIntrinsics
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.modules.SerializersModule

public fun <T> Toml.encodeToNativeWriter(serializer: SerializationStrategy<T>, value: T, nativeWriter: Writer) {
   label24: {
      val var4: Closeable = if (nativeWriter is BufferedWriter) nativeWriter as BufferedWriter else new BufferedWriter(nativeWriter, 8192);
      var var13: java.lang.Throwable = null;

      try {
         try {
            `$this$encodeToNativeWriter`.encodeToWriter(serializer, value, new TomlNativeWriter(var4 as BufferedWriter));
         } catch (var9: java.lang.Throwable) {
            var13 = var9;
            throw var9;
         }
      } catch (var10: java.lang.Throwable) {
         CloseableKt.closeFinally(var4, var13);
      }

      CloseableKt.closeFinally(var4, null);
   }
}

@JvmSynthetic
public inline fun <reified T> Toml.encodeToNativeWriter(value: T, nativeWriter: Writer) {
   val var4: SerializersModule = `$this$encodeToNativeWriter`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   encodeToNativeWriter(`$this$encodeToNativeWriter`, SerializersKt.serializer(var4, null), value, nativeWriter);
}

public fun <T> Toml.decodeFromNativeReader(deserializer: DeserializationStrategy<T>, nativeReader: Reader): T {
   label24: {
      val var3: Closeable = if (nativeReader is BufferedReader) nativeReader as BufferedReader else new BufferedReader(nativeReader, 8192);
      var var12: java.lang.Throwable = null;

      try {
         try {
            val var13: Any = `$this$decodeFromNativeReader`.decodeFromReader(deserializer, new TomlNativeReader(var3 as BufferedReader));
         } catch (var8: java.lang.Throwable) {
            var12 = var8;
            throw var8;
         }
      } catch (var9: java.lang.Throwable) {
         CloseableKt.closeFinally(var3, var12);
      }

      CloseableKt.closeFinally(var3, null);
   }
}

@JvmSynthetic
public inline fun <reified T> Toml.decodeFromNativeReader(nativeReader: Reader): T {
   val var3: SerializersModule = `$this$decodeFromNativeReader`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (T)decodeFromNativeReader(`$this$decodeFromNativeReader`, SerializersKt.serializer(var3, null), nativeReader);
}

public fun <T> Toml.decodeFromNativeReader(deserializer: DeserializationStrategy<T>, nativeReader: Reader, vararg keys: Any?): T {
   label24: {
      val var4: Closeable = if (nativeReader is BufferedReader) nativeReader as BufferedReader else new BufferedReader(nativeReader, 8192);
      var var13: java.lang.Throwable = null;

      try {
         try {
            val var14: Any = `$this$decodeFromNativeReader`.decodeFromReader(
               deserializer, new TomlNativeReader(var4 as BufferedReader), Arrays.copyOf(keys, keys.length)
            );
         } catch (var9: java.lang.Throwable) {
            var13 = var9;
            throw var9;
         }
      } catch (var10: java.lang.Throwable) {
         CloseableKt.closeFinally(var4, var13);
      }

      CloseableKt.closeFinally(var4, null);
   }
}

@JvmSynthetic
public inline fun <reified T> Toml.decodeFromNativeReader(nativeReader: Reader, vararg keys: Any?): T {
   val var4: SerializersModule = `$this$decodeFromNativeReader`.getSerializersModule();
   Intrinsics.reifiedOperationMarker(6, "T");
   MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
   return (T)decodeFromNativeReader(`$this$decodeFromNativeReader`, SerializersKt.serializer(var4, null), nativeReader, Arrays.copyOf(keys, keys.length));
}

public fun Toml.parseToTomlTable(nativeReader: Reader): TomlTable {
   label24: {
      val var2: Closeable = if (nativeReader is BufferedReader) nativeReader as BufferedReader else new BufferedReader(nativeReader, 8192);
      var var11: java.lang.Throwable = null;

      try {
         try {
            val var12: TomlTable = `$this$parseToTomlTable`.parseToTomlTable(new TomlNativeReader(var2 as BufferedReader));
         } catch (var7: java.lang.Throwable) {
            var11 = var7;
            throw var7;
         }
      } catch (var8: java.lang.Throwable) {
         CloseableKt.closeFinally(var2, var11);
      }

      CloseableKt.closeFinally(var2, null);
   }
}
