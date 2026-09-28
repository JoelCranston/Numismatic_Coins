package kotlinx.coroutines

internal interface CancelHandler : NotCompleted {
   public abstract fun invoke(cause: Throwable?) {
   }

   public class UserSupplied(handler: (Throwable?) -> Unit) : CancelHandler {
      private final val handler: (Throwable?) -> Unit

      init {
         this.handler = handler;
      }

      public override fun invoke(cause: Throwable?) {
         this.handler.invoke(cause);
      }

      public override fun toString(): String {
         return "CancelHandler.UserSupplied[${DebugStringsKt.getClassSimpleName(this.handler)}@${DebugStringsKt.getHexAddress(this)}]";
      }
   }
}
