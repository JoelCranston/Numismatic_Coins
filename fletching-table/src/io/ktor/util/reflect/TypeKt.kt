@file:SourceDebugExtension(["SMAP\nType.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Type.kt\nio/ktor/util/reflect/TypeKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,92:1\n84#1,8:93\n1#2:101\n*S KotlinDebug\n*F\n+ 1 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n69#1:93,8\n*E\n"])

package io.ktor.util.reflect

import io.ktor.utils.io.InternalAPI
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializersKt

@JvmSynthetic
public inline fun <reified T> typeInfo(): TypeInfo {
   Intrinsics.reifiedOperationMarker(4, "T");

   var var2: KType;
   try {
      Intrinsics.reifiedOperationMarker(6, "T");
      var2 = null;
   } catch (var7: java.lang.Throwable) {
      var2 = null as KType;
   }

   return new TypeInfo(Any::class, var2);
}

@InternalAPI
public fun TypeInfo.serializer(): KSerializer<out Any?> {
   val var10000: KType = `$this$serializer`.getKotlinType();
   if (var10000 != null) {
      val var3: KSerializer = SerializersKt.serializer(var10000);
      if (var3 != null) {
         return var3;
      }
   }

   return SerializersKt.serializer((KClass<? extends Object>)`$this$serializer`.getType());
}

@PublishedApi
@JvmSynthetic
internal inline fun <reified T> typeOfOrNull(): KType? {
   var var1: KType;
   try {
      Intrinsics.reifiedOperationMarker(6, "T");
      var1 = null;
   } catch (var3: java.lang.Throwable) {
      var1 = null as KType;
   }

   return var1;
}
