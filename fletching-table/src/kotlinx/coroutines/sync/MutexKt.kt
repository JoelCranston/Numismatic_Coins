package kotlinx.coroutines.sync

import kotlin.contracts.InvocationKind
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function0
import kotlin.jvm.internal.InlineMarker
import kotlinx.coroutines.internal.Symbol
import kotlinx.coroutines.sync.MutexKt.withLock.1

private final val NO_OWNER: Symbol = new Symbol("NO_OWNER")
private final val ON_LOCK_ALREADY_LOCKED_BY_OWNER: Symbol = new Symbol("ALREADY_LOCKED_BY_OWNER")
private const val TRY_LOCK_SUCCESS: Int = 0
private const val TRY_LOCK_FAILED: Int = 1
private const val TRY_LOCK_ALREADY_LOCKED_BY_OWNER: Int = 2
private const val HOLDS_LOCK_UNLOCKED: Int = 0
private const val HOLDS_LOCK_YES: Int = 1
private const val HOLDS_LOCK_ANOTHER_OWNER: Int = 2

public fun Mutex(locked: Boolean = false): Mutex {
   return new MutexImpl(locked);
}

@JvmSynthetic
fun `Mutex$default`(var0: Boolean, var1: Int, var2: Any): Mutex {
   if ((var1 and 1) != 0) {
      var0 = false;
   }

   return Mutex(var0);
}

public suspend inline fun <T> Mutex.withLock(owner: Any? = ..., action: () -> T): T {
   contract {
      callsInPlace(action, InvocationKind.EXACTLY_ONCE)
   }

   label32: {
      var `$continuation`: Continuation;
      label30: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label30;
            }
         }

         `$continuation` = new 1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            `$continuation`.L$0 = `$this$withLock`;
            `$continuation`.L$1 = owner;
            `$continuation`.L$2 = action;
            `$continuation`.label = 1;
            if (`$this$withLock`.lock(owner, `$continuation`) === var9) {
               return var9;
            }
            break;
         case 1:
            action = `$continuation`.L$2 as Function0;
            owner = `$continuation`.L$1;
            `$this$withLock` = `$continuation`.L$0 as Mutex;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      try {
         val var5: Any = action.invoke();
      } catch (var10: java.lang.Throwable) {
         InlineMarker.finallyStart(1);
         `$this$withLock`.unlock(owner);
         InlineMarker.finallyEnd(1);
      }

      InlineMarker.finallyStart(1);
      `$this$withLock`.unlock(owner);
      InlineMarker.finallyEnd(1);
   }
}

fun <T> Mutex.`withLock$$forInline`(owner: Any, action: () -> T, `$completion`: Continuation<? super T>): Any {
   label15: {
      InlineMarker.mark(0);
      `$this$withLock`.lock(owner, `$completion`);
      InlineMarker.mark(1);

      try {
         val var5: Any = action.invoke();
      } catch (var7: java.lang.Throwable) {
         InlineMarker.finallyStart(1);
         `$this$withLock`.unlock(owner);
         InlineMarker.finallyEnd(1);
      }

      InlineMarker.finallyStart(1);
      `$this$withLock`.unlock(owner);
      InlineMarker.finallyEnd(1);
   }
}

@JvmSynthetic
fun Mutex.`withLock$default`(owner: Any, action: Function0, `$completion`: Continuation, `$i$f$withLock`: Int, var5: Any): Any {
   label19: {
      if ((`$i$f$withLock` and 1) != 0) {
         owner = null;
      }

      InlineMarker.mark(0);
      `$this$withLock_u24default`.lock(owner, `$completion`);
      InlineMarker.mark(1);

      try {
         var5 = action.invoke();
      } catch (var7: java.lang.Throwable) {
         InlineMarker.finallyStart(1);
         `$this$withLock_u24default`.unlock(owner);
         InlineMarker.finallyEnd(1);
      }

      InlineMarker.finallyStart(1);
      `$this$withLock_u24default`.unlock(owner);
      InlineMarker.finallyEnd(1);
   }
}

@JvmSynthetic
fun `access$getNO_OWNER$p`(): Symbol {
   return NO_OWNER;
}

@JvmSynthetic
fun `access$getON_LOCK_ALREADY_LOCKED_BY_OWNER$p`(): Symbol {
   return ON_LOCK_ALREADY_LOCKED_BY_OWNER;
}
