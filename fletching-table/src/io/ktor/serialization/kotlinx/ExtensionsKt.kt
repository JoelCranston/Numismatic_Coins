@file:SourceDebugExtension(["SMAP\nExtensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Extensions.kt\nio/ktor/serialization/kotlinx/ExtensionsKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,44:1\n1617#2,9:45\n1869#2:54\n1870#2:56\n1626#2:57\n1#3:55\n*S KotlinDebug\n*F\n+ 1 Extensions.kt\nio/ktor/serialization/kotlinx/ExtensionsKt\n*L\n17#1:45,9\n17#1:54\n17#1:56\n17#1:57\n17#1:55\n*E\n"])

package io.ktor.serialization.kotlinx

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.SerialFormat

internal fun extensions(format: SerialFormat): List<KotlinxSerializationExtension> {
   val `$this$mapNotNull$iv`: java.lang.Iterable = ExtensionsJvmKt.getProviders();
   val `destination$iv$iv`: java.util.Collection = new ArrayList();

   for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
      val var10000: KotlinxSerializationExtension = (`element$iv$iv$iv` as KotlinxSerializationExtensionProvider).extension(format);
      if (var10000 != null) {
         `destination$iv$iv`.add(var10000);
      }
   }

   return `destination$iv$iv` as MutableList<KotlinxSerializationExtension>;
}
