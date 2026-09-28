package kotlinx.serialization.internal

import java.lang.ref.SoftReference
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nCaching.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/MutableSoftReference\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,219:1\n1#2:220\n*E\n"])
private class MutableSoftReference<T> {
   public final var reference: SoftReference<Any> = new SoftReference(null)
      private set

   @Synchronized
   public fun getOrSetWithLock(factory: () -> Any): Any {
      val var10000: Any = this.reference.get();
      if (var10000 != null) {
         return (T)var10000;
      } else {
         val value: Any = factory.invoke();
         this.reference = new SoftReference<>((T)value);
         return (T)value;
      }
   }
}
