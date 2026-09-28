package io.ktor.http

import io.ktor.util.CharsetKt
import io.ktor.util.TextKt
import java.io.Serializable
import java.util.LinkedHashMap
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nURLProtocol.kt\nKotlin\n*S Kotlin\n*F\n+ 1 URLProtocol.kt\nio/ktor/http/URLProtocol\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,92:1\n1069#2,2:93\n1#3:95\n1208#4,2:96\n1236#4,4:98\n*S KotlinDebug\n*F\n+ 1 URLProtocol.kt\nio/ktor/http/URLProtocol\n*L\n21#1:93,2\n66#1:96,2\n66#1:98,4\n*E\n"])
public data class URLProtocol(name: String, defaultPort: Int) : Serializable {
   public final val name: String
   public final val defaultPort: Int

   init {
      this.name = name;
      this.defaultPort = defaultPort;
      val `$this$all$iv`: java.lang.CharSequence = this.name;
      var var5: Int = 0;

      var var10000: Boolean;
      while (true) {
         if (var5 >= `$this$all$iv`.length()) {
            var10000 = true;
            break;
         }

         if (!CharsetKt.isLowerCase(`$this$all$iv`.charAt(var5))) {
            var10000 = false;
            break;
         }

         var5++;
      }

      if (!var10000) {
         throw new IllegalArgumentException("All characters should be lower case".toString());
      }
   }

   public operator fun component1(): String {
      return this.name;
   }

   public operator fun component2(): Int {
      return this.defaultPort;
   }

   public fun copy(name: String = this.name, defaultPort: Int = this.defaultPort): URLProtocol {
      return new URLProtocol(name, defaultPort);
   }

   public override fun toString(): String {
      return "URLProtocol(name=${this.name}, defaultPort=${this.defaultPort})";
   }

   public override fun hashCode(): Int {
      return this.name.hashCode() * 31 + Integer.hashCode(this.defaultPort);
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is URLProtocol) {
         return false;
      } else {
         val var2: URLProtocol = other as URLProtocol;
         if (!(this.name == (other as URLProtocol).name)) {
            return false;
         } else {
            return this.defaultPort == var2.defaultPort;
         }
      }
   }

   @JvmStatic
   fun {
      val var11: java.lang.Iterable = CollectionsKt.listOf(new URLProtocol[]{HTTP, HTTPS, WS, WSS, SOCKS});
      val `destination$iv$iv`: java.util.Map = new LinkedHashMap(
         kotlin.ranges.RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(var11, 10)), 16)
      );

      for (Object element$iv$iv : $this$associateBy$iv) {
         `destination$iv$iv`.put((`element$iv$iv` as URLProtocol).name, `element$iv$iv`);
      }

      byName = `destination$iv$iv`;
   }

   public companion object {
      public final val HTTP: URLProtocol
      public final val HTTPS: URLProtocol
      public final val WS: URLProtocol
      public final val WSS: URLProtocol
      public final val SOCKS: URLProtocol
      public final val byName: Map<String, URLProtocol>

      public fun createOrDefault(name: String): URLProtocol {
         val it: java.lang.String = TextKt.toLowerCasePreservingASCIIRules(name);
         var var10000: URLProtocol = URLProtocol.Companion.getByName().get(it);
         if (var10000 == null) {
            var10000 = new URLProtocol(it, 0);
         }

         return var10000;
      }
   }
}
