@file:SourceDebugExtension(["SMAP\nHttpRequestRetry.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpRequestRetry.kt\nio/ktor/client/plugins/HttpRequestRetryKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Attributes.kt\nio/ktor/util/AttributesKt\n+ 4 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,497:1\n1#2:498\n21#3:499\n21#3:509\n21#3:519\n21#3:529\n21#3:539\n69#4:500\n84#4,8:501\n69#4:510\n84#4,8:511\n69#4:520\n84#4,8:521\n69#4:530\n84#4,8:531\n69#4:540\n84#4,8:541\n*S KotlinDebug\n*F\n+ 1 HttpRequestRetry.kt\nio/ktor/client/plugins/HttpRequestRetryKt\n*L\n453#1:499\n456#1:509\n461#1:519\n466#1:529\n471#1:539\n453#1:500\n453#1:501,8\n456#1:510\n456#1:511,8\n461#1:520\n461#1:521,8\n466#1:530\n466#1:531,8\n471#1:540\n471#1:541,8\n*E\n"])

package io.ktor.client.plugins

import io.ktor.client.call.HttpClientCall
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.plugins.HttpRequestRetryKt.HttpRequestRetry.1
import io.ktor.client.plugins.HttpRequestRetryKt.HttpRequestRetry.2.2
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.ClientPluginBuilder
import io.ktor.client.plugins.api.CreatePluginUtilsKt
import io.ktor.client.plugins.api.Send
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.ExceptionUtilsJvmKt
import io.ktor.events.EventDefinition
import io.ktor.util.AttributeKey
import io.ktor.util.logging.KtorSimpleLoggerJvmKt
import java.net.SocketTimeoutException
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function2
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Job
import org.slf4j.Logger

private final val LOGGER: Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpRequestRetry")
public final val HttpRequestRetryEvent: EventDefinition<HttpRetryEventData> = new EventDefinition()
public final val HttpRequestRetry: ClientPlugin<HttpRequestRetryConfig> =
   CreatePluginUtilsKt.createClientPlugin("RetryFeature", 1.INSTANCE, HttpRequestRetryKt::HttpRequestRetry$lambda$0)
   internal final val MaxRetriesPerRequestAttributeKey: AttributeKey<Int>
private final val ShouldRetryPerRequestAttributeKey: AttributeKey<(HttpRetryShouldRetryContext, HttpRequest, HttpResponse) -> Boolean>
private final val ShouldRetryOnExceptionPerRequestAttributeKey: AttributeKey<(HttpRetryShouldRetryContext, HttpRequestBuilder, Throwable) -> Boolean>
private final val ModifyRequestPerRequestAttributeKey: AttributeKey<(HttpRetryModifyRequestContext, HttpRequestBuilder) -> Unit>
private final val RetryDelayPerRequestAttributeKey: AttributeKey<(HttpRetryDelayContext, Int) -> Long>

public fun HttpRequestBuilder.retry(block: (HttpRequestRetryConfig) -> Unit) {
   val var3: HttpRequestRetryConfig = new HttpRequestRetryConfig();
   block.invoke(var3);
   `$this$retry`.getAttributes().put(ShouldRetryPerRequestAttributeKey, var3.getShouldRetry$ktor_client_core());
   `$this$retry`.getAttributes().put(ShouldRetryOnExceptionPerRequestAttributeKey, var3.getShouldRetryOnException$ktor_client_core());
   `$this$retry`.getAttributes().put(RetryDelayPerRequestAttributeKey, var3.getDelayMillis$ktor_client_core());
   `$this$retry`.getAttributes().put(MaxRetriesPerRequestAttributeKey, var3.getMaxRetries());
   `$this$retry`.getAttributes().put(ModifyRequestPerRequestAttributeKey, var3.getModifyRequest());
}

private fun Throwable.isTimeoutException(): Boolean {
   val exception: java.lang.Throwable = ExceptionUtilsJvmKt.unwrapCancellationException(`$this$isTimeoutException`);
   return exception is HttpRequestTimeoutException || exception is ConnectTimeoutException || exception is SocketTimeoutException;
}

private suspend fun HttpResponse.throwOnInvalidResponseBody(): Boolean {
   // $VF: Couldn't be decompiled
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   // java.lang.IndexOutOfBoundsException: Index 2 out of bounds for length 0
   //   at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:100)
   //   at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:106)
   //   at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:302)
   //   at java.base/java.util.Objects.checkIndex(Objects.java:365)
   //   at java.base/java.util.ArrayList.remove(ArrayList.java:552)
   //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.removeExceptionInstructionsEx(FinallyProcessor.java:1051)
   //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.verifyFinallyEx(FinallyProcessor.java:501)
   //   at org.jetbrains.java.decompiler.modules.decompiler.FinallyProcessor.iterateGraph(FinallyProcessor.java:90)
   //
   // Bytecode:
   // 000: aload 1
   // 001: instanceof io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1
   // 004: ifeq 027
   // 007: aload 1
   // 008: checkcast io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1
   // 00b: astore 11
   // 00d: aload 11
   // 00f: getfield io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1.label I
   // 012: ldc -2147483648
   // 014: iand
   // 015: ifeq 027
   // 018: aload 11
   // 01a: dup
   // 01b: getfield io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1.label I
   // 01e: ldc -2147483648
   // 020: isub
   // 021: putfield io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1.label I
   // 024: goto 031
   // 027: new io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1
   // 02a: dup
   // 02b: aload 1
   // 02c: invokespecial io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1.<init> (Lkotlin/coroutines/Continuation;)V
   // 02f: astore 11
   // 031: aload 11
   // 033: getfield io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1.result Ljava/lang/Object;
   // 036: astore 10
   // 038: invokestatic kotlin/coroutines/intrinsics/IntrinsicsKt.getCOROUTINE_SUSPENDED ()Ljava/lang/Object;
   // 03b: astore 12
   // 03d: aload 11
   // 03f: getfield io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1.label I
   // 042: tableswitch 329 0 1 22 87
   // 058: aload 10
   // 05a: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 05d: aload 0
   // 05e: invokestatic io/ktor/client/plugins/DoubleReceivePluginKt.isSaved (Lio/ktor/client/statement/HttpResponse;)Z
   // 061: ifeq 186
   // 064: aload 0
   // 065: invokevirtual io/ktor/client/statement/HttpResponse.getRawContent ()Lio/ktor/utils/io/ByteReadChannel;
   // 068: astore 2
   // 069: bipush 0
   // 06a: istore 3
   // 06b: nop
   // 06c: aload 2
   // 06d: bipush 0
   // 06e: aload 11
   // 070: bipush 1
   // 071: aconst_null
   // 072: aload 11
   // 074: aload 0
   // 075: invokestatic kotlin/coroutines/jvm/internal/SpillingKt.nullOutSpilledVariable (Ljava/lang/Object;)Ljava/lang/Object;
   // 078: putfield io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1.L$0 Ljava/lang/Object;
   // 07b: aload 11
   // 07d: aload 2
   // 07e: putfield io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1.L$1 Ljava/lang/Object;
   // 081: aload 11
   // 083: iload 3
   // 084: putfield io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1.I$0 I
   // 087: aload 11
   // 089: bipush 1
   // 08a: putfield io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1.label I
   // 08d: invokestatic io/ktor/utils/io/ByteReadChannel.awaitContent$default (Lio/ktor/utils/io/ByteReadChannel;ILkotlin/coroutines/Continuation;ILjava/lang/Object;)Ljava/lang/Object;
   // 090: dup
   // 091: aload 12
   // 093: if_acmpne 0b9
   // 096: aload 12
   // 098: areturn
   // 099: aload 11
   // 09b: getfield io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1.I$0 I
   // 09e: istore 3
   // 09f: aload 11
   // 0a1: getfield io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1.L$1 Ljava/lang/Object;
   // 0a4: checkcast io/ktor/utils/io/ByteReadChannel
   // 0a7: astore 2
   // 0a8: aload 11
   // 0aa: getfield io/ktor/client/plugins/HttpRequestRetryKt$throwOnInvalidResponseBody$1.L$0 Ljava/lang/Object;
   // 0ad: checkcast io/ktor/client/statement/HttpResponse
   // 0b0: astore 0
   // 0b1: nop
   // 0b2: aload 10
   // 0b4: invokestatic kotlin/ResultKt.throwOnFailure (Ljava/lang/Object;)V
   // 0b7: aload 10
   // 0b9: checkcast java/lang/Boolean
   // 0bc: invokevirtual java/lang/Boolean.booleanValue ()Z
   // 0bf: istore 4
   // 0c1: aload 2
   // 0c2: astore 5
   // 0c4: nop
   // 0c5: getstatic kotlin/Result.Companion Lkotlin/Result$Companion;
   // 0c8: pop
   // 0c9: aload 5
   // 0cb: astore 6
   // 0cd: bipush 0
   // 0ce: istore 7
   // 0d0: aload 6
   // 0d2: invokestatic io/ktor/utils/io/ByteReadChannelKt.cancel (Lio/ktor/utils/io/ByteReadChannel;)V
   // 0d5: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 0d8: invokestatic kotlin/Result.constructor-impl (Ljava/lang/Object;)Ljava/lang/Object;
   // 0db: astore 6
   // 0dd: goto 0f0
   // 0e0: astore 7
   // 0e2: getstatic kotlin/Result.Companion Lkotlin/Result$Companion;
   // 0e5: pop
   // 0e6: aload 7
   // 0e8: invokestatic kotlin/ResultKt.createFailure (Ljava/lang/Throwable;)Ljava/lang/Object;
   // 0eb: invokestatic kotlin/Result.constructor-impl (Ljava/lang/Object;)Ljava/lang/Object;
   // 0ee: astore 6
   // 0f0: aload 6
   // 0f2: astore 5
   // 0f4: aload 5
   // 0f6: invokestatic kotlin/Result.exceptionOrNull-impl (Ljava/lang/Object;)Ljava/lang/Throwable;
   // 0f9: dup
   // 0fa: ifnull 115
   // 0fd: astore 6
   // 0ff: aload 6
   // 101: astore 7
   // 103: bipush 0
   // 104: istore 8
   // 106: getstatic io/ktor/client/plugins/HttpRequestRetryKt.LOGGER Lorg/slf4j/Logger;
   // 109: ldc "Failed to close response body channel"
   // 10b: aload 7
   // 10d: invokeinterface org/slf4j/Logger.debug (Ljava/lang/String;Ljava/lang/Throwable;)V 3
   // 112: goto 116
   // 115: pop
   // 116: goto 173
   // 119: astore 5
   // 11b: aload 2
   // 11c: astore 6
   // 11e: nop
   // 11f: getstatic kotlin/Result.Companion Lkotlin/Result$Companion;
   // 122: pop
   // 123: aload 6
   // 125: astore 7
   // 127: bipush 0
   // 128: istore 8
   // 12a: aload 7
   // 12c: invokestatic io/ktor/utils/io/ByteReadChannelKt.cancel (Lio/ktor/utils/io/ByteReadChannel;)V
   // 12f: getstatic kotlin/Unit.INSTANCE Lkotlin/Unit;
   // 132: invokestatic kotlin/Result.constructor-impl (Ljava/lang/Object;)Ljava/lang/Object;
   // 135: astore 7
   // 137: goto 14a
   // 13a: astore 8
   // 13c: getstatic kotlin/Result.Companion Lkotlin/Result$Companion;
   // 13f: pop
   // 140: aload 8
   // 142: invokestatic kotlin/ResultKt.createFailure (Ljava/lang/Throwable;)Ljava/lang/Object;
   // 145: invokestatic kotlin/Result.constructor-impl (Ljava/lang/Object;)Ljava/lang/Object;
   // 148: astore 7
   // 14a: aload 7
   // 14c: astore 6
   // 14e: aload 6
   // 150: invokestatic kotlin/Result.exceptionOrNull-impl (Ljava/lang/Object;)Ljava/lang/Throwable;
   // 153: dup
   // 154: ifnull 16f
   // 157: astore 7
   // 159: aload 7
   // 15b: astore 8
   // 15d: bipush 0
   // 15e: istore 9
   // 160: getstatic io/ktor/client/plugins/HttpRequestRetryKt.LOGGER Lorg/slf4j/Logger;
   // 163: ldc "Failed to close response body channel"
   // 165: aload 8
   // 167: invokeinterface org/slf4j/Logger.debug (Ljava/lang/String;Ljava/lang/Throwable;)V 3
   // 16c: goto 170
   // 16f: pop
   // 170: aload 5
   // 172: athrow
   // 173: iload 4
   // 175: ifeq 17c
   // 178: bipush 1
   // 179: goto 17d
   // 17c: bipush 0
   // 17d: nop
   // 17e: nop
   // 17f: ifeq 186
   // 182: bipush 1
   // 183: goto 187
   // 186: bipush 0
   // 187: invokestatic kotlin/coroutines/jvm/internal/Boxing.boxBoolean (Z)Ljava/lang/Boolean;
   // 18a: areturn
   // 18b: new java/lang/IllegalStateException
   // 18e: dup
   // 18f: ldc_w "call to 'resume' before 'invoke' with coroutine"
   // 192: invokespecial java/lang/IllegalStateException.<init> (Ljava/lang/String;)V
   // 195: athrow
}

fun ClientPluginBuilder.`HttpRequestRetry$lambda$0`(): Unit {
   val shouldRetry: Function3 = (`$this$createClientPlugin`.getPluginConfig() as HttpRequestRetryConfig).getShouldRetry$ktor_client_core();
   val shouldRetryOnException: Function3 = (`$this$createClientPlugin`.getPluginConfig() as HttpRequestRetryConfig)
      .getShouldRetryOnException$ktor_client_core();
   val delayMillis: Function2 = (`$this$createClientPlugin`.getPluginConfig() as HttpRequestRetryConfig).getDelayMillis$ktor_client_core();
   val delay: Function2 = (`$this$createClientPlugin`.getPluginConfig() as HttpRequestRetryConfig).getDelay$ktor_client_core();
   val maxRetries: Int = (`$this$createClientPlugin`.getPluginConfig() as HttpRequestRetryConfig).getMaxRetries();
   val modifyRequest: Function2 = (`$this$createClientPlugin`.getPluginConfig() as HttpRequestRetryConfig).getModifyRequest();
   `$this$createClientPlugin`.onRequest(new io.ktor.client.plugins.HttpRequestRetryKt.HttpRequestRetry.2.1(maxRetries, null));
   `$this$createClientPlugin`.on(
      Send.INSTANCE, new 2(shouldRetry, shouldRetryOnException, maxRetries, delayMillis, modifyRequest, `$this$createClientPlugin`, delay, null)
   );
   return Unit.INSTANCE;
}

fun `HttpRequestRetry$lambda$0$shouldRetry`(
   retryCount: Int, maxRetries: Int, shouldRetry: (HttpRetryShouldRetryContext?, HttpRequest?, HttpResponse?) -> java.lang.Boolean, call: HttpClientCall
): Boolean {
   return retryCount < maxRetries
      && shouldRetry.invoke(new HttpRetryShouldRetryContext(retryCount + 1), call.getRequest(), call.getResponse()) as java.lang.Boolean;
}

fun `HttpRequestRetry$lambda$0$shouldRetryOnException`(
   retryCount: Int,
   maxRetries: Int,
   shouldRetry: (HttpRetryShouldRetryContext?, HttpRequestBuilder?, java.lang.Throwable?) -> java.lang.Boolean,
   subRequest: HttpRequestBuilder,
   cause: java.lang.Throwable
): Boolean {
   return retryCount < maxRetries && shouldRetry.invoke(new HttpRetryShouldRetryContext(retryCount + 1), subRequest, cause) as java.lang.Boolean;
}

fun `HttpRequestRetry$lambda$0$prepareRequest`(request: HttpRequestBuilder): HttpRequestBuilder {
   val subRequest: HttpRequestBuilder = new HttpRequestBuilder().takeFrom(request);
   request.getExecutionContext().invokeOnCompletion(HttpRequestRetryKt::HttpRequestRetry$lambda$0$prepareRequest$0);
   return subRequest;
}

fun `HttpRequestRetry$lambda$0$prepareRequest$0`(`$subRequest`: HttpRequestBuilder, cause: java.lang.Throwable): Unit {
   val var10000: Job = `$subRequest`.getExecutionContext();
   val subRequestJob: CompletableJob = var10000 as CompletableJob;
   if (cause == null) {
      subRequestJob.complete();
   } else {
      subRequestJob.completeExceptionally(cause);
   }

   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$isTimeoutException`(`$receiver`: java.lang.Throwable): Boolean {
   return isTimeoutException(`$receiver`);
}

@JvmSynthetic
fun `access$throwOnInvalidResponseBody`(`$receiver`: HttpResponse, `$completion`: Continuation): Any {
   return throwOnInvalidResponseBody(`$receiver`, `$completion`);
}

@JvmSynthetic
fun `access$getShouldRetryPerRequestAttributeKey$p`(): AttributeKey {
   return ShouldRetryPerRequestAttributeKey;
}

@JvmSynthetic
fun `access$getShouldRetryOnExceptionPerRequestAttributeKey$p`(): AttributeKey {
   return ShouldRetryOnExceptionPerRequestAttributeKey;
}

@JvmSynthetic
fun `access$getRetryDelayPerRequestAttributeKey$p`(): AttributeKey {
   return RetryDelayPerRequestAttributeKey;
}

@JvmSynthetic
fun `access$getModifyRequestPerRequestAttributeKey$p`(): AttributeKey {
   return ModifyRequestPerRequestAttributeKey;
}

@JvmSynthetic
fun `access$HttpRequestRetry$lambda$0$prepareRequest`(request: HttpRequestBuilder): HttpRequestBuilder {
   return HttpRequestRetry$lambda$0$prepareRequest(request);
}

@JvmSynthetic
fun `access$HttpRequestRetry$lambda$0$shouldRetry`(retryCount: Int, maxRetries: Int, shouldRetry: Function3, call: HttpClientCall): Boolean {
   return HttpRequestRetry$lambda$0$shouldRetry(retryCount, maxRetries, shouldRetry, call);
}

@JvmSynthetic
fun `access$HttpRequestRetry$lambda$0$shouldRetryOnException`(
   retryCount: Int, maxRetries: Int, shouldRetry: Function3, subRequest: HttpRequestBuilder, cause: java.lang.Throwable
): Boolean {
   return HttpRequestRetry$lambda$0$shouldRetryOnException(retryCount, maxRetries, shouldRetry, subRequest, cause);
}

@JvmSynthetic
fun `access$getLOGGER$p`(): Logger {
   return LOGGER;
}
