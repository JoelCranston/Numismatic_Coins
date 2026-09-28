package kotlinx.serialization.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.ClassValueCache.get..inlined.getOrSet.1

@SourceDebugExtension(["SMAP\nCaching.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ClassValueCache\n+ 2 Caching.kt\nkotlinx/serialization/internal/ClassValueReferences\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,219:1\n84#2,3:220\n89#2:224\n1#3:223\n*S KotlinDebug\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ClassValueCache\n*L\n52#1:220,3\n52#1:224\n52#1:223\n*E\n"])
private class ClassValueCache<T>(compute: (KClass<*>) -> KSerializer<Any>?) : SerializerCache<T> {
   public final val compute: (KClass<*>) -> KSerializer<Any>?
   private final val classValue: ClassValueReferences<CacheEntry<Any>>

   init {
      this.compute = compute;
      this.classValue = new ClassValueReferences<>();
   }

   public override fun get(key: KClass<Any>): KSerializer<Any>? {
      val var10000: Any = this.classValue.get(JvmClassMappingKt.getJavaClass(key));
      val var8: Any = (var10000 as MutableSoftReference).reference.get();
      return ((var8 ?: (var10000 as MutableSoftReference).getOrSetWithLock(new 1(this, key))) as CacheEntry).serializer;
   }

   public override fun isStored(key: KClass<*>): Boolean {
      return this.classValue.isStored(JvmClassMappingKt.getJavaClass(key));
   }
}
