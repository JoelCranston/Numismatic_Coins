@file:SourceDebugExtension(["SMAP\nWebsocketContentConverter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebsocketContentConverter.kt\nio/ktor/serialization/WebsocketContentConverterKt\n+ 2 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,97:1\n69#2:98\n84#2,8:99\n69#2:107\n84#2,8:108\n69#2:116\n84#2,8:117\n*S KotlinDebug\n*F\n+ 1 WebsocketContentConverter.kt\nio/ktor/serialization/WebsocketContentConverterKt\n*L\n82#1:98\n82#1:99,8\n96#1:107\n96#1:108,8\n96#1:116\n96#1:117,8\n*E\n"])

package io.ktor.serialization

import io.ktor.util.reflect.TypeInfo
import io.ktor.websocket.Frame
import java.nio.charset.Charset
import kotlin.coroutines.Continuation
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType

@JvmSynthetic
public suspend inline fun <reified T> WebsocketContentConverter.serialize(value: Any, charset: Charset = ...): Frame {
   Intrinsics.reifiedOperationMarker(4, "T");

   var var8: KType;
   try {
      Intrinsics.reifiedOperationMarker(6, "T");
      var8 = null;
   } catch (var15: java.lang.Throwable) {
      var8 = null as KType;
   }

   val var14: TypeInfo = new TypeInfo(Any::class, var8);
   InlineMarker.mark(0);
   val var10000: Any = `$this$serialize`.serialize(charset, var14, value, `$completion`);
   InlineMarker.mark(1);
   return var10000;
}

@JvmSynthetic
fun WebsocketContentConverter.`serialize$default`(value: Any, charset: Charset, `$completion`: Continuation, `$i$f$serialize`: Int, `$i$f$typeInfo`: Any): Any {
   if ((`$i$f$serialize` and 2) != 0) {
      charset = Charsets.UTF_8;
   }

   Intrinsics.reifiedOperationMarker(4, "T");

   var var8: KType;
   try {
      Intrinsics.reifiedOperationMarker(6, "T");
      var8 = null;
   } catch (var15: java.lang.Throwable) {
      var8 = null as KType;
   }

   val var14: TypeInfo = new TypeInfo(Any::class, var8);
   InlineMarker.mark(0);
   val var10000: Any = `$this$serialize_u24default`.serialize(charset, var14, value, `$completion`);
   InlineMarker.mark(1);
   return var10000;
}

@JvmSynthetic
public suspend inline fun <reified T> WebsocketContentConverter.deserialize(content: Frame, charset: Charset = ...): Any {
   Intrinsics.reifiedOperationMarker(4, "T");

   var var8: KType;
   try {
      Intrinsics.reifiedOperationMarker(6, "T");
      var8 = null;
   } catch (var15: java.lang.Throwable) {
      var8 = null as KType;
   }

   val var14: TypeInfo = new TypeInfo(Any::class, var8);
   InlineMarker.mark(0);
   val var10000: Any = `$this$deserialize`.deserialize(charset, var14, content, `$completion`);
   InlineMarker.mark(1);
   Intrinsics.reifiedOperationMarker(1, "T");
   return var10000;
}

@JvmSynthetic
fun WebsocketContentConverter.`deserialize$default`(
   content: Frame, charset: Charset, `$completion`: Continuation, `$i$f$deserialize`: Int, `$i$f$typeInfo`: Any
): Any {
   if ((`$i$f$deserialize` and 2) != 0) {
      charset = Charsets.UTF_8;
   }

   Intrinsics.reifiedOperationMarker(4, "T");

   var var8: KType;
   try {
      Intrinsics.reifiedOperationMarker(6, "T");
      var8 = null;
   } catch (var15: java.lang.Throwable) {
      var8 = null as KType;
   }

   val var14: TypeInfo = new TypeInfo(Any::class, var8);
   InlineMarker.mark(0);
   val var10000: Any = `$this$deserialize_u24default`.deserialize(charset, var14, content, `$completion`);
   InlineMarker.mark(1);
   Intrinsics.reifiedOperationMarker(1, "T");
   return var10000;
}
