package io.ktor.http.content

import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.util.AttributeKey
import io.ktor.util.ContentEncoder
import io.ktor.util.StringValuesKt
import io.ktor.utils.io.ByteReadChannel
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nCompressedContent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CompressedContent.kt\nio/ktor/http/content/CompressedReadChannelResponse\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Headers.kt\nio/ktor/http/Headers$Companion\n*L\n1#1,99:1\n1#2:100\n30#3:101\n*S KotlinDebug\n*F\n+ 1 CompressedContent.kt\nio/ktor/http/content/CompressedReadChannelResponse\n*L\n46#1:101\n*E\n"])
private class CompressedReadChannelResponse(original: OutgoingContent,
      delegateChannel: () -> ByteReadChannel,
      encoder: ContentEncoder,
      coroutineContext: CoroutineContext
   )
   : OutgoingContent.ReadChannelContent {
   public final val original: OutgoingContent
   public final val delegateChannel: () -> ByteReadChannel
   public final val encoder: ContentEncoder
   public final val coroutineContext: CoroutineContext

   public open val headers: Headers
      public open get() {
         return this.headers$delegate.getValue() as Headers;
      }


   public open val contentType: ContentType?
      public open get() {
         return this.original.getContentType();
      }


   public open val status: HttpStatusCode?
      public open get() {
         return this.original.getStatus();
      }


   public open val contentLength: Long?
      public open get() {
         var var10000: java.lang.Long = this.original.getContentLength();
         if (var10000 != null) {
            var10000 = this.encoder.predictCompressedLength(var10000.longValue());
            if (var10000 != null) {
               return if (var10000.longValue() >= 0L) var10000 else null;
            }
         }

         return null;
      }


   init {
      this.original = original;
      this.delegateChannel = delegateChannel;
      this.encoder = encoder;
      this.coroutineContext = coroutineContext;
      this.headers$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, CompressedReadChannelResponse::headers_delegate$lambda$0);
   }

   public override fun readFrom(): ByteReadChannel {
      return this.encoder.encode(this.delegateChannel.invoke(), this.coroutineContext);
   }

   public override fun <T : Any> getProperty(key: AttributeKey<Any>): Any? {
      return (T)this.original.getProperty(key);
   }

   public override fun <T : Any> setProperty(key: AttributeKey<Any>, value: Any?) {
      this.original.setProperty(key, value);
   }

   @JvmStatic
   fun `headers_delegate$lambda$0`(`this$0`: CompressedReadChannelResponse): Headers {
      var var3: HeadersBuilder;
      var var10001: java.lang.String;
      var var10002: java.lang.String;
      label12: {
         val `this_$iv`: Headers.Companion = Headers.Companion;
         var3 = new HeadersBuilder(0, 1, null);
         StringValuesKt.appendFiltered$default(
            var3, `this$0`.original.getHeaders(), false, CompressedReadChannelResponse::headers_delegate$lambda$0$0$0, 2, null
         );
         var3.append(HttpHeaders.INSTANCE.getContentEncoding(), `this$0`.encoder.getName());
         var10001 = HttpHeaders.INSTANCE.getVary();
         val var6: java.lang.String = `this$0`.original.getHeaders().get(HttpHeaders.INSTANCE.getVary());
         if (var6 != null) {
            val var7: java.lang.String = "$var6, ${HttpHeaders.INSTANCE.getAcceptEncoding()}";
            if (var7 != null) {
               var10002 = var7;
               break label12;
            }
         }

         var10002 = HttpHeaders.INSTANCE.getAcceptEncoding();
      }

      var3.append(var10001, var10002);
      return var3.build();
   }

   @JvmStatic
   fun `headers_delegate$lambda$0$0$0`(name: java.lang.String, var1: java.lang.String): Boolean {
      return !StringsKt.equals(name, HttpHeaders.INSTANCE.getContentLength(), true);
   }
}
