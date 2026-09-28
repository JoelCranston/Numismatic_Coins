@file:SourceDebugExtension(["SMAP\nformDsl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 formDsl.kt\nio/ktor/client/request/forms/FormDslKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,281:1\n231#1,2:287\n13805#2,2:282\n37#3,2:284\n1#4:286\n*S KotlinDebug\n*F\n+ 1 formDsl.kt\nio/ktor/client/request/forms/FormDslKt\n*L\n279#1:287,2\n38#1:282,2\n87#1:284,2\n*E\n"])

package io.ktor.client.request.forms

import io.ktor.client.request.forms.FormDslKt.append.1
import io.ktor.http.ContentType
import io.ktor.http.HeaderValueWithParametersKt
import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import io.ktor.http.content.PartData
import io.ktor.utils.io.core.ByteReadPacketKt
import java.util.ArrayList
import java.util.Arrays
import kotlin.contracts.InvocationKind
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source

public fun formData(vararg values: FormPart<*>): List<PartData> {
   val result: java.util.List = new ArrayList();

   for (Object element$iv : values) {
      val key: java.lang.String = ((FormPart)`element$iv`).component1();
      val value: Any = ((FormPart)`element$iv`).component2();
      val headers: Headers = ((FormPart)`element$iv`).component3();
      val part: HeadersBuilder = new HeadersBuilder(0, 1, null);
      part.append(HttpHeaders.INSTANCE.getContentDisposition(), "form-data; name=${HeaderValueWithParametersKt.escapeIfNeeded(key)}");
      part.appendAll(headers);
      val var10000: PartData;
      if (value is java.lang.String) {
         var10000 = new PartData.FormItem(value as java.lang.String, FormDslKt::formData$lambda$0$1, part.build());
      } else if (value is java.lang.Number) {
         var10000 = new PartData.FormItem(value.toString(), FormDslKt::formData$lambda$0$2, part.build());
      } else if (value is java.lang.Boolean) {
         var10000 = new PartData.FormItem(java.lang.String.valueOf((value as java.lang.Boolean).booleanValue()), FormDslKt::formData$lambda$0$3, part.build());
      } else if (value is ByteArray) {
         part.append(HttpHeaders.INSTANCE.getContentLength(), java.lang.String.valueOf((value as ByteArray).length));
         var10000 = new PartData.BinaryItem(FormDslKt::formData$lambda$0$4, FormDslKt::formData$lambda$0$5, part.build());
      } else if (value is Source) {
         if (value is Buffer) {
            part.append(HttpHeaders.INSTANCE.getContentLength(), java.lang.String.valueOf(ByteReadPacketKt.getRemaining(value as Source)));
         }

         var10000 = new PartData.BinaryItem(FormDslKt::formData$lambda$0$6, FormDslKt::formData$lambda$0$7, part.build());
      } else if (value is InputProvider) {
         val var17: java.lang.Long = (value as InputProvider).getSize();
         if (var17 != null) {
            part.append(HttpHeaders.INSTANCE.getContentLength(), java.lang.String.valueOf(var17.longValue()));
         }

         var10000 = new PartData.BinaryItem((value as InputProvider).getBlock(), FormDslKt::formData$lambda$0$8, part.build());
      } else {
         if (value !is ChannelProvider) {
            throw new IllegalStateException(("Unknown form content type: $value").toString());
         }

         val var18: java.lang.Long = (value as ChannelProvider).getSize();
         if (var18 != null) {
            part.append(HttpHeaders.INSTANCE.getContentLength(), java.lang.String.valueOf(var18.longValue()));
         }

         var10000 = new PartData.BinaryChannelItem((value as ChannelProvider).getBlock(), part.build());
      }

      result.add(var10000);
   }

   return result;
}

public fun formData(block: (FormBuilder) -> Unit): List<PartData> {
   val `$this$toTypedArray$iv`: FormBuilder = new FormBuilder();
   block.invoke(`$this$toTypedArray$iv`);
   val var1: Array<FormPart> = `$this$toTypedArray$iv`.build$ktor_client_core().toArray(new FormPart[0]);
   return formData(Arrays.copyOf(var1, var1.length));
}

public inline fun FormBuilder.append(key: String, headers: Headers = Headers.Companion.getEmpty(), size: Long? = null, crossinline bodyBuilder: (Sink) -> Unit) {
   contract {
      callsInPlace(bodyBuilder, InvocationKind.EXACTLY_ONCE)
   }

   `$this$append`.append(new FormPart<>(key, new InputProvider(size, new 1(bodyBuilder)), headers));
}

@JvmSynthetic
fun FormBuilder.`append$default`(key: java.lang.String, headers: Headers, size: java.lang.Long, bodyBuilder: Function1, `$i$f$append`: Int, var6: Any) {
   if ((`$i$f$append` and 2) != 0) {
      headers = Headers.Companion.getEmpty();
   }

   if ((`$i$f$append` and 4) != 0) {
      size = null;
   }

   `$this$append_u24default`.append(new FormPart<>(key, new InputProvider(size, new 1(bodyBuilder)), headers));
}

public fun FormBuilder.append(key: String, filename: String, contentType: ContentType? = null, size: Long? = null, bodyBuilder: (Sink) -> Unit) {
   contract {
      callsInPlace(bodyBuilder, InvocationKind.EXACTLY_ONCE)
   }

   val headersBuilder: HeadersBuilder = new HeadersBuilder(0, 1, null);
   headersBuilder.set(HttpHeaders.INSTANCE.getContentDisposition(), "filename=${HeaderValueWithParametersKt.escapeIfNeeded(filename)}");
   if (contentType != null) {
      headersBuilder.set(HttpHeaders.INSTANCE.getContentType(), contentType.toString());
   }

   `$this$append`.append(new FormPart<>(key, new InputProvider(size, new 1(bodyBuilder)), headersBuilder.build()));
}

@JvmSynthetic
fun `append$default`(
   var0: FormBuilder, var1: java.lang.String, var2: java.lang.String, var3: ContentType, var4: java.lang.Long, var5: Function1, var6: Int, var7: Any
) {
   if ((var6 and 4) != 0) {
      var3 = null;
   }

   if ((var6 and 8) != 0) {
      var4 = null;
   }

   append(var0, var1, var2, var3, var4, var5);
}

fun `formData$lambda$0$1`(): Unit {
   return Unit.INSTANCE;
}

fun `formData$lambda$0$2`(): Unit {
   return Unit.INSTANCE;
}

fun `formData$lambda$0$3`(): Unit {
   return Unit.INSTANCE;
}

fun `formData$lambda$0$4`(`$value`: Any): Source {
   return ByteReadPacketKt.ByteReadPacket$default(`$value` as ByteArray, 0, 0, 6, null);
}

fun `formData$lambda$0$5`(): Unit {
   return Unit.INSTANCE;
}

fun `formData$lambda$0$6`(`$value`: Any): Source {
   return (`$value` as Source).peek();
}

fun `formData$lambda$0$7`(`$value`: Any): Unit {
   (`$value` as Source).close();
   return Unit.INSTANCE;
}

fun `formData$lambda$0$8`(): Unit {
   return Unit.INSTANCE;
}
