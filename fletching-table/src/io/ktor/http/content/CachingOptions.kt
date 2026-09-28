package io.ktor.http.content

import io.ktor.http.CacheControl
import io.ktor.util.date.GMTDate

public data class CachingOptions(cacheControl: CacheControl? = null, expires: GMTDate? = null) {
   public final val cacheControl: CacheControl?
   public final val expires: GMTDate?

   init {
      this.cacheControl = cacheControl;
      this.expires = expires;
   }

   public operator fun component1(): CacheControl? {
      return this.cacheControl;
   }

   public operator fun component2(): GMTDate? {
      return this.expires;
   }

   public fun copy(cacheControl: CacheControl? = this.cacheControl, expires: GMTDate? = this.expires): CachingOptions {
      return new CachingOptions(cacheControl, expires);
   }

   public override fun toString(): String {
      return "CachingOptions(cacheControl=${this.cacheControl}, expires=${this.expires})";
   }

   public override fun hashCode(): Int {
      return (if (this.cacheControl == null) 0 else this.cacheControl.hashCode()) * 31 + (if (this.expires == null) 0 else this.expires.hashCode());
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is CachingOptions) {
         return false;
      } else {
         val var2: CachingOptions = other as CachingOptions;
         if (!(this.cacheControl == (other as CachingOptions).cacheControl)) {
            return false;
         } else {
            return this.expires == var2.expires;
         }
      }
   }

   fun CachingOptions() {
      this(null, null, 3, null);
   }
}
