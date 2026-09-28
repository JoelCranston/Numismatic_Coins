package io.ktor.utils.io.pool

public abstract class NoPoolImpl<T> : ObjectPool<T> {
   public open val capacity: Int
      public open get() {
         return 0;
      }


   public override fun recycle(instance: Any) {
   }

   public override fun dispose() {
   }
}
