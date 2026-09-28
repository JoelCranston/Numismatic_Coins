@file:SourceDebugExtension(["SMAP\nTickerChannels.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TickerChannels.kt\nkotlinx/coroutines/channels/TickerChannelsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,108:1\n1#2:109\n*E\n"])

package kotlinx.coroutines.channels

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.AbstractTimeSource
import kotlinx.coroutines.AbstractTimeSourceKt
import kotlinx.coroutines.DelayKt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.EventLoop_commonKt
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.TickerChannelsKt.fixedPeriodTicker.1
import kotlinx.coroutines.channels.TickerChannelsKt.ticker.3

@ObsoleteCoroutinesApi
public fun ticker(
   delayMillis: Long,
   initialDelayMillis: Long = delayMillis,
   context: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
   mode: TickerMode = TickerMode.FIXED_PERIOD
): ReceiveChannel<Unit> {
   if (delayMillis < 0L) {
      throw new IllegalArgumentException(("Expected non-negative delay, but has $delayMillis ms").toString());
   } else if (initialDelayMillis < 0L) {
      throw new IllegalArgumentException(("Expected non-negative initial delay, but has $initialDelayMillis ms").toString());
   } else {
      return ProduceKt.produce(GlobalScope.INSTANCE, Dispatchers.getUnconfined().plus(context), 0, new 3(mode, delayMillis, initialDelayMillis, null));
   }
}

@JvmSynthetic
fun `ticker$default`(var0: Long, var2: Long, var4: CoroutineContext, var5: TickerMode, var6: Int, var7: Any): ReceiveChannel {
   if ((var6 and 2) != 0) {
      var2 = var0;
   }

   if ((var6 and 4) != 0) {
      var4 = EmptyCoroutineContext.INSTANCE;
   }

   if ((var6 and 8) != 0) {
      var5 = TickerMode.FIXED_PERIOD;
   }

   return ticker(var0, var2, var4, var5);
}

private suspend fun fixedPeriodTicker(delayMillis: Long, initialDelayMillis: Long, channel: SendChannel<Unit>) {
   var `$continuation`: Continuation;
   label93: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label93;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var18: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var deadline: Long;
   var delayNs: Long;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         val var26: AbstractTimeSource = AbstractTimeSourceKt.access$getTimeSource$p();
         deadline = (if (var26 != null) var26.nanoTime() else System.nanoTime()) + EventLoop_commonKt.delayToNanos(initialDelayMillis);
         `$continuation`.L$0 = channel;
         `$continuation`.J$0 = delayMillis;
         `$continuation`.J$1 = deadline;
         `$continuation`.label = 1;
         if (DelayKt.delay(initialDelayMillis, `$continuation`) === var18) {
            return var18;
         }

         delayNs = EventLoop_commonKt.delayToNanos(delayMillis);
         break;
      case 1:
         deadline = `$continuation`.J$1;
         delayMillis = `$continuation`.J$0;
         channel = `$continuation`.L$0 as SendChannel;
         ResultKt.throwOnFailure(`$result`);
         delayNs = EventLoop_commonKt.delayToNanos(delayMillis);
         break;
      case 2:
         delayNs = `$continuation`.J$1;
         deadline = `$continuation`.J$0;
         channel = `$continuation`.L$0 as SendChannel;
         ResultKt.throwOnFailure(`$result`);
         val var10000: AbstractTimeSource = AbstractTimeSourceKt.access$getTimeSource$p();
         val now: Long = if (var10000 != null) var10000.nanoTime() else System.nanoTime();
         val nextDelay: Long = RangesKt.coerceAtLeast(deadline - now, 0L);
         if (nextDelay == 0L) {
            if (delayNs != 0L) {
               val adjustedDelay: Long = delayNs - (now - deadline) % delayNs;
               deadline = now + (delayNs - (now - deadline) % delayNs);
               val var23: Long = EventLoop_commonKt.delayNanosToMillis(adjustedDelay);
               `$continuation`.L$0 = channel;
               `$continuation`.J$0 = deadline;
               `$continuation`.J$1 = delayNs;
               `$continuation`.label = 3;
               if (DelayKt.delay(var23, `$continuation`) === var18) {
                  return var18;
               }
            } else {
               val var24: Long = EventLoop_commonKt.delayNanosToMillis(nextDelay);
               `$continuation`.L$0 = channel;
               `$continuation`.J$0 = deadline;
               `$continuation`.J$1 = delayNs;
               `$continuation`.label = 4;
               if (DelayKt.delay(var24, `$continuation`) === var18) {
                  return var18;
               }
            }
         } else {
            val var25: Long = EventLoop_commonKt.delayNanosToMillis(nextDelay);
            `$continuation`.L$0 = channel;
            `$continuation`.J$0 = deadline;
            `$continuation`.J$1 = delayNs;
            `$continuation`.label = 4;
            if (DelayKt.delay(var25, `$continuation`) === var18) {
               return var18;
            }
         }
         break;
      case 3:
         delayNs = `$continuation`.J$1;
         deadline = `$continuation`.J$0;
         channel = `$continuation`.L$0 as SendChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      case 4:
         delayNs = `$continuation`.J$1;
         deadline = `$continuation`.J$0;
         channel = `$continuation`.L$0 as SendChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (true) {
      deadline += delayNs;
      val var10001: Unit = Unit.INSTANCE;
      `$continuation`.L$0 = channel;
      `$continuation`.J$0 = deadline;
      `$continuation`.J$1 = delayNs;
      `$continuation`.label = 2;
      if (channel.send(var10001, `$continuation`) === var18) {
         return var18;
      }

      val var27: AbstractTimeSource = AbstractTimeSourceKt.access$getTimeSource$p();
      val var20: Long = if (var27 != null) var27.nanoTime() else System.nanoTime();
      val var21: Long = RangesKt.coerceAtLeast(deadline - var20, 0L);
      if (var21 == 0L) {
         if (delayNs != 0L) {
            val var22: Long = delayNs - (var20 - deadline) % delayNs;
            deadline = var20 + (delayNs - (var20 - deadline) % delayNs);
            val var28: Long = EventLoop_commonKt.delayNanosToMillis(var22);
            `$continuation`.L$0 = channel;
            `$continuation`.J$0 = deadline;
            `$continuation`.J$1 = delayNs;
            `$continuation`.label = 3;
            if (DelayKt.delay(var28, `$continuation`) === var18) {
               return var18;
            }
         } else {
            val var29: Long = EventLoop_commonKt.delayNanosToMillis(var21);
            `$continuation`.L$0 = channel;
            `$continuation`.J$0 = deadline;
            `$continuation`.J$1 = delayNs;
            `$continuation`.label = 4;
            if (DelayKt.delay(var29, `$continuation`) === var18) {
               return var18;
            }
         }
      } else {
         val var30: Long = EventLoop_commonKt.delayNanosToMillis(var21);
         `$continuation`.L$0 = channel;
         `$continuation`.J$0 = deadline;
         `$continuation`.J$1 = delayNs;
         `$continuation`.label = 4;
         if (DelayKt.delay(var30, `$continuation`) === var18) {
            return var18;
         }
      }
   }
}

private suspend fun fixedDelayTicker(delayMillis: Long, initialDelayMillis: Long, channel: SendChannel<Unit>) {
   var `$continuation`: Continuation;
   label47: {
      if (`$completion` is kotlinx.coroutines.channels.TickerChannelsKt.fixedDelayTicker.1) {
         `$continuation` = `$completion` as kotlinx.coroutines.channels.TickerChannelsKt.fixedDelayTicker.1;
         if (((`$completion` as kotlinx.coroutines.channels.TickerChannelsKt.fixedDelayTicker.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label47;
         }
      }

      `$continuation` = new kotlinx.coroutines.channels.TickerChannelsKt.fixedDelayTicker.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$continuation`.L$0 = channel;
         `$continuation`.J$0 = delayMillis;
         `$continuation`.label = 1;
         if (DelayKt.delay(initialDelayMillis, `$continuation`) === var8) {
            return var8;
         }
         break;
      case 1:
         delayMillis = `$continuation`.J$0;
         channel = `$continuation`.L$0 as SendChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      case 2:
         delayMillis = `$continuation`.J$0;
         channel = `$continuation`.L$0 as SendChannel;
         ResultKt.throwOnFailure(`$result`);
         `$continuation`.L$0 = channel;
         `$continuation`.J$0 = delayMillis;
         `$continuation`.label = 3;
         if (DelayKt.delay(delayMillis, `$continuation`) === var8) {
            return var8;
         }
         break;
      case 3:
         delayMillis = `$continuation`.J$0;
         channel = `$continuation`.L$0 as SendChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   do {
      val var10001: Unit = Unit.INSTANCE;
      `$continuation`.L$0 = channel;
      `$continuation`.J$0 = delayMillis;
      `$continuation`.label = 2;
      if (channel.send(var10001, `$continuation`) === var8) {
         return var8;
      }

      `$continuation`.L$0 = channel;
      `$continuation`.J$0 = delayMillis;
      `$continuation`.label = 3;
   } while (DelayKt.delay(delayMillis, $continuation) != var8);

   return var8;
}

@JvmSynthetic
fun `access$fixedPeriodTicker`(delayMillis: Long, initialDelayMillis: Long, channel: SendChannel, `$completion`: Continuation): Any {
   return fixedPeriodTicker(delayMillis, initialDelayMillis, channel, `$completion`);
}

@JvmSynthetic
fun `access$fixedDelayTicker`(delayMillis: Long, initialDelayMillis: Long, channel: SendChannel, `$completion`: Continuation): Any {
   return fixedDelayTicker(delayMillis, initialDelayMillis, channel, `$completion`);
}
