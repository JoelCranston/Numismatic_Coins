package io.ktor.utils.io.pool

import java.util.concurrent.atomic.AtomicReferenceArray
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nDefaultPool.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultPool.kt\nio/ktor/utils/io/pool/DefaultPool\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,111:1\n1#2:112\n*E\n"])
public abstract class DefaultPool<T> : ObjectPool<T> {
   public final val capacity: Int
   private final val maxIndex: Int
   private final val shift: Int
   private final val instances: AtomicReferenceArray<Any?>
   private final val next: IntArray

   open fun DefaultPool(capacity: Int) {
      this.capacity = capacity;
      if (this.capacity <= 0) {
         throw new IllegalArgumentException(("capacity should be positive but it is ${this.capacity}").toString());
      } else if (this.capacity > 536870911) {
         throw new IllegalArgumentException(("capacity should be less or equal to 536870911 but it is ${this.capacity}").toString());
      } else {
         this.top = 0L;
         this.maxIndex = Integer.highestOneBit(this.capacity * 4 - 1) * 2;
         this.shift = Integer.numberOfLeadingZeros(this.maxIndex) + 1;
         this.instances = new AtomicReferenceArray<>(this.maxIndex + 1);
         this.next = new int[this.maxIndex + 1];
      }
   }

   protected abstract fun produceInstance(): Any {
   }

   protected open fun clearInstance(instance: Any): Any {
      return (T)instance;
   }

   protected open fun validateInstance(instance: Any) {
   }

   protected open fun disposeInstance(instance: Any) {
   }

   public override fun borrow(): Any {
      var var10000: Any = this.tryPop();
      if (var10000 != null) {
         var10000 = this.clearInstance((T)var10000);
         if (var10000 != null) {
            return (T)var10000;
         }
      }

      return this.produceInstance();
   }

   public override fun recycle(instance: Any) {
      this.validateInstance((T)instance);
      if (!this.tryPush((T)instance)) {
         this.disposeInstance((T)instance);
      }
   }

   public override fun dispose() {
      while (true) {
         val var10000: Any = this.tryPop();
         if (var10000 == null) {
            return;
         }

         this.disposeInstance((T)var10000);
      }
   }

   private fun tryPush(instance: Any): Boolean {
      var var7: Int = (System.identityHashCode(instance) * -1640531527 ushr this.shift) + 1;
      val var3: Byte = 8;

      for (int var4 = 0; var4 < var3; var4++) {
         if (this.instances.compareAndSet(var7, null, (T)instance)) {
            this.pushTop(var7);
            return true;
         }

         var7 += -1;
         if (var7 == 0) {
            var7 = this.maxIndex;
         }
      }

      return false;
   }

   private fun tryPop(): Any? {
      val index: Int = this.popTop();
      return if (index == 0) null else this.instances.getAndSet(index, null);
   }

   private fun pushTop(index: Int) {
      if (index <= 0) {
         throw new IllegalArgumentException("index should be positive".toString());
      } else {
         val newTop: Long;
         do {
            val topVersion: Long = (this.top shr 32 and 4294967295L) + 1L;
            val topIndex: Int = (int)(this.top and 4294967295L);
            newTop = topVersion shl 32 or index;
            this.next[index] = topIndex;
         } while (!top$FU.compareAndSet(this, this.top, newTop));
      }
   }

   private fun popTop(): Int {
      val newVersion: Long;
      val topIndex: Int;
      do {
         if (this.top == 0L) {
            return 0;
         }

         newVersion = (this.top shr 32 and 4294967295L) + 1L;
         topIndex = (int)(this.top and 4294967295L);
         if ((int)(this.top and 4294967295L) == 0) {
            return 0;
         }
      } while (!top$FU.compareAndSet(this, this.top, newVersion << 32 | this.next[topIndex]));

      return topIndex;
   }
}
