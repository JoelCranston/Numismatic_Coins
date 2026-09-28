package kotlinx.coroutines.channels

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.channels.ChannelsKt__ChannelsKt.trySendBlocking.2

@SourceDebugExtension(["SMAP\nChannels.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Channels.kt\nkotlinx/coroutines/channels/ChannelsKt__ChannelsKt\n+ 2 Channel.kt\nkotlinx/coroutines/channels/ChannelKt\n*L\n1#1,61:1\n1009#2,2:62\n*S KotlinDebug\n*F\n+ 1 Channels.kt\nkotlinx/coroutines/channels/ChannelsKt__ChannelsKt\n*L\n37#1:62,2\n*E\n"])
@JvmSynthetic
internal class ChannelsKt__ChannelsKt {
   @JvmStatic
   public fun <E> SendChannel<E>.trySendBlocking(element: E): ChannelResult<Unit> {
      val `$this$onSuccess_u2dWpGqRn0$iv`: Any = `$this$trySendBlocking`.trySend-JP2dKIU(element);
      if (`$this$onSuccess_u2dWpGqRn0$iv` !is ChannelResult.Failed) {
         val it: Unit = `$this$onSuccess_u2dWpGqRn0$iv` as Unit;
         return ChannelResult.Companion.success-JP2dKIU(Unit.INSTANCE);
      } else {
         return (BuildersKt.runBlocking$default(null, new 2(`$this$trySendBlocking`, element, null), 1, null) as ChannelResult).unbox-impl();
      }
   }
}
