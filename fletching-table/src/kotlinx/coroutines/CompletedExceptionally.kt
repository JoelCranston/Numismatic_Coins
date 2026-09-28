package kotlinx.coroutines

import kotlinx.atomicfu.AtomicBoolean

internal open class CompletedExceptionally(cause: Throwable, handled: Boolean = false) {
   public final val cause: Throwable
   private final val _handled: AtomicBoolean

   public final val handled: Boolean
      public final get() {
         return get_handled$volatile$FU().get(this) == 1;
      }


   init {
      this.cause = cause;
      this._handled$volatile = if (handled) 1 else 0;
   }

   public fun makeHandled(): Boolean {
      return get_handled$volatile$FU().compareAndSet(this, 0, 1);
   }

   public override fun toString(): String {
      return "${DebugStringsKt.getClassSimpleName(this)}[${this.cause}]";
   }
}
