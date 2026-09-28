@file:SourceDebugExtension(["SMAP\nUrlDecodedParametersBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UrlDecodedParametersBuilder.kt\nio/ktor/http/UrlDecodedParametersBuilderKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,88:1\n1#2:89\n1869#3:90\n1563#3:91\n1634#3,3:92\n1870#3:95\n1869#3:96\n1563#3:97\n1634#3,3:98\n1870#3:101\n*S KotlinDebug\n*F\n+ 1 UrlDecodedParametersBuilder.kt\nio/ktor/http/UrlDecodedParametersBuilderKt\n*L\n72#1:90\n76#1:91\n76#1:92,3\n72#1:95\n83#1:96\n85#1:97\n85#1:98,3\n83#1:101\n*E\n"])

package io.ktor.http

import io.ktor.util.StringValues
import io.ktor.util.StringValuesBuilder
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

internal fun decodeParameters(parameters: StringValuesBuilder): Parameters {
   val var1: ParametersBuilder = ParametersKt.ParametersBuilder$default(0, 1, null);
   appendAllDecoded(var1, parameters);
   return var1.build();
}

internal fun encodeParameters(parameters: StringValues): ParametersBuilder {
   val var1: ParametersBuilder = ParametersKt.ParametersBuilder$default(0, 1, null);
   appendAllEncoded(var1, parameters);
   return var1;
}

private fun StringValuesBuilder.appendAllDecoded(parameters: StringValuesBuilder) {
   val `$this$forEach$iv`: java.lang.Iterable;
   for (Object element$iv : $this$forEach$iv) {
      val key: java.lang.String = `element$iv` as java.lang.String;
      var var10000: java.util.List = parameters.getAll(`element$iv` as java.lang.String);
      if (var10000 == null) {
         var10000 = CollectionsKt.emptyList();
      }

      val var10001: java.lang.String = CodecsKt.decodeURLQueryComponent$default(key, 0, 0, false, null, 15, null);
      val `$this$map$iv`: java.lang.Iterable = var10000;
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var10000, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add(CodecsKt.decodeURLQueryComponent$default(`item$iv$iv` as java.lang.String, 0, 0, true, null, 11, null));
      }

      `$this$appendAllDecoded`.appendAll(var10001, `destination$iv$iv`);
   }
}

private fun StringValuesBuilder.appendAllEncoded(parameters: StringValues) {
   val `$this$forEach$iv`: java.lang.Iterable;
   for (Object element$iv : $this$forEach$iv) {
      val key: java.lang.String = `element$iv` as java.lang.String;
      var var10000: java.util.List = parameters.getAll(`element$iv` as java.lang.String);
      if (var10000 == null) {
         var10000 = CollectionsKt.emptyList();
      }

      val var10001: java.lang.String = CodecsKt.encodeURLParameter$default(key, false, 1, null);
      val `$this$map$iv`: java.lang.Iterable = var10000;
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var10000, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add(CodecsKt.encodeURLParameterValue(`item$iv$iv` as java.lang.String));
      }

      `$this$appendAllEncoded`.appendAll(var10001, `destination$iv$iv`);
   }
}

@JvmSynthetic
fun `access$appendAllEncoded`(`$receiver`: StringValuesBuilder, parameters: StringValues) {
   appendAllEncoded(`$receiver`, parameters);
}
