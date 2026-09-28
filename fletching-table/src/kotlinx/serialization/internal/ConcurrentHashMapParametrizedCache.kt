package kotlinx.serialization.internal

import java.util.ArrayList
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlinx.serialization.KSerializer

@SourceDebugExtension(["SMAP\nCaching.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ConcurrentHashMapParametrizedCache\n+ 2 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Caching.kt\nkotlinx/serialization/internal/ParametrizedCacheEntry\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,219:1\n72#2,2:220\n72#2,2:229\n1#3:222\n1#3:232\n212#4:223\n213#4:228\n214#4:231\n1563#5:224\n1634#5,3:225\n*S KotlinDebug\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ConcurrentHashMapParametrizedCache\n*L\n158#1:220,2\n159#1:229,2\n158#1:222\n159#1:232\n159#1:223\n159#1:228\n159#1:231\n159#1:224\n159#1:225,3\n*E\n"])
private class ConcurrentHashMapParametrizedCache<T>(compute: (KClass<Any>, List<KType>) -> KSerializer<Any>?) : ParametrizedSerializerCache<T> {
   private final val compute: (KClass<Any>, List<KType>) -> KSerializer<Any>?
   private final val cache: ConcurrentHashMap<Class<*>, ParametrizedCacheEntry<Any>>

   init {
      this.compute = compute;
      this.cache = new ConcurrentHashMap<>();
   }

   public override fun get(key: KClass<Any>, types: List<KType>): Result<KSerializer<Any>?> {
      val `this_$iv`: ConcurrentMap = this.cache;
      val `types$iv`: Any = JvmClassMappingKt.getJavaClass(key);
      var var10000: Any = `this_$iv`.get(`types$iv`);
      if (var10000 == null) {
         val `$this$getOrPut$iv$iv`: Any = new ParametrizedCacheEntry();
         var10000 = `this_$iv`.putIfAbsent(`types$iv`, `$this$getOrPut$iv$iv`);
         if (var10000 == null) {
            var10000 = `$this$getOrPut$iv$iv`;
         }
      }

      val var19: ParametrizedCacheEntry = var10000 as ParametrizedCacheEntry;
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
