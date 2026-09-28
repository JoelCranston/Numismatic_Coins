@file:JvmName(name = "DoubleReceivePluginKt")

@file:SourceDebugExtension(["SMAP\nSaveBody.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SaveBody.kt\nio/ktor/client/plugins/DoubleReceivePluginKt\n+ 2 Attributes.kt\nio/ktor/util/AttributesKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,169:1\n21#2:170\n21#2:180\n69#3:171\n84#3,8:172\n69#3:181\n84#3,8:182\n*S KotlinDebug\n*F\n+ 1 SaveBody.kt\nio/ktor/client/plugins/DoubleReceivePluginKt\n*L\n23#1:170\n24#1:180\n23#1:171\n23#1:172,8\n24#1:181\n24#1:182,8\n*E\n"])

package io.ktor.client.plugins

import io.ktor.client.plugins.DoubleReceivePluginKt.SaveBody.1.1
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.ClientPluginBuilder
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpReceivePipeline
import io.ktor.util.AttributeKey
import io.ktor.util.logging.KtorSimpleLoggerJvmKt
import kotlin.jvm.internal.SourceDebugExtension
import org.slf4j.Logger

private final val SKIP_SAVE_BODY: AttributeKey<Unit>
private final val RESPONSE_BODY_SAVED: AttributeKey<Unit>

private final val LOGGER: Logger
   private final get() {
      return LOGGER$delegate.getValue() as Logger;
   }


internal final val SaveBody: ClientPlugin<Unit>

@Deprecated(
   message = "This plugin is no longer needed.\nThis API is deprecated and will be removed in Ktor 4.0.0"
)
public final val SaveBodyPlugin: ClientPlugin<SaveBodyPluginConfig>

public final val isSaved: Boolean
   public final get() {
      return `$this$isSaved`.getCall().getAttributes().contains(RESPONSE_BODY_SAVED);
   }


internal fun HttpRequestBuilder.skipSaveBody() {
   `$this$skipSaveBody`.getAttributes().put(SKIP_SAVE_BODY, Unit.INSTANCE);
}

@Deprecated(message = "Skipping of body saving for a specific request is no longer allowed.\nUse client.prepareRequest(...).execute { ... } syntax to prevent saving the body in memory.\n\nThis API is deprecated and will be removed in Ktor 4.0.0\nIf you were relying on this functionality, share your use case by commenting on this issue: https://youtrack.jetbrains.com/issue/KTOR-8367/")
public fun HttpRequestBuilder.skipSavingBody() {
   getLOGGER()
      .warn(
         "Skipping of body saving for a specific request is no longer allowed.\nUse client.prepareRequest(...).execute { ... } syntax to prevent saving the body in memory.\n\nThis API is deprecated and will be removed in Ktor 4.0.0\nIf you were relying on this functionality, share your use case by commenting on this issue: https://youtrack.jetbrains.com/issue/KTOR-8367/"
      );
}

fun `LOGGER_delegate$lambda$0`(): Logger {
   return KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.SaveBody");
}

fun ClientPluginBuilder.`SaveBody$lambda$0`(): Unit {
   `$this$createClientPlugin`.getClient().getReceivePipeline().intercept(HttpReceivePipeline.Phases.getBefore(), new 1(null));
   return Unit.INSTANCE;
}

fun ClientPluginBuilder.`SaveBodyPlugin$lambda$0`(): Unit {
   if ((`$this$createClientPlugin`.getPluginConfig() as SaveBodyPluginConfig).getDisabled()) {
      getLOGGER()
         .warn(
            "It is no longer possible to disable body saving for all requests. Use client.prepareRequest(...).execute { ... } syntax to prevent saving the body in memory.\n\nThis API is deprecated and will be removed in Ktor 4.0.0\nIf you were relying on this functionality, share your use case by commenting on this issue: https://youtrack.jetbrains.com/issue/KTOR-8367/"
         );
   } else {
      getLOGGER()
         .warn(
            "The SaveBodyPlugin plugin is deprecated and can be safely removed. Request bodies are now saved in memory by default for all non-streaming responses."
         );
   }

   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$getSKIP_SAVE_BODY$p`(): AttributeKey {
   return SKIP_SAVE_BODY;
}

@JvmSynthetic
fun `access$getLOGGER`(): Logger {
   return getLOGGER();
}

@JvmSynthetic
fun `access$getRESPONSE_BODY_SAVED$p`(): AttributeKey {
   return RESPONSE_BODY_SAVED;
}
