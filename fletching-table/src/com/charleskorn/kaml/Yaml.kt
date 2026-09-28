package com.charleskorn.kaml

import com.charleskorn.kaml.internal.OkioUtilsKt
import kotlin.jdk7.AutoCloseableKt
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.StringFormat
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.SerializersModuleBuildersKt
import okio.Buffer
import okio.BufferedSink
import okio.Okio
import okio.Sink
import okio.Source

public class Yaml(serializersModule: SerializersModule = SerializersModuleBuildersKt.EmptySerializersModule(),
      configuration: YamlConfiguration = new YamlConfiguration(
            false, false, null, null, null, 0, 0, null, null, null, null, 0, null, null, null, false, 65535, null
         )
   ) :
   StringFormat {
   public open val serializersModule: SerializersModule
   public final val configuration: YamlConfiguration

   init {
      this.serializersModule = serializersModule;
      this.configuration = configuration;
   }

   public fun <T> decodeFromYamlNode(deserializer: DeserializationStrategy<T>, node: YamlNode): T {
      return (T)YamlInput.Companion
         .createFor$kaml(node, this, this.getSerializersModule(), this.configuration, deserializer.getDescriptor())
         .decodeSerializableValue(deserializer);
   }

   public override fun <T> decodeFromString(deserializer: DeserializationStrategy<T>, string: String): T {
      return (T)this.decodeFromSource(deserializer, OkioUtilsKt.bufferedSource(string));
   }

   public fun <T> decodeFromSource(deserializer: DeserializationStrategy<T>, source: Source): T {
      return (T)YamlInput.Companion
         .createFor$kaml(this.parseToYamlNode$kaml(source), this, this.getSerializersModule(), this.configuration, deserializer.getDescriptor())
         .decodeSerializableValue(deserializer);
   }

   public fun parseToYamlNode(string: String): YamlNode {
      return this.parseToYamlNode$kaml(OkioUtilsKt.bufferedSource(string));
   }

   internal fun parseToYamlNode(source: Source): YamlNode {
      val parser: YamlParser = new YamlParser(source, this.configuration.getCodePointLimit$kaml());
      val node: YamlNode = new YamlNodeReader(
            parser,
            this.configuration.getExtensionDefinitionPrefix$kaml(),
            this.configuration.getAnchorsAndAliases$kaml().getMaxAliasCount-0hXNFcg$kaml(),
            null
         )
         .read();
      parser.ensureEndOfStreamReached();
      return node;
   }

   public fun <T> encodeToSink(serializer: SerializationStrategy<T>, value: T, sink: Sink) {
      this.encodeToBufferedSink(serializer, value, Okio.buffer(sink));
   }

   public override fun <T> encodeToString(serializer: SerializationStrategy<T>, value: T): String {
      val buffer: Buffer = new Buffer();
      this.encodeToBufferedSink(serializer, value, buffer);
      return StringsKt.trimEnd(buffer.readUtf8()).toString();
   }

   @PublishedApi
   internal fun <T> encodeToBufferedSink(serializer: SerializationStrategy<T>, value: T, sink: BufferedSink) {
      label32: {
         val var4: AutoCloseable = new BufferedSinkDataWriter(sink);
         var var5: java.lang.Throwable = null;

         try {
            try {
               val var8: AutoCloseable = new YamlOutput(var4 as BufferedSinkDataWriter, this.getSerializersModule(), this.configuration);
               var var9: java.lang.Throwable = null;

               try {
                  try {
                     (var8 as YamlOutput).encodeSerializableValue(serializer, value);
                  } catch (var12: java.lang.Throwable) {
                     var9 = var12;
                     throw var12;
                  }
               } catch (var13: java.lang.Throwable) {
                  AutoCloseableKt.closeFinally(var8, var9);
               }

               AutoCloseableKt.closeFinally(var8, null);
            } catch (var14: java.lang.Throwable) {
               var5 = var14;
               throw var14;
            }
         } catch (var15: java.lang.Throwable) {
            AutoCloseableKt.closeFinally(var4, var5);
         }

         AutoCloseableKt.closeFinally(var4, null);
      }
   }

   fun Yaml() {
      this(null, null, 3, null);
   }

   public companion object {
      public final val default: Yaml
   }
}
