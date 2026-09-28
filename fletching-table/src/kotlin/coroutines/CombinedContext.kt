package kotlin.coroutines

import java.io.InvalidObjectException
import java.io.ObjectInputStream
import java.io.Serializable
import kotlin.coroutines.CoroutineContext.Element
import kotlin.coroutines.CoroutineContext.Key
import kotlin.jvm.internal.Ref
import kotlin.jvm.internal.SourceDebugExtension

@SinceKotlin(version = "1.3")
@SourceDebugExtension(["SMAP\nCoroutineContextImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoroutineContextImpl.kt\nkotlin/coroutines/CombinedContext\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,200:1\n1#2:201\n*E\n"])
internal class CombinedContext(left: CoroutineContext, element: Element) : CoroutineContext, Serializable {
   private final val left: CoroutineContext
   private final val element: Element

   init {
      this.left = left;
      this.element = element;
   }

   public override operator fun <E : Element> get(key: Key<E>): E? {
      var cur: CombinedContext = this;

      while (true) {
         val next: CoroutineContext.Element = cur.element.get(key);
         if (next != null) {
            return (E)next;
         }

         if (cur.left !is CombinedContext) {
            return (E)cur.left.get(key);
         }

         cur = cur.left as CombinedContext;
      }
   }

   public override fun <R> fold(initial: R, operation: (R, Element) -> R): R {
      return (R)operation.invoke(this.left.fold(initial, operation), this.element);
   }

   public override fun minusKey(key: Key<*>): CoroutineContext {
      if (this.element.get(key) != null) {
         return this.left;
      } else {
         val newLeft: CoroutineContext = this.left.minusKey(key);
         return if (newLeft === this.left)
            this
            else
            (if (newLeft === EmptyCoroutineContext.INSTANCE) this.element else new CombinedContext(newLeft, this.element));
      }
   }

   private fun size(): Int {
      var cur: CombinedContext = this;
      var size: Int = 2;

      while (true) {
         val var3: CoroutineContext = cur.left;
         val var10000: CombinedContext = cur.left as? CombinedContext;
         if ((cur.left as? CombinedContext) == null) {
            return size;
         }

         cur = var10000;
         size++;
      }
   }

   private fun contains(element: Element): Boolean {
      return this.get(element.getKey()) == element;
   }

   private fun containsAll(context: CombinedContext): Boolean {
      var cur: CombinedContext = context;

      while (this.contains(cur.element)) {
         val next: CoroutineContext = cur.left;
         if (cur.left !is CombinedContext) {
            return this.contains(next as CoroutineContext.Element);
         }

         cur = cur.left as CombinedContext;
      }

      return false;
   }

   public override operator fun equals(other: Any?): Boolean {
      return this === other || other is CombinedContext && (other as CombinedContext).size() == this.size() && (other as CombinedContext).containsAll(this);
   }

   public override fun hashCode(): Int {
      return this.left.hashCode() + this.element.hashCode();
   }

   public override fun toString(): String {
      return "[${this.fold("", CombinedContext::toString$lambda$0)}]";
   }

   private fun writeReplace(): Any {
      val n: Int = this.size();
      val elements: Array<CoroutineContext> = new CoroutineContext[n];
      val index: Ref.IntRef = new Ref.IntRef();
      this.fold(Unit.INSTANCE, CombinedContext::writeReplace$lambda$0);
      if (index.element != n) {
         throw new IllegalStateException("Check failed.");
      } else {
         return new CombinedContext.Serialized(elements);
      }
   }

   private fun readObject(input: ObjectInputStream) {
      throw new InvalidObjectException("Deserialization is supported via proxy only");
   }

   @JvmStatic
   fun `toString$lambda$0`(acc: java.lang.String, element: CoroutineContext.Element): java.lang.String {
      return if (acc.length() == 0) element.toString() else "$acc, $element";
   }

   @JvmStatic
   fun `writeReplace$lambda$0`(`$elements`: Array<CoroutineContext>, `$index`: Ref.IntRef, var2: Unit, element: CoroutineContext.Element): Unit {
      `$elements`[`$index`.element++] = element;
      return Unit.INSTANCE;
   }

   @SourceDebugExtension(["SMAP\nCoroutineContextImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoroutineContextImpl.kt\nkotlin/coroutines/CombinedContext$Serialized\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,200:1\n13216#2,3:201\n*S KotlinDebug\n*F\n+ 1 CoroutineContextImpl.kt\nkotlin/coroutines/CombinedContext$Serialized\n*L\n197#1:201,3\n*E\n"])
   private class Serialized(vararg elements: Any) : Serializable {
      public final val elements: Array<CoroutineContext>

      init {
         this.elements = elements;
      }

      private fun readResolve(): Any {
         var `accumulator$iv`: Any = EmptyCoroutineContext.INSTANCE;

         val `$this$fold$iv`: Array<CoroutineContext>;
         for (Object element$iv : $this$fold$iv) {
            `accumulator$iv` = (`accumulator$iv` as CoroutineContext).plus((CoroutineContext)`element$iv`);
         }

         return `accumulator$iv`;
      }

      public companion object {
         private const val serialVersionUID: Long
      }
   }
}
