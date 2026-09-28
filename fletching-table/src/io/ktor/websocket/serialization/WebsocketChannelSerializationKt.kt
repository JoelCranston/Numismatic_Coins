@file:SourceDebugExtension(["SMAP\nWebsocketChannelSerialization.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebsocketChannelSerialization.kt\nio/ktor/websocket/serialization/WebsocketChannelSerializationKt\n+ 2 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,137:1\n69#2:138\n84#2,8:139\n69#2:147\n84#2,8:148\n*S KotlinDebug\n*F\n+ 1 WebsocketChannelSerialization.kt\nio/ktor/websocket/serialization/WebsocketChannelSerializationKt\n*L\n33#1:138\n33#1:139,8\n84#1:147\n84#1:148,8\n*E\n"])

package io.ktor.websocket.serialization

import io.ktor.serialization.WebsocketContentConverter
import io.ktor.serialization.WebsocketDeserializeException
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.InternalAPI
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.serialization.WebsocketChannelSerializationKt.sendSerializedBase.2
import java.nio.charset.Charset
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

@InternalAPI
@JvmSynthetic
public suspend inline fun <reified T> WebSocketSession.sendSerializedBase(data: Any?, converter: WebsocketContentConverter, charset: Charset) {
   Intrinsics.reifiedOperationMarker(4, "T");

   var var9: KType;
   try {
      Intrinsics.reifiedOperationMarker(6, "T");
      var9 = null;
   } catch (var16: java.lang.Throwable) {
      var9 = null as KType;
   }

   val var15: TypeInfo = new TypeInfo(Any::class, var9);
   InlineMarker.mark(0);
   sendSerializedBase(`$this$sendSerializedBase`, data, var15, converter, charset, `$completion`);
   InlineMarker.mark(1);
   return Unit.INSTANCE;
}

@InternalAPI
public suspend fun WebSocketSession.sendSerializedBase(data: Any?, typeInfo: TypeInfo, converter: WebsocketContentConverter, charset: Charset) {
   var `$continuation`: Continuation;
   label27: {
      if (`$completion` is 2) {
         `$continuation` = `$completion` as 2;
         if (((`$completion` as 2).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label27;
         }
      }

      `$continuation` = new 2(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$continuation`.L$0 = `$this$sendSerializedBase`;
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(data);
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(typeInfo);
         `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(converter);
         `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(charset);
         `$continuation`.label = 1;
         var10000 = converter.serialize(charset, typeInfo, data, `$continuation`);
         if (var10000 === var9) {
            return var9;
         }
         break;
      case 1:
         charset = `$continuation`.L$4 as Charset;
         converter = `$continuation`.L$3 as WebsocketContentConverter;
         typeInfo = `$continuation`.L$2 as TypeInfo;
         data = `$continuation`.L$1;
         `$this$sendSerializedBase` = `$continuation`.L$0 as WebSocketSession;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      case 2:
         val serializedData: Frame = `$continuation`.L$5 as Frame;
         charset = `$continuation`.L$4 as Charset;
         converter = `$continuation`.L$3 as WebsocketContentConverter;
         typeInfo = `$continuation`.L$2 as TypeInfo;
         data = `$continuation`.L$1;
         `$this$sendSerializedBase` = `$continuation`.L$0 as WebSocketSession;
         ResultKt.throwOnFailure(`$result`);
         return Unit.INSTANCE;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   val var15: Frame = var10000 as Frame;
   var10000 = `$this$sendSerializedBase`.getOutgoing();
   `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$sendSerializedBase`);
   `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(data);
   `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(typeInfo);
   `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(converter);
   `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(charset);
   `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(var15);
   `$continuation`.label = 2;
   return if (((SendChannel)var10000).send(var15, `$continuation`) === var9) var9 else Unit.INSTANCE;
}

@InternalAPI
@JvmSynthetic
public suspend inline fun <reified T> WebSocketSession.receiveDeserializedBase(converter: WebsocketContentConverter, charset: Charset): Any? {
   Intrinsics.reifiedOperationMarker(4, "T");

   var var8: KType;
   try {
      Intrinsics.reifiedOperationMarker(6, "T");
      var8 = null;
   } catch (var14: java.lang.Throwable) {
      var8 = null as KType;
   }

   val var13: TypeInfo = new TypeInfo(Any::class, var8);
   InlineMarker.mark(0);
   val var10000: Any = receiveDeserializedBase(`$this$receiveDeserializedBase`, var13, converter, charset, `$completion`);
   InlineMarker.mark(1);
   return var10000;
}

@InternalAPI
public suspend fun WebSocketSession.receiveDeserializedBase(typeInfo: TypeInfo, converter: WebsocketContentConverter, charset: Charset): Any? {
   var `$continuation`: Continuation;
   label54: {
      if (`$completion` is io.ktor.websocket.serialization.WebsocketChannelSerializationKt.receiveDeserializedBase.2) {
         `$continuation` = `$completion` as io.ktor.websocket.serialization.WebsocketChannelSerializationKt.receiveDeserializedBase.2;
         if (((`$completion` as io.ktor.websocket.serialization.WebsocketChannelSerializationKt.receiveDeserializedBase.2).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label54;
         }
      }

      `$continuation` = new io.ktor.websocket.serialization.WebsocketChannelSerializationKt.receiveDeserializedBase.2(`$completion`);
   }

   var frame: Frame;
   var var10000: ReceiveChannel;
   label58: {
      val `$result`: Any = `$continuation`.result;
      val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$this$receiveDeserializedBase`.getIncoming();
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$receiveDeserializedBase`);
            `$continuation`.L$1 = typeInfo;
            `$continuation`.L$2 = converter;
            `$continuation`.L$3 = charset;
            `$continuation`.label = 1;
            var10000 = var10000.receive(`$continuation`);
            if (var10000 === var9) {
               return var9;
            }
            break;
         case 1:
            charset = `$continuation`.L$3 as Charset;
            converter = `$continuation`.L$2 as WebsocketContentConverter;
            typeInfo = `$continuation`.L$1 as TypeInfo;
            `$this$receiveDeserializedBase` = `$continuation`.L$0 as WebSocketSession;
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         case 2:
            frame = `$continuation`.L$4 as Frame;
            charset = `$continuation`.L$3 as Charset;
            converter = `$continuation`.L$2 as WebsocketContentConverter;
            typeInfo = `$continuation`.L$1 as TypeInfo;
            `$this$receiveDeserializedBase` = `$continuation`.L$0 as WebSocketSession;
            ResultKt.throwOnFailure(`$result`);
            var10000 = (ReceiveChannel)`$result`;
            break label58;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      frame = var10000 as Frame;
      if (!converter.isApplicable(var10000 as Frame)) {
         throw new WebsocketDeserializeException("Converter doesn't support frame type ${frame.getFrameType().name()}", null, frame, 2, null);
      }

      `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$receiveDeserializedBase`);
      `$continuation`.L$1 = typeInfo;
      `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(converter);
      `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(charset);
      `$continuation`.L$4 = frame;
      `$continuation`.label = 2;
      var10000 = (ReceiveChannel)converter.deserialize(charset, typeInfo, frame, `$continuation`);
      if (var10000 === var9) {
         return var9;
      }
   }

   if (typeInfo.getType().isInstance(var10000)) {
      return var10000;
   } else if (var10000 == null) {
      val var15: KType = typeInfo.getKotlinType();
      if (var15 != null && var15.isMarkedNullable()) {
         return null;
      } else {
         throw new WebsocketDeserializeException("Frame has null content", null, frame, 2, null);
      }
   } else {
      throw new WebsocketDeserializeException(
         "Can't deserialize value: expected value of type ${typeInfo.getType().getSimpleName()}, got ${(var10000.getClass()::class).getSimpleName()}",
         null,
         frame,
         2,
         null
      );
   }
}
