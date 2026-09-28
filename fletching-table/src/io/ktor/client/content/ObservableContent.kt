package io.ktor.client.content

import io.ktor.client.call.UnsupportedContentTypeException
import io.ktor.client.content.ObservableContent.getContent.1
import io.ktor.client.utils.ByteChannelUtilsKt
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.OutgoingContent
import io.ktor.util.AttributeKey
import io.ktor.utils.io.ByteChannelCtorKt
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.GlobalScope

internal class ObservableContent(delegate: OutgoingContent, callContext: CoroutineContext, listener: ProgressListener) : OutgoingContent.ReadChannelContent {
   private final val delegate: OutgoingContent
   private final val callContext: CoroutineContext
   private final val listener: ProgressListener

   public open val contentType: ContentType?
      public open get() {
         return this.delegate.getContentType();
      }


   public open val contentLength: Long?
      public open get() {
         return this.delegate.getContentLength();
      }


   public open val status: HttpStatusCode?
      public open get() {
         return this.delegate.getStatus();
      }


   public open val headers: Headers
      public open get() {
         return this.delegate.getHeaders();
      }


   init {
      this.delegate = delegate;
      this.callContext = callContext;
      this.listener = listener;
   }

   private fun getContent(delegate: OutgoingContent): ByteReadChannel {
      val var10000: ByteReadChannel;
      if (delegate is OutgoingContent.ContentWrapper) {
         var10000 = this.getContent((delegate as OutgoingContent.ContentWrapper).delegate());
      } else if (delegate is OutgoingContent.ByteArrayContent) {
         var10000 = ByteChannelCtorKt.ByteReadChannel$default((delegate as OutgoingContent.ByteArrayContent).bytes(), 0, 0, 6, null);
      } else {
         if (delegate is OutgoingContent.ProtocolUpgrade) {
            throw new UnsupportedContentTypeException(delegate);
         }

         if (delegate is OutgoingContent.NoContent) {
            var10000 = ByteReadChannel.Companion.getEmpty();
         } else if (delegate is OutgoingContent.ReadChannelContent) {
            var10000 = (delegate as OutgoingContent.ReadChannelContent).readFrom();
         } else {
            if (delegate !is OutgoingContent.WriteChannelContent) {
               throw new NoWhenBranchMatchedException();
            }

            var10000 = ByteWriteChannelOperationsKt.writer(GlobalScope.INSTANCE, this.callContext, true, new 1(delegate, null)).getChannel();
         }
      }

      return var10000;
   }

   public override fun <T : Any> getProperty(key: AttributeKey<Any>): Any? {
      return (T)this.delegate.getProperty(key);
   }

   public override fun <T : Any> setProperty(key: AttributeKey<Any>, value: Any?) {
      this.delegate.setProperty(key, value);
   }

   public override fun readFrom(): ByteReadChannel {
      return ByteChannelUtilsKt.observable(this.getContent(this.delegate), this.callContext, this.getContentLength(), this.listener);
   }
}
