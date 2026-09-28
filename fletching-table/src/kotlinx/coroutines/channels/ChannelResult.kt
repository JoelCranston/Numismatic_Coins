package kotlinx.coroutines.channels

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.InternalCoroutinesApi

@JvmInline
@SourceDebugExtension(["SMAP\nChannel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Channel.kt\nkotlinx/coroutines/channels/ChannelResult\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1485:1\n1#2:1486\n*E\n"])
public inline class ChannelResult<T> {
   @PublishedApi
   internal final val holder: Any?

   public final val isSuccess: Boolean
      public final get() {
         return arg0 !is ChannelResult.Failed;
      }


   public final val isFailure: Boolean
      public final get() {
         return arg0 is ChannelResult.Failed;
      }


   public final val isClosed: Boolean
      public final get() {
         return arg0 is ChannelResult.Closed;
      }


   @JvmStatic
   public fun getOrNull(): Any? {
      return (T)(if (arg0 !is ChannelResult.Failed) arg0 else null);
   }

   @JvmStatic
   public fun getOrThrow(): Any {
      if (arg0 !is ChannelResult.Failed) {
         return (T)arg0;
      } else if (arg0 is ChannelResult.Closed) {
         if ((arg0 as ChannelResult.Closed).cause == null) {
            throw new IllegalStateException("Trying to call 'getOrThrow' on a channel closed without a cause".toString());
         } else {
            throw (arg0 as ChannelResult.Closed).cause;
         }
      } else {
         throw new IllegalStateException("Trying to call 'getOrThrow' on a failed result of a non-closed channel".toString());
      }
   }

   @JvmStatic
   public fun exceptionOrNull(): Throwable? {
      return if ((arg0 as? ChannelResult.Closed) != null) (arg0 as? ChannelResult.Closed).cause else null;
   }

   @JvmStatic
   public open fun toString(): String {
      return if (arg0 is ChannelResult.Closed) (arg0 as ChannelResult.Closed).toString() else "Value($arg0)";
   }

   override fun toString(): java.lang.String {
      return toString-impl(this.holder);
   }

   @JvmStatic
   fun `hashCode-impl`(arg0: Any): Int {
      return if (arg0 == null) 0 else arg0.hashCode();
   }

   public override fun hashCode(): Int {
      return hashCode-impl(this.holder);
   }

   @JvmStatic
   fun `equals-impl`(arg0: Any, other: Any): Boolean {
      if (other !is ChannelResult) {
         return false;
      } else {
         return arg0 == (other as ChannelResult).unbox-impl();
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      return equals-impl(this.holder, other);
   }

   @PublishedApi
   @JvmStatic
   fun <T> `constructor-impl`(holder: Any?): Any {
      return holder;
   }

   @JvmStatic
   fun `equals-impl0`(p1: Any, p2: Any): Boolean {
      return p1 == p2;
   }

   internal class Closed(cause: Throwable?) : ChannelResult.Failed {
      public final val cause: Throwable?

      init {
         this.cause = cause;
      }

      public override operator fun equals(other: Any?): Boolean {
         return other is ChannelResult.Closed && this.cause == (other as ChannelResult.Closed).cause;
      }

      public override fun hashCode(): Int {
         return if (this.cause != null) this.cause.hashCode() else 0;
      }

      public override fun toString(): String {
         return "Closed(${this.cause})";
      }
   }

   @InternalCoroutinesApi
   public companion object {
      private final val failed: kotlinx.coroutines.channels.ChannelResult.Failed

      @InternalCoroutinesApi
      public fun <E> success(value: E): ChannelResult<E> {
         return ChannelResult.constructor-impl(value);
      }

      @InternalCoroutinesApi
      public fun <E> failure(): ChannelResult<E> {
         return ChannelResult.constructor-impl(ChannelResult.access$getFailed$cp());
      }

      @InternalCoroutinesApi
      public fun <E> closed(cause: Throwable?): ChannelResult<E> {
         return ChannelResult.constructor-impl(new ChannelResult.Closed(cause));
      }
   }

   internal open class Failed {
      public override fun toString(): String {
         return "Failed";
      }
   }
}
