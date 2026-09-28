@file:SourceDebugExtension(["SMAP\nRequestBody.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RequestBody.kt\nio/ktor/client/request/RequestBodyKt\n+ 2 Type.kt\nio/ktor/util/reflect/TypeKt\n+ 3 Attributes.kt\nio/ktor/util/AttributesKt\n*L\n1#1,37:1\n69#2:38\n84#2,8:39\n69#2:47\n84#2,8:48\n69#2:57\n84#2,8:58\n21#3:56\n*S KotlinDebug\n*F\n+ 1 RequestBody.kt\nio/ktor/client/request/RequestBodyKt\n*L\n19#1:38\n19#1:39,8\n27#1:47\n27#1:48,8\n12#1:57\n12#1:58,8\n12#1:56\n*E\n"])

package io.ktor.client.request

import io.ktor.http.content.NullBody
import io.ktor.http.content.OutgoingContent
import io.ktor.util.AttributeKey
import io.ktor.util.reflect.TypeInfo
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType

internal final val BodyTypeAttributeKey: AttributeKey<TypeInfo>

@JvmSynthetic
public inline fun <reified T> HttpRequestBuilder.setBody(body: Any) {
   if (body == null) {
      `$this$setBody`.setBody(NullBody.INSTANCE);
      Intrinsics.reifiedOperationMarker(4, "T");

      var var7: KType;
      try {
         Intrinsics.reifiedOperationMarker(6, "T");
         var7 = null;
      } catch (var13: java.lang.Throwable) {
         var7 = null as KType;
      }

      `$this$setBody`.setBodyType(new TypeInfo(Any::class, var7));
   } else if (body is OutgoingContent) {
      `$this$setBody`.setBody(body);
      `$this$setBody`.setBodyType(null);
   } else {
      `$this$setBody`.setBody(body);
      Intrinsics.reifiedOperationMarker(4, "T");

      var var17: KType;
      try {
         Intrinsics.reifiedOperationMarker(6, "T");
         var17 = null;
      } catch (var12: java.lang.Throwable) {
         var17 = null as KType;
      }

      `$this$setBody`.setBodyType(new TypeInfo(Any::class, var17));
   }
}

public fun HttpRequestBuilder.setBody(body: Any?, bodyType: TypeInfo) {
   var var10001: Any = body;
   if (body == null) {
      var10001 = NullBody.INSTANCE;
   }

   `$this$setBody`.setBody(var10001);
   `$this$setBody`.setBodyType(bodyType);
}
