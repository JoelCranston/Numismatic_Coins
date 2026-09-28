package kotlinx.coroutines.internal

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.DebugKt

@JvmInline
@SourceDebugExtension(["SMAP\nInlineList.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InlineList.kt\nkotlinx/coroutines/internal/InlineList\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,45:1\n1#2:46\n*E\n"])
internal inline class InlineList<E> {
   private final val holder: Any?

   @JvmStatic
   public operator fun plus(element: Any): InlineList<Any> {
      if (DebugKt.getASSERTIONS_ENABLED() && element is java.util.List) {
         throw new AssertionError();
      } else {
         val var10000: Any;
         if (arg0 == null) {
            var10000 = constructor-impl(element);
         } else if (arg0 is ArrayList) {
            (arg0 as ArrayList).add(element);
            var10000 = constructor-impl(arg0);
         } else {
            val list: ArrayList = new ArrayList(4);
            list.add(arg0);
            list.add(element);
            var10000 = constructor-impl(list);
         }

         return var10000;
      }
   }

   @JvmStatic
   public inline fun forEachReversed(action: (Any) -> Unit) {
      if (arg0 != null) {
         if (arg0 !is ArrayList) {
            action.invoke(arg0);
         } else {
            val list: ArrayList = arg0 as ArrayList;

            for (int i = ((ArrayList)arg0).size() - 1; -1 < i; i--) {
               action.invoke(list.get(i));
            }
         }
      }
   }

   @JvmStatic
   fun `toString-impl`(arg0: Any): java.lang.String {
      return "InlineList(holder=$arg0)";
   }

   public override fun toString(): String {
      return toString-impl(this.holder);
   }

   @JvmStatic
   fun `hashCode-impl`(arg0: Any): Int {
      return if (arg0 == null) 0 else arg0.hashCode();
   }

   public override fun hashCode(): Int {
      return hashCode-impl(this.holder);
   }

   @JvmStatic
   fun `equals-impl`(arg0: Any, other: Any): Boolean {
      if (other !is InlineList) {
         return false;
      } else {
         return arg0 == (other as InlineList).unbox-impl();
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      return equals-impl(this.holder, other);
   }

   @JvmStatic
   fun <E> `constructor-impl`(holder: Any?): Any {
      return holder;
   }

   @JvmStatic
   fun `equals-impl0`(p1: Any, p2: Any): Boolean {
      return p1 == p2;
   }
}
