package io.ktor.http

import java.util.ArrayList
import kotlin.enums.EnumEntries

public abstract class CacheControl {
   public final val visibility: io.ktor.http.CacheControl.Visibility?

   open fun CacheControl(visibility: CacheControl.Visibility?) {
      this.visibility = visibility;
   }

   public class MaxAge(maxAgeSeconds: Int,
      proxyMaxAgeSeconds: Int? = null,
      mustRevalidate: Boolean = false,
      proxyRevalidate: Boolean = false,
      visibility: io.ktor.http.CacheControl.Visibility? = null
   ) : CacheControl(visibility) {
      public final val maxAgeSeconds: Int
      public final val proxyMaxAgeSeconds: Int?
      public final val mustRevalidate: Boolean
      public final val proxyRevalidate: Boolean

      init {
         this.maxAgeSeconds = maxAgeSeconds;
         this.proxyMaxAgeSeconds = proxyMaxAgeSeconds;
         this.mustRevalidate = mustRevalidate;
         this.proxyRevalidate = proxyRevalidate;
      }

      public override fun toString(): String {
         val parts: ArrayList = new ArrayList(5);
         parts.add("max-age=${this.maxAgeSeconds}");
         if (this.proxyMaxAgeSeconds != null) {
            parts.add("s-maxage=${this.proxyMaxAgeSeconds}");
         }

         if (this.mustRevalidate) {
            parts.add("must-revalidate");
         }

         if (this.proxyRevalidate) {
            parts.add("proxy-revalidate");
         }

         if (this.getVisibility() != null) {
            parts.add(this.getVisibility().getHeaderValue$ktor_http());
         }

         return CollectionsKt.joinToString$default(parts, ", ", null, null, 0, null, null, 62, null);
      }

      public override operator fun equals(other: Any?): Boolean {
         return other === this
            || other is CacheControl.MaxAge
               && (other as CacheControl.MaxAge).maxAgeSeconds == this.maxAgeSeconds
               && (other as CacheControl.MaxAge).proxyMaxAgeSeconds == this.proxyMaxAgeSeconds
               && (other as CacheControl.MaxAge).mustRevalidate == this.mustRevalidate
               && (other as CacheControl.MaxAge).proxyRevalidate == this.proxyRevalidate
               && (other as CacheControl.MaxAge).getVisibility() === this.getVisibility();
      }

      public override fun hashCode(): Int {
         val var10000: Int = 31
            * (
               31
                     * (
                        31 * (31 * this.maxAgeSeconds + (if (this.proxyMaxAgeSeconds != null) this.proxyMaxAgeSeconds else 0))
                           + java.lang.Boolean.hashCode(this.mustRevalidate)
                     )
                  + java.lang.Boolean.hashCode(this.proxyRevalidate)
            );
         val var10001: CacheControl.Visibility = this.getVisibility();
         return var10000 + (if (var10001 != null) var10001.hashCode() else 0);
      }
   }

   public class NoCache(visibility: io.ktor.http.CacheControl.Visibility?) : CacheControl(visibility) {
      public override fun toString(): String {
         return if (this.getVisibility() == null) "no-cache" else "no-cache, ${this.getVisibility().getHeaderValue$ktor_http()}";
      }

      public override operator fun equals(other: Any?): Boolean {
         return other is CacheControl.NoCache && this.getVisibility() === (other as CacheControl.NoCache).getVisibility();
      }

      public override fun hashCode(): Int {
         val var10000: CacheControl.Visibility = this.getVisibility();
         return if (var10000 != null) var10000.hashCode() else 0;
      }
   }

   public class NoStore(visibility: io.ktor.http.CacheControl.Visibility?) : CacheControl(visibility) {
      public override fun toString(): String {
         return if (this.getVisibility() == null) "no-store" else "no-store, ${this.getVisibility().getHeaderValue$ktor_http()}";
      }

      public override operator fun equals(other: Any?): Boolean {
         return other is CacheControl.NoStore && (other as CacheControl.NoStore).getVisibility() === this.getVisibility();
      }

      public override fun hashCode(): Int {
         val var10000: CacheControl.Visibility = this.getVisibility();
         return if (var10000 != null) var10000.hashCode() else 0;
      }
   }

   public enum class Visibility(headerValue: String) {
      Public("public"),
      Private("private")
      internal final val headerValue: String

      init {
         this.headerValue = headerValue;
      }

      @JvmStatic
      fun getEntries(): EnumEntries<CacheControl.Visibility> {
         return $ENTRIES;
      }
   }
}
