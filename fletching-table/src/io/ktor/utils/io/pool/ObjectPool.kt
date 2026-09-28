package io.ktor.utils.io.pool

public interface ObjectPool<T> : AutoCloseable {
   public val capacity: Int

   public abstract fun borrow(): Any {
   }

   public abstract fun recycle(instance: Any) {
   }

   public abstract fun dispose() {
   }

   public override fun close() {
      this.dispose();
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun <T> close(`$this`: ObjectPool<T>) {
         ObjectPool.access$close$jd(`$this`);
      }
   }
}
