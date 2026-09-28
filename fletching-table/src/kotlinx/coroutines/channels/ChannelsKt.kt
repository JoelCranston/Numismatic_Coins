package kotlinx.coroutines.channels

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.ObsoleteCoroutinesApi

// $VF: Class flags could not be determined
internal class ChannelsKt {
   @JvmStatic
   public java.lang.String DEFAULT_CLOSE_MESSAGE = "Channel was closed";

   @JvmStatic
   fun <E> SendChannel<? super E>.trySendBlocking(element: E): Any {
      return ChannelsKt__ChannelsKt.trySendBlocking(`$this$trySendBlocking`, element);
   }

   @JvmStatic
   fun <E, R> ReceiveChannel<? extends E>.consume(block: (ReceiveChannel<? extends E>?) -> R): R {
      return ChannelsKt__Channels_commonKt.consume(`$this$consume`, block);
   }

   @JvmStatic
   fun <E> ReceiveChannel<? extends E>.consumeEach(action: (E?) -> Unit, `$completion`: Continuation<? super Unit>): Any? {
      return ChannelsKt__Channels_commonKt.consumeEach(`$this$consumeEach`, action, `$completion`);
   }

   @JvmStatic
   fun <E> ReceiveChannel<? extends E>.toList(`$completion`: Continuation<? super java.utilList<? extends E>>): Any? {
      return ChannelsKt__Channels_commonKt.toList(`$this$toList`, `$completion`);
   }

   @PublishedApi
   @JvmStatic
   fun ReceiveChannel<?>.cancelConsumed(cause: java.lang.Throwable?) {
      ChannelsKt__Channels_commonKt.cancelConsumed(`$this$cancelConsumed`, cause);
   }

   /** @deprecated */
   @Deprecated(message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported", level = DeprecationLevel.ERROR)
   @ObsoleteCoroutinesApi
   @JvmStatic
   fun <E, R> BroadcastChannel<E>.consume(block: (ReceiveChannel<? extends E>?) -> R): R {
      return ChannelsKt__DeprecatedKt.consume(`$this$consume`, block);
   }

   /** @deprecated */
   @Deprecated(message = "BroadcastChannel is deprecated in the favour of SharedFlow and is no longer supported", level = DeprecationLevel.ERROR)
   @JvmStatic
   fun <E> BroadcastChannel<E>.consumeEach(action: (E?) -> Unit, `$completion`: Continuation<? super Unit>): Any {
      return ChannelsKt__DeprecatedKt.consumeEach(`$this$consumeEach`, action, `$completion`);
   }

   @PublishedApi
   @JvmStatic
   fun consumesAll(vararg channels: ReceiveChannel<?>): (java.lang.Throwable?) -> Unit {
      return ChannelsKt__DeprecatedKt.consumesAll(channels);
   }

   @PublishedApi
   @JvmStatic
   fun <E> ReceiveChannel<? extends E>.filter(context: CoroutineContext, predicate: (E?, Continuation<? super java.lang.Boolean>?) -> Any): ReceiveChannel<E> {
      return ChannelsKt__DeprecatedKt.filter(`$this$filter`, context, predicate);
   }

   @PublishedApi
   @JvmStatic
   fun <E> ReceiveChannel<? extends E>.filterNotNull(): ReceiveChannel<E> {
      return ChannelsKt__DeprecatedKt.filterNotNull(`$this$filterNotNull`);
   }

   @PublishedApi
   @JvmStatic
   fun <E, C extends SendChannel<? super E>> ReceiveChannel<? extends E>.toChannel(destination: C, `$completion`: Continuation<? super C>): Any? {
      return ChannelsKt__DeprecatedKt.toChannel(`$this$toChannel`, (C)destination, `$completion`);
   }

   @PublishedApi
   @JvmStatic
   fun <E, C extends java.util.Collection<? super E>> ReceiveChannel<? extends E>.toCollection(destination: C, `$completion`: Continuation<? super C>): Any? {
      return ChannelsKt__DeprecatedKt.toCollection(`$this$toCollection`, (C)destination, `$completion`);
   }

   @PublishedApi
   @JvmStatic
   fun <K, V, M extends java.util.Map<? super K, ? super V>> ReceiveChannel<? extends Pair<? extends K, ? extends V>>.toMap(
      destination: M, `$completion`: Continuation<? super M>
   ): Any? {
      return ChannelsKt__DeprecatedKt.toMap(`$this$toMap`, (M)destination, `$completion`);
   }

   @PublishedApi
   @JvmStatic
   fun <E, R> ReceiveChannel<? extends E>.map(context: CoroutineContext, transform: (E?, Continuation<? super R>?) -> Any): ReceiveChannel<R> {
      return ChannelsKt__DeprecatedKt.map(`$this$map`, context, transform);
   }

   @PublishedApi
   @JvmStatic
   fun <E, R> ReceiveChannel<? extends E>.mapIndexed(context: CoroutineContext, transform: (Int?, E?, Continuation<? super R>?) -> Any): ReceiveChannel<R> {
      return ChannelsKt__DeprecatedKt.mapIndexed(`$this$mapIndexed`, context, transform);
   }

   @PublishedApi
   @JvmStatic
   fun <E, K> ReceiveChannel<? extends E>.distinctBy(context: CoroutineContext, selector: (E?, Continuation<? super K>?) -> Any): ReceiveChannel<E> {
      return ChannelsKt__DeprecatedKt.distinctBy(`$this$distinctBy`, context, selector);
   }

   @PublishedApi
   @JvmStatic
   fun <E> ReceiveChannel<? extends E>.toMutableSet(`$completion`: Continuation<? super java.utilSet<E>>): Any? {
      return ChannelsKt__DeprecatedKt.toMutableSet(`$this$toMutableSet`, `$completion`);
   }

   @PublishedApi
   @JvmStatic
   fun <E, R, V> ReceiveChannel<? extends E>.zip(other: ReceiveChannel<? extends R>, context: CoroutineContext, transform: (E?, R?) -> V): ReceiveChannel<V> {
      return ChannelsKt__DeprecatedKt.zip(`$this$zip`, other, context, transform);
   }

   @PublishedApi
   @JvmStatic
   fun ReceiveChannel<?>.consumes(): (java.lang.Throwable?) -> Unit {
      return ChannelsKt__DeprecatedKt.consumes(`$this$consumes`);
   }
}
