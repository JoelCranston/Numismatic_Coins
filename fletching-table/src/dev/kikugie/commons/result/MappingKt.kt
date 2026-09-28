@file:SourceDebugExtension(["SMAP\nMapping.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Mapping.kt\ndev/kikugie/commons/result/MappingKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,53:1\n1#2:54\n13472#3,2:55\n*S KotlinDebug\n*F\n+ 1 Mapping.kt\ndev/kikugie/commons/result/MappingKt\n*L\n52#1:55,2\n*E\n"])

package dev.kikugie.commons.result

import kotlin.contracts.InvocationKind
import kotlin.jvm.internal.SourceDebugExtension

public inline fun <T : Any> notNullResult(value: T?): Result<T> {
   return if (value != null) Result.constructor-impl(value) else Result.constructor-impl(ResultKt.createFailure(new NullPointerException()));
}

public inline fun <T : Any> notNullResult(value: T?, message: () -> String): Result<T> {
   contract {
      callsInPlace(message, InvocationKind.AT_MOST_ONCE)
   }

   return if (value != null)
      Result.constructor-impl(value)
      else
      Result.constructor-impl(ResultKt.createFailure(new NullPointerException(message.invoke() as java.lang.String)));
}

public inline fun <T> Result<T>.mapException(transform: (Throwable) -> Throwable): Result<T> {
   contract {
      callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
   }

   val var10000: java.lang.Throwable = Result.exceptionOrNull-impl(`$this$mapException`);
   return if (var10000 != null) Result.constructor-impl(ResultKt.createFailure(transform.invoke(var10000) as java.lang.Throwable)) else `$this$mapException`;
}

public inline fun <T, R> Result<T>.mapResult(transform: (T) -> Result<R>): Result<R> {
   contract {
      callsInPlace(transform, InvocationKind.AT_MOST_ONCE)
   }

   val var10000: Any;
   if (Result.exceptionOrNull-impl(`$this$mapResult`) != null) {
      var10000 = `$this$mapResult`;
   } else {
      ResultKt.throwOnFailure(`$this$mapResult`);
      var10000 = (transform.invoke(`$this$mapResult`) as Result).unbox-impl();
   }

   return var10000;
}

public fun <T : Throwable> T.inherit(other: Throwable): T {
   val `$this$inherit_u24lambda_u243`: java.lang.Throwable = `$this$inherit`;
   `$this$inherit`.setStackTrace(other.getStackTrace());
   val `$this$forEach$iv`: java.lang.Throwable = `$this$inherit`;

   try {
      val var14: Any = Result.constructor-impl(`$this$forEach$iv`.initCause(other.getCause()));
   } catch (var12: java.lang.Throwable) {
      val `$i$f$forEach`: Any = Result.constructor-impl(ResultKt.createFailure(var12));
   }
   for (Object element$iv : $this$forEach$iv) {
      `$this$inherit_u24lambda_u243`.addSuppressed(`element$iv` as java.lang.Throwable);
   }

   return (T)`$this$inherit`;
}
