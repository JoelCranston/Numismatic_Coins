package io.ktor.http.content

import io.ktor.http.DateUtilsKt
import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import io.ktor.util.date.DateKt
import io.ktor.util.date.GMTDate
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nVersions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Versions.kt\nio/ktor/http/content/LastModifiedVersion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,318:1\n1761#2,3:319\n1740#2,3:322\n774#2:325\n865#2,2:326\n1617#2,9:328\n1869#2:337\n1870#2:339\n1626#2:340\n1#3:338\n1#3:341\n*S KotlinDebug\n*F\n+ 1 Versions.kt\nio/ktor/http/content/LastModifiedVersion\n*L\n123#1:319,3\n132#1:322,3\n140#1:325\n140#1:326,2\n141#1:328,9\n141#1:337\n141#1:339\n141#1:340\n141#1:338\n*E\n"])
public data class LastModifiedVersion(lastModified: GMTDate) : Version {
   public final val lastModified: GMTDate
   private final val truncatedModificationDate: GMTDate

   init {
      this.lastModified = lastModified;
      this.truncatedModificationDate = DateKt.truncateToSeconds(this.lastModified);
   }

   public override fun check(requestHeaders: Headers): VersionCheckResult {
      var var10000: java.util.List = requestHeaders.getAll(HttpHeaders.INSTANCE.getIfModifiedSince());
      val modifiedSince: java.util.List = if (var10000 != null) this.parseDates(var10000) else null;
      if (modifiedSince != null && !this.ifModifiedSince(modifiedSince)) {
         return VersionCheckResult.NOT_MODIFIED;
      } else {
         var10000 = requestHeaders.getAll(HttpHeaders.INSTANCE.getIfUnmodifiedSince());
         val unmodifiedSince: java.util.List = if (var10000 != null) this.parseDates(var10000) else null;
         return if (unmodifiedSince != null && !this.ifUnmodifiedSince(unmodifiedSince)) VersionCheckResult.PRECONDITION_FAILED else VersionCheckResult.OK;
      }
   }

   public fun ifModifiedSince(dates: List<GMTDate>): Boolean {
      val `$this$any$iv`: java.lang.Iterable = dates;
      val var10000: Boolean;
      if (dates is java.util.Collection && (dates as java.util.Collection).isEmpty()) {
         var10000 = false;
      } else {
         for (Object element$iv : $this$any$iv) {
            if (this.truncatedModificationDate.compareTo(`element$iv` as GMTDate) > 0) {
               return true;
            }
         }

         var10000 = false;
      }

      return var10000;
   }

   public fun ifUnmodifiedSince(dates: List<GMTDate>): Boolean {
      val `$this$all$iv`: java.lang.Iterable = dates;
      val var10000: Boolean;
      if (dates is java.util.Collection && (dates as java.util.Collection).isEmpty()) {
         var10000 = true;
      } else {
         for (Object element$iv : $this$all$iv) {
            if (this.truncatedModificationDate.compareTo(`element$iv` as GMTDate) > 0) {
               return false;
            }
         }

         var10000 = true;
      }

      return var10000;
   }

   public override fun appendHeadersTo(builder: HeadersBuilder) {
      builder.set(HttpHeaders.INSTANCE.getLastModified(), DateUtilsKt.toHttpDate(this.lastModified));
   }

   private fun List<String>.parseDates(): List<GMTDate>? {
      var `$this$mapNotNull$iv`: java.lang.Iterable = `$this$parseDates`;
      var `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$filter$iv) {
         if (!StringsKt.isBlank(`$i$f$forEach` as java.lang.String)) {
            `destination$iv$iv`.add(`$i$f$forEach`);
         }
      }

      `$this$mapNotNull$iv` = `destination$iv$iv` as java.util.List;
      `destination$iv$iv` = new ArrayList();

      for (Object element$iv$iv$iv : $this$filter$iv) {
         val it: java.lang.String = var27 as java.lang.String;

         var var15: GMTDate;
         try {
            var15 = DateUtilsKt.fromHttpToGmtDate(it);
         } catch (var19: java.lang.Throwable) {
            var15 = null;
         }

         if (var15 != null) {
            `destination$iv$iv`.add(var15);
         }
      }

      return if (!(`destination$iv$iv` as java.util.List).isEmpty()) `destination$iv$iv` as java.util.List else null;
   }

   public operator fun component1(): GMTDate {
      return this.lastModified;
   }

   public fun copy(lastModified: GMTDate = this.lastModified): LastModifiedVersion {
      return new LastModifiedVersion(lastModified);
   }

   public override fun toString(): String {
      return "LastModifiedVersion(lastModified=${this.lastModified})";
   }

   public override fun hashCode(): Int {
      return this.lastModified.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is LastModifiedVersion) {
         return false;
      } else {
         return this.lastModified == (other as LastModifiedVersion).lastModified;
      }
   }
}
