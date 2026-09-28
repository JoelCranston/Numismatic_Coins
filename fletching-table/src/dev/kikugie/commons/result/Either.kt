package dev.kikugie.commons.result

import dev.kikugie.commons.ExperimentalCommonsAPI

@ExperimentalCommonsAPI
public sealed interface Either<L, R> {
   public open val isRight: Boolean
      public open get() {
         return false;
      }


   public open val isLeft: Boolean
      public open get() {
         return false;
      }


   public companion object {
      public inline fun <L, R> left(value: L): Either<L, R> {
         return new Either.Left<>((L)value);
      }

      public inline fun <L, R> right(value: R): Either<L, R> {
         return new Either.Right<>((R)value);
      }
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun <L, R> isRight(`$this`: Either<L, R>): Boolean {
         return Either.access$isRight$jd(`$this`);
      }

      @Deprecated
      @JvmStatic
      fun <L, R> isLeft(`$this`: Either<L, R>): Boolean {
         return Either.access$isLeft$jd(`$this`);
      }
   }

   public data class Left<L, R> @PublishedApi  internal constructor(value: Any) : Either<L, R> {
      public final val value: Any

      public open val isLeft: Boolean
         public open get() {
            return true;
         }


      init {
         this.value = (L)value;
      }

      public operator fun component1(): Any {
         return this.value;
      }

      internal fun copy(value: Any = ...): dev.kikugie.commons.result.Either.Left<Any, Any> {
         return new Either.Left<>((L)value);
      }

      public override fun toString(): String {
         return "Left(value=${this.value})";
      }

      public override fun hashCode(): Int {
         return if (this.value == null) 0 else this.value.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is Either.Left) {
            return false;
         } else {
            return this.value == (other as Either.Left).value;
         }
      }

      override fun isRight(): Boolean {
         return Either.super.isRight();
      }
   }

   public data class Right<L, R> @PublishedApi  internal constructor(value: Any) : Either<L, R> {
      public final val value: Any

      public open val isRight: Boolean
         public open get() {
            return true;
         }


      init {
         this.value = (R)value;
      }

      public operator fun component1(): Any {
         return this.value;
      }

      internal fun copy(value: Any = ...): dev.kikugie.commons.result.Either.Right<Any, Any> {
         return new Either.Right<>((R)value);
      }

      public override fun toString(): String {
         return "Right(value=${this.value})";
      }

      public override fun hashCode(): Int {
         return if (this.value == null) 0 else this.value.hashCode();
      }

      public override operator fun equals(other: Any?): Boolean {
         if (this === other) {
            return true;
         } else if (other !is Either.Right) {
            return false;
         } else {
            return this.value == (other as Either.Right).value;
         }
      }

      override fun isLeft(): Boolean {
         return Either.super.isLeft();
      }
   }
}
