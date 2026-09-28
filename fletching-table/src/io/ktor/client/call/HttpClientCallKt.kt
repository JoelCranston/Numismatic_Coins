@file:SourceDebugExtension(["SMAP\nHttpClientCall.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpClientCall.kt\nio/ktor/client/call/HttpClientCallKt\n+ 2 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,223:1\n69#2:224\n84#2,8:225\n69#2:233\n84#2,8:234\n*S KotlinDebug\n*F\n+ 1 HttpClientCall.kt\nio/ktor/client/call/HttpClientCallKt\n*L\n151#1:224\n151#1:225,8\n162#1:233\n162#1:234,8\n*E\n"])

package io.ktor.client.call

import io.ktor.client.statement.HttpResponse
import io.ktor.util.reflect.TypeInfo
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType

@JvmSynthetic
public suspend inline fun <reified T> HttpClientCall.body(): Any {
   Intrinsics.reifiedOperationMarker(4, "T");

   var var6: KType;
   try {
      Intrinsics.reifiedOperationMarker(6, "T");
      var6 = null;
   } catch (var12: java.lang.Throwable) {
      var6 = null as KType;
   }

   val var11: TypeInfo = new TypeInfo(Any::class, var6);
   InlineMarker.mark(0);
   val var10000: Any = `$this$body`.bodyNullable(var11, `$completion`);
   InlineMarker.mark(1);
   Intrinsics.reifiedOperationMarker(1, "T");
   return var10000;
}

@JvmSynthetic
public suspend inline fun <reified T> HttpResponse.body(): Any {
   val var10: HttpClientCall = `$this$body`.getCall();
   Intrinsics.reifiedOperationMarker(4, "T");

   var var6: KType;
   try {
      Intrinsics.reifiedOperationMarker(6, "T");
      var6 = null;
   } catch (var12: java.lang.Throwable) {
      var6 = null as KType;
   }

   val var11: TypeInfo = new TypeInfo(Any::class, var6);
   InlineMarker.mark(0);
   val var10000: Any = var10.bodyNullable(var11, `$completion`);
   InlineMarker.mark(1);
   Intrinsics.reifiedOperationMarker(1, "T");
   return var10000;
}

public suspend fun <T> HttpResponse.body(typeInfo: TypeInfo): Any {
   val var10000: Any = `$this$body`.getCall().bodyNullable(typeInfo, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else var10000;
}
