package kotlinx.serialization.internal

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlinx.serialization.KSerializer

@SourceDebugExtension(["SMAP\nCaching.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ConcurrentHashMapCache\n+ 2 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,219:1\n72#2,2:220\n1#3:222\n*S KotlinDebug\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ConcurrentHashMapCache\n*L\n142#1:220,2\n142#1:222\n*E\n"])
private class ConcurrentHashMapCache<T>(compute: (KClass<*>) -> KSerializer<Any>?) : SerializerCache<T> {
   private final val compute: (KClass<*>) -> KSerializer<Any>?
   private final val cache: ConcurrentHashMap<Class<*>, CacheEntry<Any>>

   init {
      this.compute = compute;
      this.cache = new ConcurrentHashMap<>();
   }

   public override fun get(key: KClass<Any>): KSerializer<Any>? {
      val `$this$getOrPut$iv`: ConcurrentMap = this.cache;
      val `key$iv`: Any = JvmClassMappingKt.getJavaClass(key);
      var var10000: Any = `$this$getOrPut$iv`.get(`key$iv`);
      if (var10000 == null) {
         val `default$iv`: Any = new CacheEntry<>(this.compute.invoke(key));
         var10000 = `$this$getOrPut$iv`.putIfAbsent(`key$iv`, `default$iv`);
         if (var10000 == null) {
            var10000 = `default$iv`;
         }
      }

      return (var10000 as CacheEntry).serializer;
   }

   public override fun isStored(key: KClass<*>): Boolean {
      return this.cache.containsKey(JvmClassMappingKt.getJavaClass(key));
   }
}
