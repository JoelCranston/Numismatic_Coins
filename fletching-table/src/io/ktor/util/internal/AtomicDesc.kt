package io.ktor.util.internal

public abstract class AtomicDesc {
   public abstract fun prepare(op: AtomicOp<*>): Any? {
   }

   public abstract fun complete(op: AtomicOp<*>, failure: Any?) {
   }
}
