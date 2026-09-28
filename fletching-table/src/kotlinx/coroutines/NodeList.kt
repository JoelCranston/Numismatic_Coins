package kotlinx.coroutines

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.internal.LockFreeLinkedListHead
import kotlinx.coroutines.internal.LockFreeLinkedListNode

@SourceDebugExtension(["SMAP\nJobSupport.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JobSupport.kt\nkotlinx/coroutines/NodeList\n+ 2 LockFreeLinkedList.kt\nkotlinx/coroutines/internal/LockFreeLinkedListHead\n*L\n1#1,1583:1\n273#2,6:1584\n*S KotlinDebug\n*F\n+ 1 JobSupport.kt\nkotlinx/coroutines/NodeList\n*L\n1510#1:1584,6\n*E\n"])
internal class NodeList : LockFreeLinkedListHead, Incomplete {
   public open val isActive: Boolean
      public open get() {
         return true;
      }


   public open val list: NodeList
      public open get() {
         return this;
      }


   public fun getString(state: String): String {
      val var2: StringBuilder = new StringBuilder();
      val `$this$getString_u24lambda_u241`: StringBuilder = var2;
      var2.append("List{");
      var2.append(state);
      var2.append("}[");
      var var11: Boolean = true;
      val `this_$iv`: LockFreeLinkedListHead = this;
      val var10000: Any = this.getNext();

      for (LockFreeLinkedListNode cur$iv = (LockFreeLinkedListNode)var10000; !(`cur$iv` == `this_$iv`); cur$iv = cur$iv.getNextNode()) {
         if (`cur$iv` is JobNode) {
            if (var11) {
               var11 = false;
            } else {
               `$this$getString_u24lambda_u241`.append(", ");
            }

            `$this$getString_u24lambda_u241`.append(`cur$iv`);
         }
      }

      `$this$getString_u24lambda_u241`.append("]");
      return var2.toString();
   }

   public override fun toString(): String {
      return if (DebugKt.getDEBUG()) this.getString("Active") else super.toString();
   }
}
