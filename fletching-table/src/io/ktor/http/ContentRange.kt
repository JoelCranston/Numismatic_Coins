package io.ktor.http

public sealed class ContentRange protected constructor() {
   public data class Bounded(from: Long, to: Long) : ContentRange() {
      public final val from: Long
      public final val to: Long

      init {
         this.from = from;
         this.to = to;
      }

      public override fun toString(): String {
         return "${this.from}-${this.to}";
      }

      public operator fun component1(): Long {
         return this.from;
      }

      public operator fun component2(): Long {
         return this.to;
      }

      public fun copy(from: Long = this.from, to: Long = this.to): io.ktor.http.ContentRange.Bounded {
         return new ContentRange.Bounded(from, to);
      }

      public override fun hashCode(): Int {
         return java.lang.Long.hashCode(this.from) * 31 + java.lang.Long.hashCode(this.to);
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is ContentRange.Bounded) {
            return false;
         } else {
            val var2: ContentRange.Bounded = other as ContentRange.Bounded;
            if (this.from != (other as ContentRange.Bounded).from) {
               return false;
            } else {
               return this.to == var2.to;
            }
         }
      }
   }

   public data class Suffix(lastCount: Long) : ContentRange() {
      public final val lastCount: Long

      init {
         this.lastCount = lastCount;
      }

      public override fun toString(): String {
         return "-${this.lastCount}";
      }

      public operator fun component1(): Long {
         return this.lastCount;
      }

      public fun copy(lastCount: Long = this.lastCount): io.ktor.http.ContentRange.Suffix {
         return new ContentRange.Suffix(lastCount);
      }

      public override fun hashCode(): Int {
         return java.lang.Long.hashCode(this.lastCount);
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is ContentRange.Suffix) {
            return false;
         } else {
            return this.lastCount == (other as ContentRange.Suffix).lastCount;
         }
      }
   }

   public data class TailFrom(from: Long) : ContentRange() {
      public final val from: Long

      init {
         this.from = from;
      }

      public override fun toString(): String {
         return "${this.from}-";
      }

      public operator fun component1(): Long {
         return this.from;
      }

      public fun copy(from: Long = this.from): io.ktor.http.ContentRange.TailFrom {
         return new ContentRange.TailFrom(from);
      }

      public override fun hashCode(): Int {
         return java.lang.Long.hashCode(this.from);
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is ContentRange.TailFrom) {
            return false;
         } else {
            return this.from == (other as ContentRange.TailFrom).from;
         }
      }
   }
}
