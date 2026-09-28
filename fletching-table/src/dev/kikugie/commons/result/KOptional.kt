package dev.kikugie.commons.result

import dev.kikugie.commons.ExperimentalCommonsAPI
import java.util.NoSuchElementException
import kotlin.jvm.internal.SourceDebugExtension

@JvmInline
@ExperimentalCommonsAPI
public inline class KOptional<T> {
   @PublishedApi
   internal final val value: Any?

   public final val isPresent: Boolean
      public final get() {
         return arg0 === KOptional.MissingMarker.INSTANCE;
      }


   public final val isEmpty: Boolean
      public final get() {
         return arg0 === KOptional.MissingMarker.INSTANCE;
      }


   @Throws(java/util/NoSuchElementException::class)
   @JvmStatic
   public inline fun get(): Any {
      if (arg0 != KOptional.MissingMarker.INSTANCE) {
         return (T)arg0;
      } else {
         throw new NoSuchElementException("No value present");
      }
   }

   @JvmStatic
   public inline fun getOrNull(): Any? {
      var var10000: Any = arg0;
      if (arg0 == null) {
         var10000 = null;
      }

      return (T)var10000;
   }

   @JvmStatic
   public open fun toString(): String {
      return "KOptional(value=$arg0)";
   }

   override fun toString(): java.lang.String {
      return toString-impl(this.value);
   }

   @JvmStatic
   public open fun hashCode(): Int {
      return if (arg0 == null) 0 else arg0.hashCode();
   }

   override fun hashCode(): Int {
      return hashCode-impl(this.value);
   }

   @JvmStatic
   public open operator fun equals(other: Any?): Boolean {
      if (other !is KOptional) {
         return false;
      } else {
         return arg0 == (other as KOptional).unbox-impl();
      }
   }

   override fun equals(other: Any): Boolean {
      return equals-impl(this.value, other);
   }

   @PublishedApi
   @JvmStatic
   fun <T> `constructor-impl`(value: Any?): Any {
      return value;
   }

   @JvmStatic
   fun `equals-impl0`(p1: Any, p2: Any): Boolean {
      return p1 == p2;
   }

   @SourceDebugExtension(["SMAP\nOptional.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Optional.kt\ndev/kikugie/commons/result/KOptional$Companion\n*L\n1#1,84:1\n20#1,3:85\n*S KotlinDebug\n*F\n+ 1 Optional.kt\ndev/kikugie/commons/result/KOptional$Companion\n*L\n24#1:85,3\n*E\n"])
   public companion object {
      public inline fun <T> empty(): KOptional<T> {
         return KOptional.constructor-impl(KOptional.MissingMarker.INSTANCE);
      }

      public inline fun <T> of(value: T): KOptional<T> {
         return KOptional.constructor-impl(value);
      }

      public inline fun <T> notNull(value: T?): KOptional<T> {
         return if (value == null) KOptional.constructor-impl(KOptional.MissingMarker.INSTANCE) else KOptional.constructor-impl(value);
      }
   }

   @PublishedApi
   internal object MissingMarker
}
