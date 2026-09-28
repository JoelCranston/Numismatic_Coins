package io.ktor.utils.io.pool

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nPool.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Pool.kt\nio/ktor/utils/io/pool/SingleInstancePool\n+ 2 AtomicFU.common.kt\nkotlinx/atomicfu/AtomicFU_commonKt\n*L\n1#1,189:1\n360#2,4:190\n*S KotlinDebug\n*F\n+ 1 Pool.kt\nio/ktor/utils/io/pool/SingleInstancePool\n*L\n87#1:190,4\n*E\n"])
public abstract class SingleInstancePool<T> : ObjectPool<T> {
   public final val capacity: Int
      public final get() {
         return 1;
      }


   protected abstract fun produceInstance(): Any {
   }

   protected abstract fun disposeInstance(instance: Any) {
   }

   public override fun borrow(): Any {
      var instance: SingleInstancePool = this;

      do {
         if (instance.borrowed != 0) {
            throw new IllegalStateException("Instance is already consumed".toString());
         }
      } while (!borrowed$FU.compareAndSet($this$update$iv, $this$update$iv.borrowed, 1));

      instance = this.produceInstance();
      this.instance = instance;
      return (T)instance;
   }

   public override fun recycle(instance: Any) {
      if (this.instance != instance) {
         if (this.instance == null && this.borrowed != 0) {
            throw new IllegalStateException("Already recycled or an irrelevant instance tried to be recycled".toString());
         } else {
            throw new IllegalStateException("Unable to recycle irrelevant instance".toString());
         }
      } else {
         this.instance = null;
         if (!disposed$FU.compareAndSet(this, 0, 1)) {
            throw new IllegalStateException("An instance is already disposed".toString());
         } else {
            this.disposeInstance((T)instance);
         }
      }
   }

   public override fun dispose() {
      if (disposed$FU.compareAndSet(this, 0, 1)) {
         if (this.instance == null) {
            return;
         }

         val value: Any = this.instance;
         this.instance = null;
         this.disposeInstance((T)value);
      }
   }
}
