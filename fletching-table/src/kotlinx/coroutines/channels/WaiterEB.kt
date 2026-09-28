package kotlinx.coroutines.channels

import kotlinx.coroutines.Waiter

private class WaiterEB(waiter: Waiter) {
   public final val waiter: Waiter

   init {
      this.waiter = waiter;
   }

   public override fun toString(): String {
      return "WaiterEB(${this.waiter})";
   }
}
