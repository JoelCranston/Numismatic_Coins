package kotlinx.coroutines.flow

internal class ThrowingCollector(e: Throwable) : FlowCollector<Object> {
   public final val e: Throwable

   init {
      this.e = e;
   }

   public override suspend fun emit(value: Any?) {
      throw this.e;
   }
}
