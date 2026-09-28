@file:SourceDebugExtension(["SMAP\nClientSessions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClientSessions.kt\nio/ktor/client/plugins/websocket/ClientSessionsKt\n+ 2 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,139:1\n69#2:140\n84#2,8:141\n69#2:149\n84#2,8:150\n*S KotlinDebug\n*F\n+ 1 ClientSessions.kt\nio/ktor/client/plugins/websocket/ClientSessionsKt\n*L\n93#1:140\n93#1:141,8\n138#1:149\n138#1:150,8\n*E\n"])

package io.ktor.client.plugins.websocket

import io.ktor.client.plugins.HttpClientPluginKt
import io.ktor.serialization.ContentConverterKt
import io.ktor.serialization.WebsocketContentConverter
import io.ktor.serialization.WebsocketConverterNotFoundException
import io.ktor.util.reflect.TypeInfo
import io.ktor.websocket.serialization.WebsocketChannelSerializationKt
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType

public final val converter: WebsocketContentConverter?
   public final get() {
      val var10000: WebSockets = HttpClientPluginKt.pluginOrNull(`$this$converter`.getCall().getClient(), WebSockets.Plugin);
      return if (var10000 != null) var10000.getContentConverter() else null;
   }


public suspend fun DefaultClientWebSocketSession.sendSerialized(data: Any?, typeInfo: TypeInfo) {
   var var10000: WebsocketContentConverter = getConverter(`$this$sendSerialized`);
   if (var10000 == null) {
      throw new WebsocketConverterNotFoundException("No converter was found for websocket", null, 2, null);
   } else {
      var10000 = (WebsocketContentConverter)WebsocketChannelSerializationKt.sendSerializedBase(
         `$this$sendSerialized`,
         data,
         typeInfo,
         var10000,
         ContentConverterKt.suitableCharset$default(`$this$sendSerialized`.getCall().getRequest().getHeaders(), null, 1, null),
         `$completion`
      );
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }
}

@JvmSynthetic
public suspend inline fun <reified T> DefaultClientWebSocketSession.sendSerialized(data: Any) {
   Intrinsics.reifiedOperationMarker(4, "T");

   var var7: KType;
   try {
      Intrinsics.reifiedOperationMarker(6, "T");
      var7 = null;
   } catch (var14: java.lang.Throwable) {
      var7 = null as KType;
   }

   val var13: TypeInfo = new TypeInfo(Any::class, var7);
   InlineMarker.mark(0);
   sendSerialized(`$this$sendSerialized`, data, var13, `$completion`);
   InlineMarker.mark(1);
   return Unit.INSTANCE;
}

public suspend fun <T> DefaultClientWebSocketSession.receiveDeserialized(typeInfo: TypeInfo): Any {
   var var10000: WebsocketContentConverter = getConverter(`$this$receiveDeserialized`);
   if (var10000 == null) {
      throw new WebsocketConverterNotFoundException("No converter was found for websocket", null, 2, null);
   } else {
      var10000 = (WebsocketContentConverter)WebsocketChannelSerializationKt.receiveDeserializedBase(
         `$this$receiveDeserialized`,
         typeInfo,
         var10000,
         ContentConverterKt.suitableCharset$default(`$this$receiveDeserialized`.getCall().getRequest().getHeaders(), null, 1, null),
         `$completion`
      );
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else var10000;
   }
}

@JvmSynthetic
public suspend inline fun <reified T> DefaultClientWebSocketSession.receiveDeserialized(): Any {
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
   val var10000: Any = receiveDeserialized(`$this$receiveDeserialized`, var11, `$completion`);
   InlineMarker.mark(1);
   return var10000;
}
