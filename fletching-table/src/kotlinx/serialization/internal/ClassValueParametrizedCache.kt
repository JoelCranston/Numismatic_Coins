package kotlinx.serialization.internal

import java.util.ArrayList
import java.util.concurrent.ConcurrentMap
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.ClassValueParametrizedCache.get-gIAlu-s..inlined.getOrSet.1

@SourceDebugExtension(["SMAP\nCaching.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ClassValueParametrizedCache\n+ 2 Caching.kt\nkotlinx/serialization/internal/ClassValueReferences\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Caching.kt\nkotlinx/serialization/internal/ParametrizedCacheEntry\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n*L\n1#1,219:1\n84#2,3:220\n89#2:224\n1#3:223\n1#3:234\n212#4:225\n213#4:230\n214#4:233\n1563#5:226\n1634#5,3:227\n72#6,2:231\n*S KotlinDebug\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ClassValueParametrizedCache\n*L\n128#1:220,3\n128#1:224\n128#1:223\n129#1:234\n129#1:225\n129#1:230\n129#1:233\n129#1:226\n129#1:227,3\n129#1:231,2\n*E\n"])
private class ClassValueParametrizedCache<T>(compute: (KClass<Any>, List<KType>) -> KSerializer<Any>?) : ParametrizedSerializerCache<T> {
   private final val compute: (KClass<Any>, List<KType>) -> KSerializer<Any>?
   private final val classValue: ClassValueReferences<ParametrizedCacheEntry<Any>>

   init {
      this.compute = compute;
      this.classValue = new ClassValueReferences<>();
   }

   public override fun get(key: KClass<Any>, types: List<KType>): Result<KSerializer<Any>?> {
      var var10000: Any = this.classValue.get(JvmClassMappingKt.getJavaClass(key));
      val var30: Any = (var10000 as MutableSoftReference).reference.get();
      val var19: ParametrizedCacheEntry = (var30 ?: (var10000 as MutableSoftReference).getOrSetWithLock(new 1())) as ParametrizedCacheEntry;
      val var21: java.lang.Iterable = types;
      val `$i$f$getOrPut`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(types, 10));

      for (Object item$iv$iv$iv : $this$map$iv$iv) {
         `$i$f$getOrPut`.add(new KTypeWrapper(`item$iv$iv$iv` as KType));
      }

      val `wrappedTypes$iv`: java.util.List = `$i$f$getOrPut` as java.util.List;
      val var23: ConcurrentMap = ParametrizedCacheEntry.access$getSerializers$p(var19);
      var10000 = var23.get(`wrappedTypes$iv`);
      if (var10000 == null) {
         var var26: Any;
         try {
            var26 = Result.constructor-impl(this.compute.invoke(key, types));
         } catch (var18: java.lang.Throwable) {
            var26 = Result.constructor-impl(ResultKt.createFailure(var18));
         }

         val var28: Any = Result.box-impl(var26);
         var10000 = var23.putIfAbsent(`wrappedTypes$iv`, var28);
         if (var10000 == null) {
            var10000 = var28;
         }
      }

      return (var10000 as Result).unbox-impl();
   }
}
