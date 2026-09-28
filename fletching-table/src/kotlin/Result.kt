package kotlin

import java.io.Serializable
import kotlin.internal.InlineOnly

@JvmInline
@SinceKotlin(version = "1.3")
public inline class Result<T> : Serializable {
   @PublishedApi
   internal final val value: Any?

   public final val isSuccess: Boolean
      public final get() {
         return var0 !is Result.Failure;
      }


   public final val isFailure: Boolean
      public final get() {
         return var0 is Result.Failure;
      }


   @InlineOnly
   @JvmStatic
   public inline fun getOrNull(): Any? {
      return (T)(if (isFailure-impl(var0)) null else var0);
   }

   @JvmStatic
   public fun exceptionOrNull(): Throwable? {
      return if (var0 is Result.Failure) (var0 as Result.Failure).exception else null;
   }

   @JvmStatic
   public open fun toString(): String {
      return if (var0 is Result.Failure) (var0 as Result.Failure).toString() else "Success($var0)";
   }

   override fun toString(): java.lang.String {
      return toString-impl(this.value);
   }

   @JvmStatic
   fun `hashCode-impl`(var0: Any): Int {
      return if (var0 == null) 0 else var0.hashCode();
   }

   public override fun hashCode(): Int {
      return hashCode-impl(this.value);
   }

   @JvmStatic
   fun `equals-impl`(var0: Any, other: Any): Boolean {
      if (other !is Result) {
         return false;
      } else {
         return var0 == (other as Result).unbox-impl();
      }
   }

   public override operator fun equals(other: Any?): Boolean {
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

   public companion object {
      @InlineOnly
      @JvmName(name = "success")
      public inline fun <T> success(value: T): Result<T> {
         return Result.constructor-impl(value);
      }

      @InlineOnly
      @JvmName(name = "failure")
      public inline fun <T> failure(exception: Throwable): Result<T> {
         return Result.constructor-impl(ResultKt.createFailure(exception));
      }
   }

   internal class Failure(exception: Throwable) : Serializable {
      public final val exception: Throwable

      init {
         this.exception = exception;
      }

      public override operator fun equals(other: Any?): Boolean {
         return other is Result.Failure && this.exception == (other as Result.Failure).exception;
      }

      public override fun hashCode(): Int {
         return this.exception.hashCode();
      }

      public override fun toString(): String {
         return "Failure(${this.exception})";
      }
   }
}
