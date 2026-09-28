package io.ktor.util.internal

public abstract class AtomicOp<T> : OpDescriptor {
   public final val isDecided: Boolean
      public final get() {
         return this.consensus != LockFreeLinkedListKt.access$getNO_DECISION$p();
      }


   public fun tryDecide(decision: Any?): Boolean {
      if (decision === LockFreeLinkedListKt.access$getNO_DECISION$p()) {
         throw new IllegalStateException("Check failed.");
      } else {
         return consensus$FU.compareAndSet(this, LockFreeLinkedListKt.access$getNO_DECISION$p(), decision);
      }
   }

   private fun decide(decision: Any?): Any? {
      return if (this.tryDecide(decision)) decision else this.consensus;
   }

   public abstract fun prepare(affected: Any): Any? {
   }

   public abstract fun complete(affected: Any, failure: Any?) {
   }

   public override fun perform(affected: Any?): Any? {
      var decision: Any = this.consensus;
      if (this.consensus === LockFreeLinkedListKt.access$getNO_DECISION$p()) {
         decision = this.decide(this.prepare((T)affected));
      }

      this.complete((T)affected, decision);
      return decision;
   }
}
