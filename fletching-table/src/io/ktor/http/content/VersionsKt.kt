@file:SourceDebugExtension(["SMAP\nVersions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Versions.kt\nio/ktor/http/content/VersionsKt\n+ 2 Attributes.kt\nio/ktor/util/AttributesKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,318:1\n21#2:319\n69#3:320\n84#3,8:321\n*S KotlinDebug\n*F\n+ 1 Versions.kt\nio/ktor/http/content/VersionsKt\n*L\n16#1:319\n16#1:320\n16#1:321,8\n*E\n"])

package io.ktor.http.content

import io.ktor.util.AttributeKey
import kotlin.jvm.internal.SourceDebugExtension

public final val VersionListProperty: AttributeKey<List<Version>>

public final var versions: List<Version>
   public final get() {
      var var10000: java.util.List = `$this$versions`.getProperty(VersionListProperty);
      if (var10000 == null) {
         var10000 = CollectionsKt.emptyList();
      }

      return var10000;
   }

   public final set(value) {
      `$this$versions`.setProperty(VersionListProperty, value);
   }


public fun EntityTagVersion(spec: String): EntityTagVersion {
   return EntityTagVersion.Companion.parseSingle(spec);
}
