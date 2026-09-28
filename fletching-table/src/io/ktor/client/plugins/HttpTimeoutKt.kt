package io.ktor.client.plugins

import io.ktor.client.engine.HttpClientEngineCapability
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.TimeoutExceptionsKt
import io.ktor.client.plugins.HttpTimeoutKt.HttpTimeout.2
import io.ktor.client.plugins.HttpTimeoutKt.HttpTimeout.3.1
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.ClientPluginBuilder
import io.ktor.client.plugins.api.CreatePluginUtilsKt
import io.ktor.client.plugins.api.Send
import io.ktor.client.plugins.sse.SSEClientContent
import io.ktor.client.request.ClientUpgradeContent
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestData
import io.ktor.client.utils.ExceptionUtilsJvmKt
import io.ktor.http.URLProtocolKt
import io.ktor.util.logging.KtorSimpleLoggerJvmKt
import io.ktor.utils.io.InternalAPI
import java.net.SocketTimeoutException
import java.util.concurrent.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import org.slf4j.Logger

private final val LOGGER: Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpTimeout")
public final val HttpTimeout: ClientPlugin<HttpTimeoutConfig> =
   CreatePluginUtilsKt.createClientPlugin("HttpTimeout", 2.INSTANCE, HttpTimeoutKt::HttpTimeout$lambda$1)

private final val supportsRequestTimeout: Boolean
   private final get() {
      return !URLProtocolKt.isWebsocket(`$this$supportsRequestTimeout`.getUrl().getProtocol())
         && `$this$supportsRequestTimeout`.getBody() !is ClientUpgradeContent
         && `$this$supportsRequestTimeout`.getBody() !is SSEClientContent;
   }


private fun CoroutineScope.applyRequestTimeout(request: HttpRequestBuilder, requestTimeout: Long?) {
   if (requestTimeout != null && requestTimeout != java.lang.Long.MAX_VALUE) {
      request.getExecutionContext().invokeOnCompletion(HttpTimeoutKt::applyRequestTimeout$lambda$0);
      return;
   }
}

public fun HttpRequestBuilder.timeout(block: (HttpTimeoutConfig) -> Unit) {
   val var10001: HttpClientEngineCapability = HttpTimeoutCapability.INSTANCE;
   val var2: HttpTimeoutConfig = new HttpTimeoutConfig(null, null, null, 7, null);
   block.invoke(var2);
   `$this$timeout`.setCapability(var10001, var2);
}

public fun ConnectTimeoutException(request: HttpRequestData, cause: Throwable? = null): ConnectTimeoutException {
   var var2: HttpTimeoutConfig;
   var var10000: ConnectTimeoutException;
   var var10002: StringBuilder;
   label11: {
      var10000 = new ConnectTimeoutException;
      var10002 = new StringBuilder().append("Connect timeout has expired [url=").append(request.getUrl()).append(", connect_timeout=");
      var2 = request.getCapabilityOrNull(HttpTimeoutCapability.INSTANCE);
      if (var2 != null) {
         var2 = var2.getConnectTimeoutMillis();
         if (var2 != null) {
            break label11;
         }
      }

      var2 = "unknown";
   }

   var10000./* $VF: Unable to resugar constructor */<init>(var10002.append(var2).append(" ms]").toString(), cause);
   return var10000;
}

@JvmSynthetic
fun `ConnectTimeoutException$default`(var0: HttpRequestData, var1: java.lang.Throwable, var2: Int, var3: Any): ConnectTimeoutException {
   if ((var2 and 2) != 0) {
      var1 = null;
   }

   return ConnectTimeoutException(var0, var1);
}

public fun ConnectTimeoutException(url: String, timeout: Long?, cause: Throwable? = null): ConnectTimeoutException {
   val var10000: ConnectTimeoutException = new ConnectTimeoutException;
   val var10002: StringBuilder = new StringBuilder().append("Connect timeout has expired [url=").append(url).append(", connect_timeout=");
   var var10003: Any = timeout;
   if (timeout == null) {
      var10003 = "unknown";
   }

   var10000./* $VF: Unable to resugar constructor */<init>(var10002.append(var10003).append(" ms]").toString(), cause);
   return var10000;
}

@JvmSynthetic
fun `ConnectTimeoutException$default`(var0: java.lang.String, var1: java.lang.Long, var2: java.lang.Throwable, var3: Int, var4: Any): ConnectTimeoutException {
   if ((var3 and 4) != 0) {
      var2 = null;
   }

   return ConnectTimeoutException(var0, var1, var2);
}

public fun SocketTimeoutException(request: HttpRequestData, cause: Throwable? = null): SocketTimeoutException {
   val var10000: StringBuilder = new StringBuilder().append("Socket timeout has expired [url=").append(request.getUrl()).append(", socket_timeout=");
   val var10001: HttpTimeoutConfig = request.getCapabilityOrNull(HttpTimeoutCapability.INSTANCE);
   if (var10001 != null) {
      val var2: java.lang.Long = var10001.getSocketTimeoutMillis();
      if (var2 != null) {
         return TimeoutExceptionsKt.SocketTimeoutException(var10000.append(var2).append("] ms").toString(), cause);
      }
   }

   return TimeoutExceptionsKt.SocketTimeoutException(var10000.append("unknown").append("] ms").toString(), cause);
}

@JvmSynthetic
fun `SocketTimeoutException$default`(var0: HttpRequestData, var1: java.lang.Throwable, var2: Int, var3: Any): SocketTimeoutException {
   if ((var2 and 2) != 0) {
      var1 = null;
   }

   return SocketTimeoutException(var0, var1);
}

@InternalAPI
public fun convertLongTimeoutToIntWithInfiniteAsZero(timeout: Long): Int {
   return if (timeout == java.lang.Long.MAX_VALUE)
      0
      else
      (if (timeout < -2147483648L) Integer.MIN_VALUE else (if (timeout > 2147483647L) Integer.MAX_VALUE else (int)timeout));
}

@InternalAPI
public fun convertLongTimeoutToLongWithInfiniteAsZero(timeout: Long): Long {
   return if (timeout == java.lang.Long.MAX_VALUE) 0L else timeout;
}

@PublishedApi
internal inline fun <T> unwrapRequestTimeoutException(block: () -> Any): Any {
   try {
      return (T)block.invoke();
   } catch (var3: CancellationException) {
      throw ExceptionUtilsJvmKt.unwrapCancellationException(var3);
   }
}

fun ClientPluginBuilder.`HttpTimeout$lambda$1`(): Unit {
   `$this$createClientPlugin`.on(
      Send.INSTANCE,
      new 1(
         (`$this$createClientPlugin`.getPluginConfig() as HttpTimeoutConfig).getRequestTimeoutMillis(),
         (`$this$createClientPlugin`.getPluginConfig() as HttpTimeoutConfig).getConnectTimeoutMillis(),
         (`$this$createClientPlugin`.getPluginConfig() as HttpTimeoutConfig).getSocketTimeoutMillis(),
         null
      )
   );
   return Unit.INSTANCE;
}

fun `HttpTimeout$lambda$1$hasNotNullTimeouts`(
   requestTimeoutMillis: java.lang.Long, connectTimeoutMillis: java.lang.Long, socketTimeoutMillis: java.lang.Long, supportsRequestTimeout: Boolean
): Boolean {
   return supportsRequestTimeout && requestTimeoutMillis != null || connectTimeoutMillis != null || socketTimeoutMillis != null;
}

fun `applyRequestTimeout$lambda$0`(`$killer`: Job, it: java.lang.Throwable): Unit {
   Job.DefaultImpls.cancel$default(`$killer`, null, 1, null);
   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$getLOGGER$p`(): Logger {
   return LOGGER;
}

@JvmSynthetic
fun `access$getSupportsRequestTimeout`(`$receiver`: HttpRequestBuilder): Boolean {
   return getSupportsRequestTimeout(`$receiver`);
}

@JvmSynthetic
fun `access$HttpTimeout$lambda$1$hasNotNullTimeouts`(
   requestTimeoutMillis: java.lang.Long, connectTimeoutMillis: java.lang.Long, socketTimeoutMillis: java.lang.Long, supportsRequestTimeout: Boolean
): Boolean {
   return HttpTimeout$lambda$1$hasNotNullTimeouts(requestTimeoutMillis, connectTimeoutMillis, socketTimeoutMillis, supportsRequestTimeout);
}

@JvmSynthetic
fun `access$applyRequestTimeout`(`$receiver`: CoroutineScope, request: HttpRequestBuilder, requestTimeout: java.lang.Long) {
   applyRequestTimeout(`$receiver`, request, requestTimeout);
}
