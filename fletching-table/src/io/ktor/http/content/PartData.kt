package io.ktor.http.content

import io.ktor.http.ContentDisposition
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.utils.io.ByteReadChannel
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Source

@SourceDebugExtension(["SMAP\nMultipart.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Multipart.kt\nio/ktor/http/content/PartData\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,179:1\n1#2:180\n*E\n"])
public sealed class PartData protected constructor(dispose: () -> Unit, headers: Headers) {
   public final val dispose: () -> Unit
   public final val headers: Headers

   public final val contentDisposition: ContentDisposition?
      public final get() {
         return this.contentDisposition$delegate.getValue() as ContentDisposition;
      }


   public final val contentType: ContentType?
      public final get() {
         return this.contentType$delegate.getValue() as ContentType;
      }


   public final val name: String?
      public final get() {
         val var10000: ContentDisposition = this.getContentDisposition();
         return if (var10000 != null) var10000.getName() else null;
      }


   init {
      this.dispose = dispose;
      this.headers = headers;
      this.contentDisposition$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, PartData::contentDisposition_delegate$lambda$0);
      this.contentType$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, PartData::contentType_delegate$lambda$0);
   }

   @JvmStatic
   fun `contentDisposition_delegate$lambda$0`(`this$0`: PartData): ContentDisposition {
      val var10000: java.lang.String = `this$0`.headers.get(HttpHeaders.INSTANCE.getContentDisposition());
      return if (var10000 != null) ContentDisposition.Companion.parse(var10000) else null;
   }

   @JvmStatic
   fun `contentType_delegate$lambda$0`(`this$0`: PartData): ContentType {
      val var10000: java.lang.String = `this$0`.headers.get(HttpHeaders.INSTANCE.getContentType());
      return if (var10000 != null) ContentType.Companion.parse(var10000) else null;
   }

   public class BinaryChannelItem(provider: () -> ByteReadChannel, partHeaders: Headers) : PartData(PartData.BinaryChannelItem::_init_$lambda$0, partHeaders) {
      public final val provider: () -> ByteReadChannel

      init {
         this.provider = provider;
      }

      @JvmStatic
      fun `_init_$lambda$0`(): Unit {
         return Unit.INSTANCE;
      }
   }

   public class BinaryItem(provider: () -> Source, dispose: () -> Unit, partHeaders: Headers) : PartData(dispose, partHeaders) {
      public final val provider: () -> Source

      init {
         this.provider = provider;
      }
   }

   public class FileItem(provider: () -> ByteReadChannel, dispose: () -> Unit, partHeaders: Headers) : PartData(dispose, partHeaders) {
      public final val provider: () -> ByteReadChannel
      public final val originalFileName: String?

      init {
         this.provider = provider;
         val var10001: ContentDisposition = this.getContentDisposition();
         this.originalFileName = if (var10001 != null) var10001.parameter("filename") else null;
      }
   }

   public class FormItem(value: String, dispose: () -> Unit, partHeaders: Headers) : PartData(dispose, partHeaders) {
      public final val value: String

      init {
         this.value = value;
      }
   }
}
