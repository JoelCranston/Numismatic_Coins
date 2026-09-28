@file:SourceDebugExtension(["SMAP\nControlFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ControlFlow.kt\ndev/kikugie/commons/ControlFlowKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,76:1\n1#2:77\n*E\n"])

package dev.kikugie.commons

import kotlin.contracts.InvocationKind
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension

public inline infix fun <T> Any?.then(next: T): T {
   return (T)next;
}

public inline fun <T> T.applyIf(check: Boolean, action: (T) -> Unit): T {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   val var10000: Any;
   if (check) {
      action.invoke(`$this$applyIf`);
      var10000 = `$this$applyIf`;
   } else {
      var10000 = `$this$applyIf`;
   }

   return (T)var10000;
}

public inline fun <T> T.applyIf(check: (T) -> Boolean, action: (T) -> Unit): T {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   val var10000: Any;
   if (check.invoke(`$this$applyIf`) as java.lang.Boolean) {
      action.invoke(`$this$applyIf`);
      var10000 = `$this$applyIf`;
   } else {
      var10000 = `$this$applyIf`;
   }

   return (T)var10000;
}

public inline fun <T, A> T.applyIfNotNull(value: A?, action: (T, A) -> Unit): T {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   val var10000: Any;
   if (value != null) {
      action.invoke(`$this$applyIfNotNull`, value);
      var10000 = `$this$applyIfNotNull`;
   } else {
      var10000 = `$this$applyIfNotNull`;
   }

   return (T)var10000;
}

public inline fun <T> T.runIf(check: Boolean, action: (T) -> T): T {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   return (T)(if (check) action.invoke(`$this$runIf`) else `$this$runIf`);
}

public inline fun <T> T.runIf(check: (T) -> Boolean, action: (T) -> T): T {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   return (T)(if (check.invoke(`$this$runIf`)) action.invoke(`$this$runIf`) else `$this$runIf`);
}

public inline fun <T, A> T.runIfNotNull(value: A?, action: (T, A) -> T): T {
   contract {
      callsInPlace(action, InvocationKind.AT_MOST_ONCE)
   }

   return (T)(if (value != null) action.invoke(`$this$runIfNotNull`, value) else `$this$runIfNotNull`);
}

@JvmSynthetic
public inline fun <reified T> Any.takeAs(): T {
   Intrinsics.reifiedOperationMarker(1, "T");
   return (T)(`$this$takeAs` as Any);
}

@JvmSynthetic
public inline fun <reified T> Any.takeAsOrNull(): T? {
   Intrinsics.reifiedOperationMarker(2, "T");
   return (T)(`$this$takeAsOrNull` as Any);
}
