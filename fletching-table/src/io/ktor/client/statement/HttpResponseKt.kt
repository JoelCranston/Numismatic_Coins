@file:SourceDebugExtension(["SMAP\nHttpResponse.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpResponse.kt\nio/ktor/client/statement/HttpResponseKt\n+ 2 HttpClientCall.kt\nio/ktor/client/call/HttpClientCallKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,146:1\n162#2:147\n162#2:157\n162#2:167\n69#3:148\n84#3,8:149\n69#3:158\n84#3,8:159\n69#3:168\n84#3,8:169\n*S KotlinDebug\n*F\n+ 1 HttpResponse.kt\nio/ktor/client/statement/HttpResponseKt\n*L\n125#1:147\n135#1:157\n145#1:167\n125#1:148\n125#1:149,8\n135#1:158\n135#1:159,8\n145#1:168\n145#1:169,8\n*E\n"])

package io.ktor.client.statement

import io.ktor.client.request.HttpRequest
import io.ktor.client.statement.HttpResponseKt.bodyAsText.1
import io.ktor.http.HttpMessagePropertiesKt
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.charsets.EncodingKt
import java.nio.charset.Charset
import java.nio.charset.CharsetDecoder
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.Reflection
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt
import kotlinx.io.Source

@Deprecated(
   message = "This method was renamed to readRawBytes() to reflect what it does.",
   replaceWith = @ReplaceWith(
      expression = "readRawBytes()",
      imports = {}
   )
)
@InternalAPI
public final val content: ByteReadChannel
   public final get() {
      return `$this$content`.getRawContent();
   }


public final val request: HttpRequest
   public final get() {
      return `$this$request`.getCall().getRequest();
   }


@InternalAPI
@PublishedApi
internal fun HttpResponse.complete() {
   val var10000: Job = JobKt.getJob(`$this$complete`.getCoroutineContext());
   (var10000 as CompletableJob).complete();
}

public suspend fun HttpResponse.bodyAsText(fallbackCharset: Charset = ...): String {
   var `$continuation`: Continuation;
   label37: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label37;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var18: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var decoder: CharsetDecoder;
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         var10000 = HttpMessagePropertiesKt.charset(`$this$bodyAsText`);
         if (var10000 == null) {
            var10000 = fallbackCharset;
         }

         decoder = var10000.newDecoder();

         var var12: KType;
         try {
            var12 = Reflection.typeOf(Source.class);
         } catch (var19: java.lang.Throwable) {
            var12 = null;
         }

         val var10001: TypeInfo = new TypeInfo(Source::class, var12);
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$bodyAsText`);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(fallbackCharset);
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var10000);
         `$continuation`.L$3 = decoder;
         `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(`$this$bodyAsText`);
         `$continuation`.I$0 = 0;
         `$continuation`.label = 1;
         var10000 = (Charset)`$this$bodyAsText`.getCall().bodyNullable(var10001, `$continuation`);
         if (var10000 === var18) {
            return var18;
         }
         break;
      case 1:
         val `$i$f$body`: Int = `$continuation`.I$0;
         val `$this$body$iv`: HttpResponse = `$continuation`.L$4 as HttpResponse;
         decoder = `$continuation`.L$3 as CharsetDecoder;
         val originCharset: Charset = `$continuation`.L$2 as Charset;
         fallbackCharset = `$continuation`.L$1 as Charset;
         `$this$bodyAsText` = `$continuation`.L$0 as HttpResponse;
         ResultKt.throwOnFailure(`$result`);
         var10000 = (Charset)`$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   if (var10000 == null) {
      throw new NullPointerException("null cannot be cast to non-null type kotlinx.io.Source");
   } else {
      val input: Source = var10000 as Source;
      return EncodingKt.decode$default(decoder, input, 0, 2, null);
   }
}

@JvmSynthetic
fun `bodyAsText$default`(var0: HttpResponse, var1: Charset, var2: Continuation, var3: Int, var4: Any): Any {
   if ((var3 and 1) != 0) {
      var1 = Charsets.UTF_8;
   }

   return bodyAsText(var0, var1, var2);
}

public suspend fun HttpResponse.bodyAsChannel(): ByteReadChannel {
   var `$continuation`: Continuation;
   label33: {
      if (`$completion` is io.ktor.client.statement.HttpResponseKt.bodyAsChannel.1) {
         `$continuation` = `$completion` as io.ktor.client.statement.HttpResponseKt.bodyAsChannel.1;
         if (((`$completion` as io.ktor.client.statement.HttpResponseKt.bodyAsChannel.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label33;
         }
      }

      `$continuation` = new io.ktor.client.statement.HttpResponseKt.bodyAsChannel.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var14: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);

         var var8: KType;
         try {
            var8 = Reflection.typeOf(ByteReadChannel.class);
         } catch (var15: java.lang.Throwable) {
            var8 = null;
         }

         val var10001: TypeInfo = new TypeInfo(ByteReadChannel::class, var8);
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$bodyAsChannel`);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(`$this$bodyAsChannel`);
         `$continuation`.I$0 = 0;
         `$continuation`.label = 1;
         var10000 = `$this$bodyAsChannel`.getCall().bodyNullable(var10001, `$continuation`);
         if (var10000 === var14) {
            return var14;
         }
         break;
      case 1:
         val `$i$f$body`: Int = `$continuation`.I$0;
         val `$this$body$iv`: HttpResponse = `$continuation`.L$1 as HttpResponse;
         `$this$bodyAsChannel` = `$continuation`.L$0 as HttpResponse;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   if (var10000 == null) {
      throw new NullPointerException("null cannot be cast to non-null type io.ktor.utils.io.ByteReadChannel");
   } else {
      return var10000 as ByteReadChannel;
   }
}

public suspend fun HttpResponse.bodyAsBytes(): ByteArray {
   var `$continuation`: Continuation;
   label33: {
      if (`$completion` is io.ktor.client.statement.HttpResponseKt.bodyAsBytes.1) {
         `$continuation` = `$completion` as io.ktor.client.statement.HttpResponseKt.bodyAsBytes.1;
         if (((`$completion` as io.ktor.client.statement.HttpResponseKt.bodyAsBytes.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label33;
         }
      }

      `$continuation` = new io.ktor.client.statement.HttpResponseKt.bodyAsBytes.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var14: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);

         var var8: KType;
         try {
            var8 = Reflection.typeOf(byte[].class);
         } catch (var15: java.lang.Throwable) {
            var8 = null;
         }

         val var10001: TypeInfo = new TypeInfo(ByteArray::class, var8);
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$bodyAsBytes`);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(`$this$bodyAsBytes`);
         `$continuation`.I$0 = 0;
         `$continuation`.label = 1;
         var10000 = `$this$bodyAsBytes`.getCall().bodyNullable(var10001, `$continuation`);
         if (var10000 === var14) {
            return var14;
         }
         break;
      case 1:
         val `$i$f$body`: Int = `$continuation`.I$0;
         val `$this$body$iv`: HttpResponse = `$continuation`.L$1 as HttpResponse;
         `$this$bodyAsBytes` = `$continuation`.L$0 as HttpResponse;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   if (var10000 == null) {
      throw new NullPointerException("null cannot be cast to non-null type kotlin.ByteArray");
   } else {
      return var10000 as ByteArray;
   }
}
