package kotlinx.serialization.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.internal.ClassValueReferences.getOrSet.2

@SuppressAnimalSniffer
@SourceDebugExtension(["SMAP\nCaching.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ClassValueReferences\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,219:1\n1#2:220\n*E\n"])
private class ClassValueReferences<T> : ClassValue<MutableSoftReference<T>> {
   protected open fun computeValue(type: Class<*>): MutableSoftReference<Any> {
      return new MutableSoftReference<>();
   }

   public inline fun getOrSet(key: Class<*>, crossinline factory: () -> Any): Any {
      val var10000: Any = this.get(key);
      val var7: Any = (var10000 as MutableSoftReference).reference.get();
      return (T)(var7 ?: (var10000 as MutableSoftReference).getOrSetWithLock(new 2(factory)));
   }

   public fun isStored(key: Class<*>): Boolean {
      return this.get(key).reference.get() != null;
   }
}
