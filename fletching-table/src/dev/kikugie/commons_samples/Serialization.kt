package dev.kikugie.commons_samples

import dev.kikugie.commons.serialization.IntRangeSerializer
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.test.AssertionsKt
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonKt
import kotlinx.serialization.modules.SerializersModuleBuilder
import org.junit.jupiter.api.Test

@SourceDebugExtension(["SMAP\nSerialization.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Serialization.kt\ndev/kikugie/commons_samples/Serialization\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n+ 3 SerializersModuleBuilders.kt\nkotlinx/serialization/modules/SerializersModuleBuildersKt\n*L\n1#1,23:1\n205#2:24\n222#2:25\n222#2:26\n31#3,3:27\n*S KotlinDebug\n*F\n+ 1 Serialization.kt\ndev/kikugie/commons_samples/Serialization\n*L\n19#1:24\n20#1:25\n21#1:26\n11#1:27,3\n*E\n"])
private class Serialization {
   public final val Json: Json = JsonKt.Json$default(null, Serialization::Json$lambda$1, 1, null)

   @Test
   public fun rangeSerialization() {
      AssertionsKt.assertEquals$default(
         "\"1..3\"",
         this.Json
            .encodeToString(
               SerializersKt.noCompiledSerializer(this.Json.getSerializersModule(), IntRange::class) as SerializationStrategy<? super IntRange>,
               new IntRange(1, 3)
            ),
         null,
         4,
         null
      );
      AssertionsKt.assertEquals$default(
         new IntRange(1, 3),
         this.Json.decodeFromString(SerializersKt.noCompiledSerializer(this.Json.getSerializersModule(), IntRange::class), "\"1..3\""),
         null,
         4,
         null
      );
      AssertionsKt.assertEquals$default(
         new IntRange(1, 2),
         this.Json.decodeFromString(SerializersKt.noCompiledSerializer(this.Json.getSerializersModule(), IntRange::class), "\"1..<3\""),
         null,
         4,
         null
      );
   }

   @JvmStatic
   fun JsonBuilder.`Json$lambda$1`(): Unit {
      val `builder$iv`: SerializersModuleBuilder = new SerializersModuleBuilder();
      `builder$iv`.contextual(IntRange::class, IntRangeSerializer.INSTANCE);
      `builder$iv`.include(`$this$Json`.getSerializersModule());
      `$this$Json`.setSerializersModule(`builder$iv`.build());
      return Unit.INSTANCE;
   }
}
