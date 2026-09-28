package io.ktor.network.selector

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nLockFreeMPSCQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 LockFreeMPSCQueue.kt\nio/ktor/network/selector/LockFreeMPSCQueue\n+ 2 AtomicFU.common.kt\nkotlinx/atomicfu/AtomicFU_commonKt\n*L\n1#1,244:1\n155#2,2:245\n155#2,2:247\n155#2,2:249\n*S KotlinDebug\n*F\n+ 1 LockFreeMPSCQueue.kt\nio/ktor/network/selector/LockFreeMPSCQueue\n*L\n30#1:245,2\n37#1:247,2\n48#1:249,2\n*E\n"])
internal class LockFreeMPSCQueue<E> {
   public final val isEmpty: Boolean
      public final get() {
         return (this.curRef as LockFreeMPSCQueueCore).isEmpty();
      }


   public fun close() {
      val `$this$loop$iv`: LockFreeMPSCQueue = this;

      while (true) {
         val cur: LockFreeMPSCQueueCore = `$this$loop$iv`.curRef as LockFreeMPSCQueueCore;
         if ((`$this$loop$iv`.curRef as LockFreeMPSCQueueCore).close()) {
            return;
         }

         curRef$FU.compareAndSet(this, cur, cur.next());
      }
   }

   public fun addLast(element: Any): Boolean {
      val `$this$loop$iv`: LockFreeMPSCQueue = this;

      while (true) {
         val cur: LockFreeMPSCQueueCore = `$this$loop$iv`.curRef as LockFreeMPSCQueueCore;
         switch (((LockFreeMPSCQueueCore)$this$loop$iv.curRef).addLast(element)) {
            case 0:
               return true;
            case 1:
               curRef$FU.compareAndSet(this, cur, cur.next());
            default:
               break;
            case 2:
               return false;
         }
      }
   }

   public fun removeFirstOrNull(): Any? {
      val `$this$loop$iv`: LockFreeMPSCQueue = this;

      while (true) {
         val cur: LockFreeMPSCQueueCore = `$this$loop$iv`.curRef as LockFreeMPSCQueueCore;
         val result: Any = (`$this$loop$iv`.curRef as LockFreeMPSCQueueCore).removeFirstOrNull();
         if (result != LockFreeMPSCQueueCore.REMOVE_FROZEN) {
            return (E)result;
         }

         curRef$FU.compareAndSet(this, cur, cur.next());
      }
   }
}
