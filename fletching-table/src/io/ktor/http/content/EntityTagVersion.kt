package io.ktor.http.content

import io.ktor.http.ApplicationResponsePropertiesKt
import io.ktor.http.HeaderValue
import io.ktor.http.HeaderValueWithParametersKt
import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaderValueParserKt
import io.ktor.http.HttpHeaders
import java.util.ArrayList
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nVersions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Versions.kt\nio/ktor/http/content/EntityTagVersion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,318:1\n1#2:319\n1761#3,3:320\n*S KotlinDebug\n*F\n+ 1 Versions.kt\nio/ktor/http/content/EntityTagVersion\n*L\n238#1:320,3\n*E\n"])
public data class EntityTagVersion(etag: String, weak: Boolean) : Version {
   public final val etag: String
   public final val weak: Boolean
   private final val opaque: String
   private final val normalized: String

   init {
      this.etag = etag;
      this.weak = weak;
      this.opaque = if (this.etag == "*")
         this.etag
         else
         (if (StringsKt.startsWith$default(this.etag, "\"", false, 2, null)) this.etag else HeaderValueWithParametersKt.quote(this.etag));
      this.normalized = if (this.weak) "W/${this.opaque}" else this.opaque;
      if (this.weak && this.etag == STAR.etag) {
         throw new IllegalArgumentException("Entity tag '*' could not be weak.".toString());
      } else {
         var index: Int = 0;

         for (int var4 = this.etag.length(); index < var4; index++) {
            val ch: Char = this.etag.charAt(index);
            if ((Intrinsics.compare(ch, 32) <= 0 || ch == '"') && index != 0 && index != StringsKt.getLastIndex(this.etag)) {
               throw new IllegalArgumentException(("Character '$ch' is not allowed in entity-tag.").toString());
            }
         }
      }
   }

   public override fun check(requestHeaders: Headers): VersionCheckResult {
      var var10000: java.lang.String = requestHeaders.get(HttpHeaders.INSTANCE.getIfNoneMatch());
      if (var10000 != null) {
         val var14: java.util.List = Companion.parse(var10000);
         if (var14 != null) {
            val result: VersionCheckResult = this.noneMatch(var14);
            if (result != VersionCheckResult.OK) {
               return result;
            }
         }
      }

      var10000 = requestHeaders.get(HttpHeaders.INSTANCE.getIfMatch());
      if (var10000 != null) {
         val var16: java.util.List = Companion.parse(var10000);
         if (var16 != null) {
            val var12: VersionCheckResult = this.match(var16);
            if (var12 != VersionCheckResult.OK) {
               return var12;
            }
         }
      }

      return VersionCheckResult.OK;
   }

   public fun match(other: EntityTagVersion): Boolean {
      return !this.weak && !other.weak && this.weakMatch(other);
   }

   private fun weakMatch(other: EntityTagVersion): Boolean {
      return this == STAR || other == STAR || this.opaque == other.opaque;
   }

   public fun noneMatch(givenNoneMatchEtags: List<EntityTagVersion>): VersionCheckResult {
      if (givenNoneMatchEtags.contains(STAR)) {
         return VersionCheckResult.OK;
      } else {
         val `$this$any$iv`: java.lang.Iterable = givenNoneMatchEtags;
         var var10000: Boolean;
         if (givenNoneMatchEtags is java.util.Collection && (givenNoneMatchEtags as java.util.Collection).isEmpty()) {
            var10000 = false;
         } else {
            val var4: java.util.Iterator = `$this$any$iv`.iterator();

            while (true) {
               if (!var4.hasNext()) {
                  var10000 = false;
                  break;
               }

               if (this.weakMatch(var4.next() as EntityTagVersion)) {
                  var10000 = true;
                  break;
               }
            }
         }

         return if (var10000) VersionCheckResult.NOT_MODIFIED else VersionCheckResult.OK;
      }
   }

   public fun match(givenMatchEtags: List<EntityTagVersion>): VersionCheckResult {
      if (givenMatchEtags.isEmpty()) {
         return VersionCheckResult.OK;
      } else if (givenMatchEtags.contains(STAR)) {
         return VersionCheckResult.OK;
      } else {
         for (EntityTagVersion given : givenMatchEtags) {
            if (this.match(given)) {
               return VersionCheckResult.OK;
            }
         }

         return VersionCheckResult.PRECONDITION_FAILED;
      }
   }

   public override fun appendHeadersTo(builder: HeadersBuilder) {
      ApplicationResponsePropertiesKt.etag(builder, this.normalized);
   }

   public operator fun component1(): String {
      return this.etag;
   }

   public operator fun component2(): Boolean {
      return this.weak;
   }

   public fun copy(etag: String = this.etag, weak: Boolean = this.weak): EntityTagVersion {
      return new EntityTagVersion(etag, weak);
   }

   public override fun toString(): String {
      return "EntityTagVersion(etag=${this.etag}, weak=${this.weak})";
   }

   public override fun hashCode(): Int {
      return this.etag.hashCode() * 31 + java.lang.Boolean.hashCode(this.weak);
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is EntityTagVersion) {
         return false;
      } else {
         val var2: EntityTagVersion = other as EntityTagVersion;
         if (!(this.etag == (other as EntityTagVersion).etag)) {
            return false;
         } else {
            return this.weak == var2.weak;
         }
      }
   }

   @SourceDebugExtension(["SMAP\nVersions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Versions.kt\nio/ktor/http/content/EntityTagVersion$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,318:1\n1563#2:319\n1634#2,2:320\n1636#2:323\n1#3:322\n*S KotlinDebug\n*F\n+ 1 Versions.kt\nio/ktor/http/content/EntityTagVersion$Companion\n*L\n282#1:319\n282#1:320,2\n282#1:323\n*E\n"])
   public companion object {
      public final val STAR: EntityTagVersion

      public fun parse(headerValue: String): List<EntityTagVersion> {
         val `$this$map$iv`: java.lang.Iterable = HttpHeaderValueParserKt.parseHeaderValue(headerValue);
         val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$map$iv`, 10));

         for (Object item$iv$iv : $this$map$iv) {
            val entry: HeaderValue = `item$iv$iv` as HeaderValue;
            if ((`item$iv$iv` as HeaderValue).getQuality() != 1.0) {
               throw new IllegalStateException(("entity-tag quality parameter is not allowed: ${entry.getQuality()}.").toString());
            }

            if (!entry.getParams().isEmpty()) {
               throw new IllegalStateException(("entity-tag parameters are not allowed: ${entry.getParams()}.").toString());
            }

            `destination$iv$iv`.add(EntityTagVersion.Companion.parseSingle(entry.getValue()));
         }

         return `destination$iv$iv` as MutableList<EntityTagVersion>;
      }

      public fun parseSingle(value: String): EntityTagVersion {
         if (value == "*") {
            return this.getSTAR();
         } else {
            val var5: Boolean;
            val var6: java.lang.String;
            if (StringsKt.startsWith$default(value, "W/", false, 2, null)) {
               var5 = true;
               var6 = StringsKt.drop(value, 2);
            } else {
               var5 = false;
               var6 = value;
            }

            return new EntityTagVersion(if (StringsKt.startsWith$default(var6, "\"", false, 2, null)) var6 else HeaderValueWithParametersKt.quote(var6), var5);
         }
      }
   }
}
