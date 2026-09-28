package kotlinx.serialization.internal

import java.util.ArrayList
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType
import kotlinx.serialization.KSerializer

@SourceDebugExtension(["SMAP\nCaching.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ParametrizedCacheEntry\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,219:1\n1563#2:220\n1634#2,3:221\n72#3,2:224\n1#4:226\n1#4:227\n*S KotlinDebug\n*F\n+ 1 Caching.kt\nkotlinx/serialization/internal/ParametrizedCacheEntry\n*L\n212#1:220\n212#1:221,3\n213#1:224,2\n213#1:227\n*E\n"])
private class ParametrizedCacheEntry<T> {
   private final val serializers: ConcurrentHashMap<List<KTypeWrapper>, Result<KSerializer<Any>?>> = new ConcurrentHashMap()

   public inline fun computeIfAbsent(types: List<KType>, producer: () -> KSerializer<Any>?): Result<KSerializer<Any>?> {
      val `$this$map$iv`: java.lang.Iterable = types;
      val `$i$f$getOrPut`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(types, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `$i$f$getOrPut`.add(new KTypeWrapper(`item$iv$iv` as KType));
      }

      val wrappedTypes: java.util.List = `$i$f$getOrPut` as java.util.List;
      val var16: ConcurrentMap = access$getSerializers$p(this);
      var var10000: Any = var16.get(wrappedTypes);
      if (var10000 == null) {
         var var19: Any;
         try {
            var19 = Result.constructor-impl(producer.invoke() as KSerializer);
         } catch (var15: java.lang.Throwable) {
            var19 = Result.constructor-impl(ResultKt.createFailure(var15));
         }

         val var21: Any = Result.box-impl(var19);
         var10000 = var16.putIfAbsent(wrappedTypes, var21);
         if (var10000 == null) {
            var10000 = var21;
         }
      }

      return (var10000 as Result).unbox-impl();
   }
}
