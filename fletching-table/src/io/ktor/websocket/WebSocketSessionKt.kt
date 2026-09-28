@file:SourceDebugExtension(["SMAP\nWebSocketSession.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebSocketSession.kt\nio/ktor/websocket/WebSocketSessionKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,186:1\n295#2,2:187\n*S KotlinDebug\n*F\n+ 1 WebSocketSession.kt\nio/ktor/websocket/WebSocketSessionKt\n*L\n120#1:187,2\n*E\n"])

package io.ktor.websocket

import io.ktor.websocket.WebSocketSessionKt.close.1
import java.util.concurrent.CancellationException
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension

public fun <T : WebSocketExtension<*>> WebSocketSession.extension(extension: WebSocketExtensionFactory<*, Any>): Any {
   val var10000: WebSocketExtension = extensionOrNull(`$this$extension`, extension);
   if (var10000 == null) {
      throw new IllegalStateException(("Extension $extension not found.").toString());
   } else {
      return (T)var10000;
   }
}

public fun <T : WebSocketExtension<*>> WebSocketSession.extensionOrNull(extension: WebSocketExtensionFactory<*, Any>): Any? {
   val var5: java.util.Iterator = `$this$extensionOrNull`.getExtensions().iterator();

   var var10000: Any;
   while (true) {
      if (var5.hasNext()) {
         val `element$iv`: Any = var5.next();
         if ((`element$iv` as WebSocketExtension).getFactory().getKey() != extension.getKey()) {
            continue;
         }

         var10000 = `element$iv`;
         break;
      }

      var10000 = null;
      break;
   }

   return (T)(var10000 as? WebSocketExtension);
}

public suspend fun WebSocketSession.send(content: String) {
   val var10000: Any = `$this$send`.send(new Frame.Text(content), `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun WebSocketSession.send(content: ByteArray) {
   val var10000: Any = `$this$send`.send(new Frame.Binary(true, content), `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun WebSocketSession.close(reason: CloseReason = ...) {
   var `$continuation`: Continuation;
   label58: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label58;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   label61: {
      val `$result`: Any = `$continuation`.result;
      val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);

            var var10000: Any;
            try {
               val var10001: Frame = new Frame.Close(reason);
               `$continuation`.L$0 = `$this$close`;
               `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(reason);
               `$continuation`.label = 1;
               var10000 = `$this$close`.send(var10001, `$continuation`);
            } catch (var9: java.lang.Throwable) {
               return Unit.INSTANCE;
            }

            if (var10000 === var6) {
               return var6;
            }
            break;
         case 1:
            reason = `$continuation`.L$1 as CloseReason;
            `$this$close` = `$continuation`.L$0 as WebSocketSession;

            try {
               ResultKt.throwOnFailure(`$result`);
               break;
            } catch (var10: java.lang.Throwable) {
               return Unit.INSTANCE;
            }
         case 2:
            reason = `$continuation`.L$1 as CloseReason;
            `$this$close` = `$continuation`.L$0 as WebSocketSession;

            try {
               ResultKt.throwOnFailure(`$result`);
               break label61;
            } catch (var11: java.lang.Throwable) {
               return Unit.INSTANCE;
            }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      var var14: Any;
      try {
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$close`);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(reason);
         `$continuation`.label = 2;
         var14 = `$this$close`.flush(`$continuation`);
      } catch (var8: java.lang.Throwable) {
         return Unit.INSTANCE;
      }

      if (var14 === var6) {
         return var6;
      }
   }

   try {
      ;
   } catch (var7: java.lang.Throwable) {
   }

   return Unit.INSTANCE;
}

@JvmSynthetic
fun `close$default`(var0: WebSocketSession, var1: CloseReason, var2: Continuation, var3: Int, var4: Any): Any {
   if ((var3 and 1) != 0) {
      var1 = new CloseReason(CloseReason.Codes.NORMAL, "");
   }

   return close(var0, var1, var2);
}

@Deprecated(message = "Close with reason or terminate instead.", level = DeprecationLevel.ERROR)
public suspend fun WebSocketSession.close(cause: Throwable?) {
   if (cause == null) {
      val var3: Any = close$default(`$this$close`, null, `$completion`, 1, null);
      return if (var3 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var3 else Unit.INSTANCE;
   } else {
      val var10000: Any = closeExceptionally(`$this$close`, cause, `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }
}

public suspend fun WebSocketSession.closeExceptionally(cause: Throwable) {
   val var10000: Any = close(
      `$this$closeExceptionally`,
      if (cause is CancellationException)
         new CloseReason(CloseReason.Codes.NORMAL, "")
         else
         new CloseReason(CloseReason.Codes.INTERNAL_ERROR, cause.toString()),
      `$completion`
   );
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}
