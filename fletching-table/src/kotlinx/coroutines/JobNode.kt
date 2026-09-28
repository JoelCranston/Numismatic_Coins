package kotlinx.coroutines

import kotlinx.coroutines.internal.LockFreeLinkedListNode

internal abstract class JobNode : LockFreeLinkedListNode, DisposableHandle, Incomplete {
   public final lateinit var job: JobSupport
      internal set

   public abstract val onCancelling: Boolean

   public open val isActive: Boolean
      public open get() {
         return true;
      }


   public open val list: NodeList?
      public open get() {
         return null;
      }


   public override fun dispose() {
      this.getJob().removeNode$kotlinx_coroutines_core(this);
   }

   public override fun toString(): String {
      return "${DebugStringsKt.getClassSimpleName(this)}@${DebugStringsKt.getHexAddress(this)}[job@${DebugStringsKt.getHexAddress(this.getJob())}]";
   }

   public abstract fun invoke(cause: Throwable?) {
   }
}
