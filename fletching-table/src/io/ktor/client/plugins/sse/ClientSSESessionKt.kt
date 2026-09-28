@file:SourceDebugExtension(["SMAP\nClientSSESession.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClientSSESession.kt\nio/ktor/client/plugins/sse/ClientSSESessionKt\n+ 2 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,250:1\n148#1,2:260\n69#2:251\n84#2,8:252\n69#2:262\n84#2,8:263\n*S KotlinDebug\n*F\n+ 1 ClientSSESession.kt\nio/ktor/client/plugins/sse/ClientSSESessionKt\n*L\n187#1:260,2\n149#1:251\n149#1:252,8\n187#1:262\n187#1:263,8\n*E\n"])

package io.ktor.client.plugins.sse

import io.ktor.sse.TypedServerSentEvent
import io.ktor.util.reflect.TypeInfo
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType

@JvmSynthetic
public inline fun <reified T> SSESessionWithDeserialization.deserialize(data: String?): Any? {
   var var14: Any;
   if (data != null) {
      val it: java.lang.String = data;
      val var5: Function2 = `$this$deserialize`.getDeserializer();
      Intrinsics.reifiedOperationMarker(4, "T");

      var var9: KType;
      try {
         Intrinsics.reifiedOperationMarker(6, "T");
         var9 = null;
      } catch (var13: java.lang.Throwable) {
         var9 = null as KType;
      }

      var14 = var5.invoke(new TypeInfo(Any::class, var9), data);
      Intrinsics.reifiedOperationMarker(2, "T");
      var14 = var14;
   } else {
      var14 = null;
   }

   return (T)var14;
}

@JvmSynthetic
public inline fun <reified T> SSESessionWithDeserialization.deserialize(event: TypedServerSentEvent<String>): Any? {
   val `data$iv`: java.lang.String = event.getData() as java.lang.String;
   var var17: Any;
   if (`data$iv` != null) {
      val `it$iv`: java.lang.String = `data$iv`;
      val var8: Function2 = `$this$deserialize`.getDeserializer();
      Intrinsics.reifiedOperationMarker(4, "T?");

      var var12: KType;
      try {
         Intrinsics.reifiedOperationMarker(6, "T?");
         var12 = null;
      } catch (var16: java.lang.Throwable) {
         var12 = null as KType;
      }

      var17 = var8.invoke(new TypeInfo(Any::class, var12), `data$iv`);
      Intrinsics.reifiedOperationMarker(2, "T?");
      var17 = var17;
   } else {
      var17 = null;
   }

   return (T)var17;
}
