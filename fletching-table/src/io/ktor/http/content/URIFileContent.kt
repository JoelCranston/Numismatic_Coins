package io.ktor.http.content

import io.ktor.http.ContentType
import io.ktor.util.cio.ByteBufferPoolKt
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.jvm.javaio.ReadingKt
import java.io.InputStream
import java.net.URI
import java.net.URL

public class URIFileContent(uri: URI, contentType: ContentType = ContentType.Companion, contentLength: Long? = null) : OutgoingContent.ReadChannelContent {
   public final val uri: URI
   public open val contentType: ContentType
   public open val contentLength: Long?

   init {
      this.uri = uri;
      this.contentType = contentType;
      this.contentLength = contentLength;
   }

   public constructor(url: URL, contentType: ContentType = ContentType.Companion)  {
      val var10001: URI = url.toURI();
      this(var10001, contentType, null, 4, null);
   }

   public override fun readFrom(): ByteReadChannel {
      val var10000: InputStream = this.uri.toURL().openStream();
      return ReadingKt.toByteReadChannel$default(var10000, null, ByteBufferPoolKt.getKtorDefaultPool(), 1, null);
   }
}
