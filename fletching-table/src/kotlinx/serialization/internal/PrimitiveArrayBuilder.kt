package kotlinx.serialization.internal

@PublishedApi
internal abstract class PrimitiveArrayBuilder<Array> {
   internal abstract val position: Int

   internal abstract fun ensureCapacity(requiredCapacity: Int = ...) {
   }

   internal abstract fun build(): Any {
   }
}
