@file:SourceDebugExtension(["SMAP\nCachingOptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CachingOptions.kt\nio/ktor/http/content/CachingOptionsKt\n+ 2 Attributes.kt\nio/ktor/util/AttributesKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,36:1\n21#2:37\n69#3:38\n84#3,8:39\n*S KotlinDebug\n*F\n+ 1 CachingOptions.kt\nio/ktor/http/content/CachingOptionsKt\n*L\n26#1:37\n26#1:38\n26#1:39,8\n*E\n"])

package io.ktor.http.content

import io.ktor.util.AttributeKey
import kotlin.jvm.internal.SourceDebugExtension

public final val CachingProperty: AttributeKey<CachingOptions>

public final var caching: CachingOptions?
   public final get() {
      return `$this$caching`.getProperty(CachingProperty);
   }

   public final set(value) {
      `$this$caching`.setProperty(CachingProperty, value);
   }

