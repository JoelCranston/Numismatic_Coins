@file:SourceDebugExtension(["SMAP\nContentConverter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ContentConverter.kt\nio/ktor/serialization/ContentConverterKt\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,122:1\n49#2:123\n51#2:127\n46#3:124\n51#3:126\n105#4:125\n*S KotlinDebug\n*F\n+ 1 ContentConverter.kt\nio/ktor/serialization/ContentConverterKt\n*L\n112#1:123\n112#1:127\n112#1:124\n112#1:126\n112#1:125\n*E\n"])

package io.ktor.serialization

import io.ktor.http.HeaderValue
import io.ktor.http.Headers
import io.ktor.http.HttpHeaderValueParserKt
import io.ktor.http.HttpHeaders
import io.ktor.http.content.NullBody
import io.ktor.serialization.ContentConverterKt.deserialize.1
import io.ktor.serialization.ContentConverterKt.deserialize.result.2
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.charsets.CharsetJVMKt
import java.nio.charset.Charset
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowKt

public fun Headers.suitableCharset(defaultCharset: Charset = Charsets.UTF_8): Charset {
   var var10000: Charset = suitableCharsetOrNull(`$this$suitableCharset`, defaultCharset);
   if (var10000 == null) {
      var10000 = defaultCharset;
   }

   return var10000;
}

@JvmSynthetic
fun `suitableCharset$default`(var0: Headers, var1: Charset, var2: Int, var3: Any): Charset {
   if ((var2 and 1) != 0) {
      var1 = Charsets.UTF_8;
   }

   return suitableCharset(var0, var1);
}

public fun Headers.suitableCharsetOrNull(defaultCharset: Charset = Charsets.UTF_8): Charset? {
   val var2: java.util.Iterator = HttpHeaderValueParserKt.parseAndSortHeader(`$this$suitableCharsetOrNull`.get(HttpHeaders.INSTANCE.getAcceptCharset()))
      .iterator();

   while (var2.hasNext()) {
      val charset: java.lang.String = (var2.next() as HeaderValue).component1();
      if (charset == "*") {
         return defaultCharset;
      }

      if (CharsetJVMKt.isSupported(Charsets.INSTANCE, charset)) {
         return CharsetJVMKt.forName(Charsets.INSTANCE, charset);
      }
   }

   return null;
}

@JvmSynthetic
fun `suitableCharsetOrNull$default`(var0: Headers, var1: Charset, var2: Int, var3: Any): Charset {
   if ((var2 and 1) != 0) {
      var1 = Charsets.UTF_8;
   }

   return suitableCharsetOrNull(var0, var1);
}

@InternalAPI
public suspend fun List<ContentConverter>.deserialize(body: ByteReadChannel, typeInfo: TypeInfo, charset: Charset): Any {
   var `$continuation`: Continuation;
   label39: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label39;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var13: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         var10000 = new io.ktor.serialization.ContentConverterKt.deserialize..inlined.map.1(FlowKt.asFlow(`$this$deserialize`), charset, typeInfo, body);
         val var10001: Function2 = new 2(body, null);
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$deserialize`);
         `$continuation`.L$1 = body;
         `$continuation`.L$2 = typeInfo;
         `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(charset);
         `$continuation`.label = 1;
         var10000 = FlowKt.firstOrNull((Flow)var10000, var10001, `$continuation`);
         if (var10000 === var13) {
            return var13;
         }
         break;
      case 1:
         charset = `$continuation`.L$3 as Charset;
         typeInfo = `$continuation`.L$2 as TypeInfo;
         body = `$continuation`.L$1 as ByteReadChannel;
         `$this$deserialize` = `$continuation`.L$0 as java.util.List;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   var10000 = var10000;
   if (var10000 == null) {
      if (!body.isClosedForRead()) {
         var10000 = body;
      } else {
         var10000 = typeInfo.getKotlinType();
         if (var10000 == null || !((KType)var10000).isMarkedNullable()) {
            throw new ContentConvertException("No suitable converter found for $typeInfo", null, 2, null);
         }

         var10000 = NullBody.INSTANCE;
      }
   }

   return var10000;
}
