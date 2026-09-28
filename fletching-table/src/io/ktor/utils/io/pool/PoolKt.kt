@file:SourceDebugExtension(["SMAP\nPool.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Pool.kt\nio/ktor/utils/io/pool/PoolKt\n*L\n1#1,189:1\n182#1,5:190\n*S KotlinDebug\n*F\n+ 1 Pool.kt\nio/ktor/utils/io/pool/PoolKt\n*L\n173#1:190,5\n*E\n"])

package io.ktor.utils.io.pool

import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension

@Deprecated(message = "Use useInstance instead", replaceWith = @ReplaceWith(expression = "useInstance(block)", imports = []))
public inline fun <T : Any, R> ObjectPool<Any>.useBorrowed(block: (Any) -> Any): Any {
   label15: {
      val `instance$iv`: Any = `$this$useBorrowed`.borrow();

      try {
         val var6: Any = block.invoke(`instance$iv`);
      } catch (var8: java.lang.Throwable) {
         InlineMarker.finallyStart(1);
         `$this$useBorrowed`.recycle(`instance$iv`);
         InlineMarker.finallyEnd(1);
      }

      InlineMarker.finallyStart(1);
      `$this$useBorrowed`.recycle(`instance$iv`);
      InlineMarker.finallyEnd(1);
   }
}

public inline fun <T : Any, R> ObjectPool<Any>.useInstance(block: (Any) -> Any): Any {
   label14: {
      val instance: Any = `$this$useInstance`.borrow();

      try {
         val var4: Any = block.invoke(instance);
      } catch (var6: java.lang.Throwable) {
         InlineMarker.finallyStart(1);
         `$this$useInstance`.recycle(instance);
         InlineMarker.finallyEnd(1);
      }

      InlineMarker.finallyStart(1);
      `$this$useInstance`.recycle(instance);
      InlineMarker.finallyEnd(1);
   }
}
