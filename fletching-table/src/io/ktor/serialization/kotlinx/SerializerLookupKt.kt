@file:SourceDebugExtension(["SMAP\nSerializerLookup.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SerializerLookup.kt\nio/ktor/serialization/kotlinx/SerializerLookupKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,85:1\n1#2:86\n1563#3:87\n1634#3,3:88\n1669#3,8:91\n1563#3:99\n1634#3,3:100\n1761#3,3:103\n*S KotlinDebug\n*F\n+ 1 SerializerLookup.kt\nio/ktor/serialization/kotlinx/SerializerLookupKt\n*L\n61#1:87\n61#1:88,3\n61#1:91,8\n66#1:99\n66#1:100,3\n79#1:103,3\n*E\n"])

package io.ktor.serialization.kotlinx

import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.InternalAPI
import java.util.ArrayList
import java.util.HashSet
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.StringCompanionObject
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializersKt
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.modules.SerializersModule

@InternalSerializationApi
@ExperimentalSerializationApi
public fun SerializersModule.serializerForTypeInfo(typeInfo: TypeInfo): KSerializer<*> {
   val var3: KType = typeInfo.getKotlinType();
   if (var3 != null) {
      val var4: KSerializer = if (var3.getArguments().isEmpty()) null else SerializersKt.serializerOrNull(`$this$serializerForTypeInfo`, var3);
      if (var4 != null) {
         return var4;
      }
   }

   val var10000: KSerializer = SerializersModule.getContextual$default(`$this$serializerForTypeInfo`, typeInfo.getType(), null, 2, null);
   return if (var10000 != null) maybeNullable(var10000, typeInfo) else maybeNullable(SerializersKt.serializer(typeInfo.getType()), typeInfo);
}

private fun <T : Any> KSerializer<Any>.maybeNullable(typeInfo: TypeInfo): KSerializer<*> {
   val var10000: KType = typeInfo.getKotlinType();
   return if (var10000 != null && var10000.isMarkedNullable()) BuiltinSerializersKt.getNullable(`$this$maybeNullable`) else `$this$maybeNullable`;
}

@InternalAPI
public fun guessSerializer(value: Any?, module: SerializersModule): KSerializer<Any> {
   var var10000: KSerializer;
   if (value == null) {
      var10000 = BuiltinSerializersKt.getNullable(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE));
   } else if (value is java.util.List) {
      var10000 = BuiltinSerializersKt.ListSerializer(elementSerializer(value as MutableCollection<*>, module));
   } else {
      label26:
      if (value is Array<Any>) {
         val var8: Any = ArraysKt.firstOrNull(value as Array<Any>);
         if (var8 != null) {
            var10000 = guessSerializer(var8, module);
            if (var10000 != null) {
               break label26;
            }
         }

         var10000 = BuiltinSerializersKt.ListSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE));
      } else if (value is java.util.Set) {
         var10000 = BuiltinSerializersKt.SetSerializer(elementSerializer(value as MutableCollection<*>, module));
      } else if (value is java.util.Map) {
         var10000 = BuiltinSerializersKt.MapSerializer(
            elementSerializer((value as java.util.Map).keySet(), module), elementSerializer((value as java.util.Map).values(), module)
         );
      } else {
         var10000 = SerializersModule.getContextual$default(module, value.getClass()::class, null, 2, null);
         if (var10000 == null) {
            var10000 = SerializersKt.serializer((KClass)(value.getClass()::class));
         }
      }
   }

   return var10000;
}

private fun Collection<*>.elementSerializer(module: SerializersModule): KSerializer<*> {
   var selected: java.lang.Iterable = CollectionsKt.filterNotNull(`$this$elementSerializer`);
   var `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(selected, 10));

   for (Object item$iv$iv : $this$map$iv) {
      `destination$iv$iv`.add(guessSerializer(var9, module));
   }

   selected = `destination$iv$iv` as java.util.List;
   val `$i$f$any`: HashSet = new HashSet();
   val var22: ArrayList = new ArrayList();

   for (Object e$iv : $this$map$iv) {
      if (`$i$f$any`.add((var28 as KSerializer).getDescriptor().getSerialName())) {
         var22.add(var28);
      }
   }

   val serializers: java.util.List = var22;
   if (var22.size() > 1) {
      val var37: StringBuilder = new StringBuilder().append("Serializing collections of different element types is not yet supported. Selected serializers: ");
      selected = serializers;
      `destination$iv$iv` = new ArrayList(CollectionsKt.collectionSizeOrDefault(serializers, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add((var33 as KSerializer).getDescriptor().getSerialName());
      }

      throw new IllegalStateException(var37.append(`destination$iv$iv` as java.util.List).toString().toString());
   } else {
      var var10000: KSerializer = CollectionsKt.singleOrNull(serializers);
      if (var10000 == null) {
         var10000 = BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE);
      }

      if (var10000.getDescriptor().isNullable()) {
         return var10000;
      } else {
         val var19: java.lang.Iterable = `$this$elementSerializer`;
         val var36: Boolean;
         if (`$this$elementSerializer` is java.util.Collection && (`$this$elementSerializer` as java.util.Collection).isEmpty()) {
            var36 = false;
         } else {
            for (Object element$iv : $this$any$iv) {
               if (var26 == null) {
                  return if (true) BuiltinSerializersKt.getNullable(var10000) else var10000;
               }
            }

            var36 = false;
         }

         return if (var36) BuiltinSerializersKt.getNullable(var10000) else var10000;
      }
   }
}
