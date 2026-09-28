package kotlinx.coroutines

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

public data class CoroutineName(name: String) : AbstractCoroutineContextElement(Key) {
   public final val name: String

   init {
      this.name = name;
   }

   public override fun toString(): String {
      return "CoroutineName(${this.name})";
   }

   public operator fun component1(): String {
      return this.name;
   }

   public fun copy(name: String = this.name): CoroutineName {
      return new CoroutineName(name);
   }

   public override fun hashCode(): Int {
      return this.name.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is CoroutineName) {
         return false;
      } else {
         return this.name == (other as CoroutineName).name;
      }
   }

   public companion object Key : CoroutineContext.Key<CoroutineName>
}
