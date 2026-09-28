@file:SourceDebugExtension(["SMAP\nThreadContextElement.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ThreadContextElement.kt\nkotlinx/coroutines/ThreadContextElementKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,285:1\n263#1:286\n1#2:287\n*S KotlinDebug\n*F\n+ 1 ThreadContextElement.kt\nkotlinx/coroutines/ThreadContextElementKt\n*L\n284#1:286\n*E\n"])

package kotlinx.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.internal.ThreadLocalElement
import kotlinx.coroutines.internal.ThreadLocalKey

public fun <T> ThreadLocal<T>.asContextElement(value: T = `$this$asContextElement`.get()): ThreadContextElement<T> {
   return (ThreadContextElement<T>)(new ThreadLocalElement<>(value, `$this$asContextElement`));
}

@JvmSynthetic
fun `asContextElement$default`(var0: ThreadLocal, var1: Any, var2: Int, var3: Any): ThreadContextElement {
   if ((var2 and 1) != 0) {
      var1 = var0.get();
   }

   return asContextElement(var0, var1);
}

public suspend inline fun ThreadLocal<*>.isPresent(): Boolean {
   return Boxing.boxBoolean(`$completion`.getContext().<ThreadLocalElement<?>>get(new ThreadLocalKey(`$this$isPresent`)) != null);
}

fun ThreadLocal<?>.`isPresent$$forInline`(`$completion`: Continuation<? super java.lang.Boolean>): Any {
   InlineMarker.mark(3);
   return null.getContext().<ThreadLocalElement<?>>get(new ThreadLocalKey(`$this$isPresent`)) != null;
}

public suspend inline fun ThreadLocal<*>.ensurePresent() {
   if (`$completion`.getContext().<ThreadLocalElement<?>>get(new ThreadLocalKey(`$this$ensurePresent`)) == null) {
      throw new IllegalStateException(("ThreadLocal $`$this$ensurePresent` is missing from context ${`$completion`.getContext()}").toString());
   } else {
      return Unit.INSTANCE;
   }
}

fun ThreadLocal<?>.`ensurePresent$$forInline`(`$completion`: Continuation<? super Unit>): Any {
   InlineMarker.mark(3);
   if (!null.getContext().<ThreadLocalElement<?>>get(new ThreadLocalKey(`$this$ensurePresent`)) != null) {
      val var10000: StringBuilder = new StringBuilder().append("ThreadLocal ").append(`$this$ensurePresent`).append(" is missing from context ");
      InlineMarker.mark(3);
      throw new IllegalStateException(var10000.append(null.getContext()).toString().toString());
   } else {
      return Unit.INSTANCE;
   }
}
