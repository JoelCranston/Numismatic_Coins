package kotlinx.coroutines.internal

import java.util.ArrayList
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater
import java.util.concurrent.atomic.AtomicReferenceArray
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.atomicfu.AtomicArray
import kotlinx.atomicfu.AtomicInt

@SourceDebugExtension(["SMAP\nOnDemandAllocatingPool.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OnDemandAllocatingPool.kt\nkotlinx/coroutines/internal/OnDemandAllocatingPool\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 OnDemandAllocatingPool.kt\nkotlinx/coroutines/internal/OnDemandAllocatingPoolKt\n*L\n1#1,103:1\n37#1:104\n37#1:105\n28#1,10:106\n37#1:126\n1557#2:116\n1628#2,2:117\n1630#2:121\n1557#2:122\n1628#2,3:123\n97#3,2:119\n*S KotlinDebug\n*F\n+ 1 OnDemandAllocatingPool.kt\nkotlinx/coroutines/internal/OnDemandAllocatingPool\n*L\n31#1:104\n50#1:105\n72#1:106,10\n88#1:126\n73#1:116\n73#1:117,2\n73#1:121\n87#1:122\n87#1:123,3\n75#1:119,2\n*E\n"])
internal class OnDemandAllocatingPool<T>(maxCapacity: Int, create: (Int) -> Any) {
   private final val maxCapacity: Int
   private final val create: (Int) -> Any
   private final val controlState: AtomicInt
   private final val elements: AtomicArray<Any?>

   init {
      this.maxCapacity = maxCapacity;
      this.create = create;
      this.elements = new AtomicReferenceArray(this.maxCapacity);
   }

   private inline fun tryForbidNewElements(): Int {
      val `handler$atomicfu$iv`: AtomicIntegerFieldUpdater = getControlState$volatile$FU();

      val it: Int;
      do {
         it = `handler$atomicfu$iv`.get(this);
         if ((it and Integer.MIN_VALUE) != 0) {
            return 0;
         }
      } while (!getControlState$volatile$FU().compareAndSet(this, it, it | -2147483648));

      return it;
   }

   private inline fun Int.isClosed(): Boolean {
      return (`$this$isClosed` and Integer.MIN_VALUE) != 0;
   }

   public fun allocate(): Boolean {
      val `handler$atomicfu$iv`: AtomicIntegerFieldUpdater = getControlState$volatile$FU();

      val ctl: Int;
      do {
         ctl = `handler$atomicfu$iv`.get(this);
         if ((ctl and Integer.MIN_VALUE) != 0) {
            return false;
         }

         if (ctl >= this.maxCapacity) {
            return true;
         }
      } while (!getControlState$volatile$FU().compareAndSet(this, ctl, ctl + 1));

      this.getElements().set(ctl, this.create.invoke(ctl));
      return true;
   }

   public fun close(): List<Any> {
      val `$this$map$iv`: OnDemandAllocatingPool = this;
      val `destination$iv$iv`: AtomicIntegerFieldUpdater = getControlState$volatile$FU();

      var var10000: Int;
      while (true) {
         val `$i$f$mapTo`: Int = `destination$iv$iv`.get(`$this$map$iv`);
         if ((`$i$f$mapTo` and Integer.MIN_VALUE) != 0) {
            var10000 = 0;
            break;
         }

         if (getControlState$volatile$FU().compareAndSet(`$this$map$iv`, `$i$f$mapTo`, `$i$f$mapTo` or Integer.MIN_VALUE)) {
            var10000 = `$i$f$mapTo`;
            break;
         }
      }

      val var15: java.lang.Iterable = RangesKt.until(0, var10000);
      val var17: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var15, 10));
      val var19: java.util.Iterator = var15.iterator();

      while (var19.hasNext()) {
         val i: Int = (var19 as IntIterator).nextInt();

         val element: Any;
         do {
            element = this.getElements().getAndSet(i, null);
         } while (element == null);

         var17.add(element);
      }

      return var17 as MutableList<T>;
   }

   internal fun stateRepresentation(): String {
      val ctl: Int = getControlState$volatile$FU().get(this);
      val closedStr: java.lang.Iterable = RangesKt.until(0, ctl and Integer.MAX_VALUE);
      val `$i$f$isClosed`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(closedStr, 10));
      val var8: java.util.Iterator = closedStr.iterator();

      while (var8.hasNext()) {
         `$i$f$isClosed`.add(this.getElements().get((var8 as IntIterator).nextInt()));
      }

      return "${(`$i$f$isClosed` as java.util.List).toString()}${if ((ctl and Integer.MIN_VALUE) != 0) "[closed]" else ""}";
   }

   public override fun toString(): String {
      return "OnDemandAllocatingPool(${this.stateRepresentation$kotlinx_coroutines_core()})";
   }
}
