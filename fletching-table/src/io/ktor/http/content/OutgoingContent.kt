package io.ktor.http.content

import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.OutgoingContent.ReadChannelContent.readFrom.1
import io.ktor.util.AttributeKey
import io.ktor.util.Attributes
import io.ktor.util.AttributesJvmKt
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job

@SourceDebugExtension(["SMAP\nOutgoingContent.kt\nKotlin\n*S Kotlin\n*F\n+ 1 OutgoingContent.kt\nio/ktor/http/content/OutgoingContent\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,221:1\n1#2:222\n*E\n"])
public sealed class OutgoingContent protected constructor() {
   public open val contentType: ContentType?
      public open get() {
         return null;
      }


   public open val contentLength: Long?
      public open get() {
         return null;
      }


   public open val status: HttpStatusCode?
      public open get() {
         return null;
      }


   public open val headers: Headers
      public open get() {
         return Headers.Companion.getEmpty();
      }


   private final var extensionProperties: Attributes?

   public open fun <T : Any> getProperty(key: AttributeKey<Any>): Any? {
      return (T)(if (this.extensionProperties != null) this.extensionProperties.getOrNull(key) else null);
   }

   public open fun <T : Any> setProperty(key: AttributeKey<Any>, value: Any?) {
      if (value != null || this.extensionProperties != null) {
         if (value == null) {
            if (this.extensionProperties != null) {
               this.extensionProperties.remove(key);
            }
         } else {
            var var10000: Attributes = this.extensionProperties;
            if (this.extensionProperties == null) {
               var10000 = AttributesJvmKt.Attributes$default(false, 1, null);
            }

            this.extensionProperties = var10000;
            var10000.put(key, value);
         }
      }
   }

   public open fun trailers(): Headers? {
      return null;
   }

   public abstract class ByteArrayContent : OutgoingContent {
      open fun ByteArrayContent() {
         super(null);
      }

      public abstract fun bytes(): ByteArray {
      }
   }

   public abstract class ContentWrapper : OutgoingContent {
      private final val delegate: OutgoingContent

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


      open fun ContentWrapper(delegate: OutgoingContent) {
         super(null);
         this.delegate = delegate;
      }

      public override fun <T : Any> getProperty(key: AttributeKey<Any>): Any? {
         return (T)this.delegate.getProperty(key);
      }

      public override fun <T : Any> setProperty(key: AttributeKey<Any>, value: Any?) {
         this.delegate.setProperty(key, value);
      }

      public fun delegate(): OutgoingContent {
         return this.delegate;
      }

      public abstract fun copy(delegate: OutgoingContent): io.ktor.http.content.OutgoingContent.ContentWrapper {
      }
   }

   public abstract class NoContent : OutgoingContent {
      open fun NoContent() {
         super(null);
      }
   }

   public abstract class ProtocolUpgrade : OutgoingContent {
      public final val status: HttpStatusCode
         public final get() {
            return HttpStatusCode.Companion.getSwitchingProtocols();
         }


      open fun ProtocolUpgrade() {
         super(null);
      }

      public abstract suspend fun upgrade(input: ByteReadChannel, output: ByteWriteChannel, engineContext: CoroutineContext, userContext: CoroutineContext): Job {
      }
   }

   public abstract class ReadChannelContent : OutgoingContent {
      open fun ReadChannelContent() {
         super(null);
      }

      public abstract fun readFrom(): ByteReadChannel {
      }

      public open fun readFrom(range: LongRange): ByteReadChannel {
         return if (range.isEmpty())
            ByteReadChannel.Companion.getEmpty()
            else
            ByteWriteChannelOperationsKt.writer(GlobalScope.INSTANCE, Dispatchers.getUnconfined(), true, new 1(this, range, null)).getChannel();
      }
   }

   public abstract class WriteChannelContent : OutgoingContent {
      open fun WriteChannelContent() {
         super(null);
      }

      public abstract suspend fun writeTo(channel: ByteWriteChannel) {
      }
   }
}
