@file:SourceDebugExtension(["SMAP\nOptional.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Optional.kt\ndev/kikugie/commons/result/OptionalKt\n+ 2 Optional.kt\ndev/kikugie/commons/result/KOptional$Companion\n*L\n1#1,84:1\n65#1:85\n22#2:86\n20#2:87\n*S KotlinDebug\n*F\n+ 1 Optional.kt\ndev/kikugie/commons/result/OptionalKt\n*L\n59#1:85\n72#1:86\n72#1:87\n*E\n"])

package dev.kikugie.commons.result

import dev.kikugie.commons.ExperimentalCommonsAPI
import kotlin.contracts.InvocationKind
import kotlin.jvm.internal.SourceDebugExtension

@ExperimentalCommonsAPI
public inline fun <T> KOptional<T>.ifPresent(action: (T) -> Unit): KOptional<T> {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   if (KOptional.isPresent-impl(`$this$ifPresent_u2d3LBOjBs`)) {
      action.invoke(`$this$ifPresent_u2d3LBOjBs`);
   }

   return `$this$ifPresent_u2d3LBOjBs`;
}

@ExperimentalCommonsAPI
public inline fun <T> KOptional<T>.ifEmpty(action: () -> Unit): KOptional<T> {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   if (KOptional.isEmpty-impl(`$this$ifEmpty_u2d3LBOjBs`)) {
      action.invoke();
   }

   return `$this$ifEmpty_u2d3LBOjBs`;
}

@ExperimentalCommonsAPI
public inline fun <T> KOptional<T>.getOrDefault(default: T): T {
   return (T)(if (KOptional.isPresent-impl(`$this$getOrDefault_u2d3LBOjBs`)) `$this$getOrDefault_u2d3LBOjBs` else var1);
}

@ExperimentalCommonsAPI
public inline fun <T> KOptional<T>.getOrElse(action: () -> T): T {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   return (T)(if (KOptional.isPresent-impl(`$this$getOrElse_u2d3LBOjBs`)) `$this$getOrElse_u2d3LBOjBs` else action.invoke());
}

@ExperimentalCommonsAPI
public inline fun <T, R> KOptional<T>.map(action: (T) -> R): KOptional<R> {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   val var10000: Any;
   if (KOptional.isPresent-impl(`$this$map_u2d3LBOjBs`)) {
      val `this_$iv`: KOptional.Companion = KOptional.Companion;
      var10000 = KOptional.constructor-impl(action.invoke(`$this$map_u2d3LBOjBs`));
   } else {
      val var6: KOptional.Companion = KOptional.Companion;
      var10000 = KOptional.constructor-impl(KOptional.MissingMarker.INSTANCE);
   }

   return var10000;
}

@ExperimentalCommonsAPI
public inline fun <T, R> KOptional<T>.fold(ifPresent: (T) -> R, ifEmpty: () -> R): R {
   contract {
      callsInPlace(ifPresent, InvocationKind.AT_MOST_ONCE)
      callsInPlace(ifEmpty, InvocationKind.AT_MOST_ONCE)
   }

   return (R)(if (KOptional.isPresent-impl(`$this$fold_u2dhqE9HUI`)) ifPresent.invoke(`$this$fold_u2dhqE9HUI`) else ifEmpty.invoke());
}
