package kotlinx.coroutines

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

@IgnoreJRERequirement
@PublishedApi
internal data class CoroutineId(id: Long) : AbstractCoroutineContextElement(Key), ThreadContextElement<java.lang.String> {
   public final val id: Long

   init {
      this.id = id;
   }

   public override fun toString(): String {
      return "CoroutineId(${this.id})";
   }

   public open fun updateThreadContext(context: CoroutineContext): String {
      var var12: java.lang.String;
      label15: {
         val var10000: CoroutineName = context.get(CoroutineName.Key);
         if (var10000 != null) {
            var12 = var10000.getName();
            if (var12 != null) {
               break label15;
            }
         }

         var12 = "coroutine";
      }

      val currentThread: Thread = Thread.currentThread();
      val oldName: java.lang.String = currentThread.getName();
      var var11: Int = StringsKt.lastIndexOf$default(oldName, " @", 0, false, 6, null);
      if (var11 < 0) {
         var11 = oldName.length();
      }

      val var7: StringBuilder = new StringBuilder(var11 + var12.length() + 10);
      val var10001: java.lang.String = oldName.substring(0, var11);
      var7.append(var10001);
      var7.append(" @");
      var7.append(var12);
      var7.append('#');
      var7.append(this.id);
      currentThread.setName(var7.toString());
      return oldName;
   }

   public open fun restoreThreadContext(context: CoroutineContext, oldState: String) {
      Thread.currentThread().setName(oldState);
   }

   public operator fun component1(): Long {
      return this.id;
   }

   public fun copy(id: Long = this.id): CoroutineId {
      return new CoroutineId(id);
   }

   public override fun hashCode(): Int {
      return java.lang.Long.hashCode(this.id);
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is CoroutineId) {
         return false;
      } else {
         return this.id == (other as CoroutineId).id;
      }
   }

   public companion object Key : CoroutineContext.Key<CoroutineId>
}
