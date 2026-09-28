package kotlin.coroutines

import java.io.Serializable
import kotlin.coroutines.CoroutineContext.Element
import kotlin.coroutines.CoroutineContext.Key

@SinceKotlin(version = "1.3")
public object EmptyCoroutineContext : CoroutineContext, Serializable {
   private const val serialVersionUID: Long = 0L

   private fun readResolve(): Any {
      return INSTANCE;
   }

   public override operator fun <E : Element> get(key: Key<E>): E? {
      return null;
   }

   public override fun <R> fold(initial: R, operation: (R, Element) -> R): R {
      return (R)initial;
   }

   public override operator fun plus(context: CoroutineContext): CoroutineContext {
      return context;
   }

   public override fun minusKey(key: Key<*>): CoroutineContext {
      return this;
   }

   public override fun hashCode(): Int {
      return 0;
   }

   public override fun toString(): String {
      return "EmptyCoroutineContext";
   }
}
