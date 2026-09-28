@file:SourceDebugExtension(["SMAP\nSerializersCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerializersCache.kt\nkotlinx/serialization/SerializersCacheKt\n+ 2 Platform.common.kt\nkotlinx/serialization/internal/Platform_commonKt\n*L\n1#1,79:1\n78#1:81\n78#1:82\n78#2:80\n78#2:83\n78#2:84\n*S KotlinDebug\n*F\n+ 1 SerializersCache.kt\nkotlinx/serialization/SerializersCacheKt\n*L\n22#1:81\n28#1:82\n54#1:80\n28#1:83\n45#1:84\n*E\n"])

package kotlinx.serialization

import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlin.reflect.KClassifier
import kotlin.reflect.KType
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.internal.CachingKt
import kotlinx.serialization.internal.ParametrizedSerializerCache
import kotlinx.serialization.internal.PlatformKt
import kotlinx.serialization.internal.SerializerCache
import kotlinx.serialization.modules.SerializersModuleBuildersKt

internal final val SERIALIZERS_CACHE: SerializerCache<out Any> = CachingKt.createCache(SerializersCacheKt::SERIALIZERS_CACHE$lambda$0)
private final val SERIALIZERS_CACHE_NULLABLE: SerializerCache<Any?> = CachingKt.createCache(SerializersCacheKt::SERIALIZERS_CACHE_NULLABLE$lambda$1)
private final val PARAMETRIZED_SERIALIZERS_CACHE: ParametrizedSerializerCache<out Any> =
   CachingKt.createParametrizedCache(SerializersCacheKt::PARAMETRIZED_SERIALIZERS_CACHE$lambda$3)
   private final val PARAMETRIZED_SERIALIZERS_CACHE_NULLABLE: ParametrizedSerializerCache<Any?> =
   CachingKt.createParametrizedCache(SerializersCacheKt::PARAMETRIZED_SERIALIZERS_CACHE_NULLABLE$lambda$5)

internal fun findCachedSerializer(clazz: KClass<Any>, isNullable: Boolean): KSerializer<Any?>? {
   var var4: KSerializer;
   if (!isNullable) {
      var4 = SERIALIZERS_CACHE.get(clazz);
      var4 = var4 ?: null;
   } else {
      var4 = SERIALIZERS_CACHE_NULLABLE.get(clazz);
   }

   return var4;
}

internal fun findParametrizedCachedSerializer(clazz: KClass<Any>, types: List<KType>, isNullable: Boolean): Result<KSerializer<Any?>?> {
   return if (!isNullable) PARAMETRIZED_SERIALIZERS_CACHE.get-gIAlu-s(clazz, types) else PARAMETRIZED_SERIALIZERS_CACHE_NULLABLE.get-gIAlu-s(clazz, types);
}

internal inline fun KClass<*>.polymorphicIfInterface(): PolymorphicSerializer<out Any>? {
   return if (PlatformKt.isInterface(`$this$polymorphicIfInterface`)) new PolymorphicSerializer<>(`$this$polymorphicIfInterface`) else null;
}

fun `SERIALIZERS_CACHE$lambda$0`(it: KClass): KSerializer {
   var var10000: KSerializer = SerializersKt.serializerOrNull(it);
   if (var10000 == null) {
      var10000 = if (PlatformKt.isInterface(it)) new PolymorphicSerializer(it) else null;
   }

   return var10000;
}

fun `SERIALIZERS_CACHE_NULLABLE$lambda$1`(it: KClass): KSerializer {
   var var10000: KSerializer = SerializersKt.serializerOrNull(it);
   if (var10000 == null) {
      var10000 = if (PlatformKt.isInterface(it)) new PolymorphicSerializer(it) else null;
   }

   if (var10000 != null) {
      var10000 = BuiltinSerializersKt.getNullable(var10000);
      if (var10000 != null) {
         return var10000;
      }
   }

   return null;
}

fun `PARAMETRIZED_SERIALIZERS_CACHE$lambda$3$lambda$2`(`$types`: java.util.List): KClassifier {
   return (`$types`.get(0) as KType).getClassifier();
}

fun `PARAMETRIZED_SERIALIZERS_CACHE$lambda$3`(clazz: KClass, types: java.util.List): KSerializer {
   val var10000: java.util.List = SerializersKt.serializersForParameters(SerializersModuleBuildersKt.EmptySerializersModule(), types, true);
   return SerializersKt.parametrizedSerializerOrNull(clazz, var10000, SerializersCacheKt::PARAMETRIZED_SERIALIZERS_CACHE$lambda$3$lambda$2);
}

fun `PARAMETRIZED_SERIALIZERS_CACHE_NULLABLE$lambda$5$lambda$4`(`$types`: java.util.List): KClassifier {
   return (`$types`.get(0) as KType).getClassifier();
}

fun `PARAMETRIZED_SERIALIZERS_CACHE_NULLABLE$lambda$5`(clazz: KClass, types: java.util.List): KSerializer {
   val var10000: java.util.List = SerializersKt.serializersForParameters(SerializersModuleBuildersKt.EmptySerializersModule(), types, true);
   val var5: KSerializer = SerializersKt.parametrizedSerializerOrNull(
      clazz, var10000, SerializersCacheKt::PARAMETRIZED_SERIALIZERS_CACHE_NULLABLE$lambda$5$lambda$4
   );
   if (var5 != null) {
      val var6: KSerializer = BuiltinSerializersKt.getNullable(var5);
      if (var6 != null) {
         return var6;
      }
   }

   return null;
}
