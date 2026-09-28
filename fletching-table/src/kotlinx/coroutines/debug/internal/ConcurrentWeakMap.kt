package kotlinx.coroutines.debug.internal

import java.lang.ref.Reference
import java.lang.ref.ReferenceQueue
import java.util.NoSuchElementException
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater
import java.util.concurrent.atomic.AtomicReferenceArray
import kotlin.collections.MutableMap.MutableEntry
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.markers.KMutableIterator
import kotlin.jvm.internal.markers.KMutableMap
import kotlinx.atomicfu.AtomicArray
import kotlinx.atomicfu.AtomicInt
import kotlinx.atomicfu.AtomicRef

@SourceDebugExtension(["SMAP\nConcurrentWeakMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConcurrentWeakMap.kt\nkotlinx/coroutines/debug/internal/ConcurrentWeakMap\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,280:1\n1#2:281\n*E\n"])
internal class ConcurrentWeakMap<K, V>(weakRefQueue: Boolean = false) : AbstractMutableMap<K, V> {
   private final val _size: AtomicInt
   private final val core: AtomicRef<kotlinx.coroutines.debug.internal.ConcurrentWeakMap.Core>
   private final val weakRefQueue: ReferenceQueue<Any>?

   public open val size: Int
      public open get() {
         return get_size$volatile$FU().get(this);
      }


   public open val keys: MutableSet<Any>
      public open get() {
         return new ConcurrentWeakMap.KeyValueSet<>(this, ConcurrentWeakMap::_get_keys_$lambda$0);
      }


   public open val entries: MutableSet<MutableEntry<Any, Any>>
      public open get() {
         return new ConcurrentWeakMap.KeyValueSet<>(this, ConcurrentWeakMap::_get_entries_$lambda$1);
      }


   init {
      this.weakRefQueue = if (weakRefQueue) new ReferenceQueue<>() else null;
   }

   private fun decrementSize() {
      get_size$volatile$FU().decrementAndGet(this);
   }

   public override operator fun get(key: Any): Any? {
      return (V)(if (key == null) null else (getCore$volatile$FU().get(this) as ConcurrentWeakMap.Core).getImpl((K)key));
   }

   public override fun put(key: Any, value: Any): Any? {
      var oldValue: Any = ConcurrentWeakMap.Core.putImpl$default(getCore$volatile$FU().get(this) as ConcurrentWeakMap.Core, key, value, null, 4, null);
      if (oldValue === ConcurrentWeakMapKt.access$getREHASH$p()) {
         oldValue = this.putSynchronized((K)key, (V)value);
      }

      if (oldValue == null) {
         get_size$volatile$FU().incrementAndGet(this);
      }

      return (V)oldValue;
   }

   public override fun remove(key: Any): Any? {
      if (key == null) {
         return null;
      } else {
         var oldValue: Any = ConcurrentWeakMap.Core.putImpl$default(getCore$volatile$FU().get(this) as ConcurrentWeakMap.Core, key, null, null, 4, null);
         if (oldValue === ConcurrentWeakMapKt.access$getREHASH$p()) {
            oldValue = this.putSynchronized((K)key, null);
         }

         if (oldValue != null) {
            get_size$volatile$FU().decrementAndGet(this);
         }

         return (V)oldValue;
      }
   }

   @Synchronized
   private fun putSynchronized(key: Any, value: Any?): Any? {
      var curCore: ConcurrentWeakMap.Core = getCore$volatile$FU().get(this) as ConcurrentWeakMap.Core;

      while (true) {
         val oldValue: Any = ConcurrentWeakMap.Core.putImpl$default(curCore, key, value, null, 4, null);
         if (oldValue != ConcurrentWeakMapKt.access$getREHASH$p()) {
            return (V)oldValue;
         }

         curCore = curCore.rehash();
         getCore$volatile$FU().set(this, curCore);
      }
   }

   public override fun clear() {
      for (Object k : this.keySet()) {
         this.remove(k);
      }
   }

   public fun runWeakRefQueueCleaningLoopUntilInterrupted() {
      if (this.weakRefQueue == null) {
         throw new IllegalStateException("Must be created with weakRefQueue = true".toString());
      } else {
         try {
            while (true) {
               val var10001: Reference = this.weakRefQueue.remove();
               this.cleanWeakRef(var10001 as HashedWeakRef<?>);
            }
         } catch (var3: InterruptedException) {
            Thread.currentThread().interrupt();
         }
      }
   }

   private fun cleanWeakRef(w: HashedWeakRef<*>) {
      (getCore$volatile$FU().get(this) as ConcurrentWeakMap.Core).cleanWeakRef(w);
   }

   @JvmStatic
   fun `_get_keys_$lambda$0`(k: Any, var1: Any): Any {
      return k;
   }

   @JvmStatic
   fun `_get_entries_$lambda$1`(k: Any, v: Any): java.util.Map.Entry {
      return new ConcurrentWeakMap.Entry<>(k, v);
   }

   fun ConcurrentWeakMap() {
      this(false, 1, null);
   }

   private inner class Core(allocated: Int) {
      private final val allocated: Int
      private final val shift: Int
      private final val threshold: Int
      private final val load: AtomicInt
      private final val keys: AtomicArray<HashedWeakRef<Any>?>
      private final val values: AtomicArray<Any?>

      init {
         this.this$0 = `this$0`;
         this.allocated = allocated;
         this.shift = Integer.numberOfLeadingZeros(this.allocated) + 1;
         this.threshold = 2 * this.allocated / 3;
         this.keys = new AtomicReferenceArray(this.allocated);
         this.values = new AtomicReferenceArray(this.allocated);
      }

      private fun index(hash: Int): Int {
         return hash * -1640531527 ushr this.shift;
      }

      public fun getImpl(key: Any): Any? {
         var index: Int = this.index(key.hashCode());

         while (true) {
            val var10000: HashedWeakRef = this.getKeys().get(index) as HashedWeakRef;
            if (var10000 == null) {
               return null;
            }

            val k: Any = var10000.get();
            if (key == k) {
               val value: Any = this.getValues().get(index);
               return (V)(if (value is Marked) (value as Marked).ref else value);
            }

            if (k == null) {
               this.removeCleanedAt(index);
            }

            if (index == 0) {
               index = this.allocated;
            }

            index--;
         }
      }

      private fun removeCleanedAt(index: Int) {
         val var10000: Any;
         do {
            var10000 = this.getValues().get(index);
            if (var10000 == null) {
               return;
            }

            if (var10000 is Marked) {
               return;
            }
         } while (!this.getValues().compareAndSet(index, var10000, null));

         ConcurrentWeakMap.access$decrementSize(this.this$0);
      }

      public fun putImpl(key: Any, value: Any?, weakKey0: HashedWeakRef<Any>? = null): Any? {
         var index: Int = this.index(key.hashCode());
         var loadIncremented: Boolean = false;
         var weakKey: HashedWeakRef = weakKey0;

         while (true) {
            val oldValue: HashedWeakRef = this.getKeys().get(index) as HashedWeakRef;
            if (oldValue == null) {
               if (value == null) {
                  return null;
               }

               if (!loadIncremented) {
                  val `handler$atomicfu$iv`: AtomicIntegerFieldUpdater = getLoad$volatile$FU();

                  val var10: Int;
                  do {
                     var10 = `handler$atomicfu$iv`.get(this);
                     if (var10 >= this.threshold) {
                        return ConcurrentWeakMapKt.access$getREHASH$p();
                     }
                  } while (!handler$atomicfu$iv.compareAndSet(this, var10, var10 + 1));

                  loadIncremented = true;
               }

               if (weakKey == null) {
                  weakKey = new HashedWeakRef<>(key, ConcurrentWeakMap.access$getWeakRefQueue$p(this.this$0));
               }

               if (this.getKeys().compareAndSet(index, null, weakKey)) {
                  break;
               }
            } else {
               val k: Any = oldValue.get();
               if (key == k) {
                  if (loadIncremented) {
                     getLoad$volatile$FU().decrementAndGet(this);
                  }
                  break;
               }

               if (k == null) {
                  this.removeCleanedAt(index);
               }

               if (index == 0) {
                  index = this.allocated;
               }

               index--;
            }
         }

         val var14: Any;
         do {
            var14 = this.getValues().get(index);
            if (var14 is Marked) {
               return ConcurrentWeakMapKt.access$getREHASH$p();
            }
         } while (!this.getValues().compareAndSet(index, var14, value));

         return var14;
      }

      public fun rehash(): kotlinx.coroutines.debug.internal.ConcurrentWeakMap.Core {
         label53:
         while (true) {
            val newCore: ConcurrentWeakMap.Core = this.this$0.new Core(
               (int)this.this$0, Integer.highestOneBit(RangesKt.coerceAtLeast(this.this$0.size(), 4)) * 4
            );
            var index: Int = 0;

            for (int var4 = this.allocated; index < var4; index++) {
               val w: HashedWeakRef = this.getKeys().get(index) as HashedWeakRef;
               val k: Any = if (w != null) w.get() else null;
               if (w != null && k == null) {
                  this.removeCleanedAt(index);
               }

               var var11: Any;
               do {
                  var11 = this.getValues().get(index);
                  if (var11 is Marked) {
                     var11 = (var11 as Marked).ref;
                     break;
                  }
               } while (!this.getValues().compareAndSet(index, var11, ConcurrentWeakMapKt.access$mark(var11)));

               if (k != null && var11 != null) {
                  val oldValue: Any = newCore.putImpl(k, (HashedWeakRef)var11, w);
                  if (oldValue === ConcurrentWeakMapKt.access$getREHASH$p()) {
                     continue label53;
                  }

                  if (_Assertions.ENABLED && oldValue != null) {
                     throw new AssertionError("Assertion failed");
                  }
               }
            }

            return newCore;
         }
      }

      public fun cleanWeakRef(weakRef: HashedWeakRef<*>) {
         var index: Int = this.index(weakRef.hash);

         while (true) {
            val var10000: HashedWeakRef = this.getKeys().get(index) as HashedWeakRef;
            if (var10000 == null) {
               return;
            }

            if (var10000 === weakRef) {
               this.removeCleanedAt(index);
               return;
            }

            if (index == 0) {
               index = this.allocated;
            }

            index--;
         }
      }

      public fun <E> keyValueIterator(factory: (Any, Any) -> E): MutableIterator<E> {
         return new ConcurrentWeakMap.Core.KeyValueIterator(this, factory);
      }

      @SourceDebugExtension(["SMAP\nConcurrentWeakMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ConcurrentWeakMap.kt\nkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core$KeyValueIterator\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,280:1\n1#2:281\n*E\n"])
      private inner class KeyValueIterator<E>(factory: (Any, Any) -> Any) : java.util.Iterator<E>, KMutableIterator {
         private final val factory: (Any, Any) -> Any
         private final var index: Int
         private final lateinit var key: Any
         private final lateinit var value: Any

         init {
            this.this$0 = `this$0`;
            this.factory = factory;
            this.index = -1;
            this.findNext();
         }

         private fun findNext() {
            while (true) {
               this.index++;
               if (this.index >= ConcurrentWeakMap.Core.access$getAllocated$p(this.this$0)) {
                  return;
               }

               val var10001: HashedWeakRef = ConcurrentWeakMap.Core.access$getKeys(this.this$0).get(this.index) as HashedWeakRef;
               if (var10001 != null) {
                  val var2: Any = var10001.get();
                  if (var2 != null) {
                     this.key = (K)var2;
                     var value: Any = ConcurrentWeakMap.Core.access$getValues(this.this$0).get(this.index);
                     if (value is Marked) {
                        value = (value as Marked).ref;
                     }

                     if (value != null) {
                        this.value = (V)value;
                        return;
                     }
                  }
               }
            }
         }

         public override operator fun hasNext(): Boolean {
            return this.index < ConcurrentWeakMap.Core.access$getAllocated$p(this.this$0);
         }

         public override operator fun next(): Any {
            if (this.index >= ConcurrentWeakMap.Core.access$getAllocated$p(this.this$0)) {
               throw new NoSuchElementException();
            } else {
               var var10001: Any = this.key;
               if (this.key == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("key");
                  var10001 = Unit.INSTANCE;
               }

               var var10002: Any = this.value;
               if (this.value == null) {
                  Intrinsics.throwUninitializedPropertyAccessException("value");
                  var10002 = Unit.INSTANCE;
               }

               val var1: Any = this.factory.invoke((K)var10001, (V)var10002);
               this.findNext();
               return (E)var1;
            }
         }

         public open fun remove(): Nothing {
            ConcurrentWeakMapKt.access$noImpl();
            throw new KotlinNothingValueException();
         }
      }
   }

   private class Entry<K, V>(key: Any, value: Any) : java.util.Map.Entry<K, V>, KMutableMap.Entry {
      public open val key: Any
      public open val value: Any

      init {
         this.key = (K)key;
         this.value = (V)value;
      }

      public override fun setValue(newValue: Any): Any {
         ConcurrentWeakMapKt.access$noImpl();
         throw new KotlinNothingValueException();
      }
   }

   private inner class KeyValueSet<E>(factory: (Any, Any) -> Any) : AbstractMutableSet<E> {
      private final val factory: (Any, Any) -> Any

      public open val size: Int
         public open get() {
            return this.this$0.size();
         }


      init {
         this.this$0 = `this$0`;
         this.factory = factory;
      }

      public override fun add(element: Any): Boolean {
         ConcurrentWeakMapKt.access$noImpl();
         throw new KotlinNothingValueException();
      }

      public override operator fun iterator(): MutableIterator<Any> {
         return (ConcurrentWeakMap.access$getCore$volatile$FU().get(this.this$0) as ConcurrentWeakMap.Core).keyValueIterator(this.factory);
      }
   }
}
