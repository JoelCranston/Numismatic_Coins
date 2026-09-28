@file:SourceDebugExtension(["SMAP\nDefaultResponseValidation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultResponseValidation.kt\nio/ktor/client/plugins/DefaultResponseValidationKt\n+ 2 Attributes.kt\nio/ktor/util/AttributesKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,125:1\n21#2:126\n69#3:127\n84#3,8:128\n*S KotlinDebug\n*F\n+ 1 DefaultResponseValidation.kt\nio/ktor/client/plugins/DefaultResponseValidationKt\n*L\n16#1:126\n16#1:127\n16#1:128,8\n*E\n"])

package io.ktor.client.plugins

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultResponseValidationKt.addDefaultResponseValidation.1.1
import io.ktor.util.AttributeKey
import kotlin.jvm.internal.SourceDebugExtension
import org.slf4j.Logger

private final val ValidateMark: AttributeKey<Unit>
private final val LOGGER: Logger
private const val NO_RESPONSE_TEXT: String = "<no response text provided>"
private const val BODY_FAILED_DECODING: String = "<body failed decoding>"
private const val DEPRECATED_EXCEPTION_CTOR: String = "Please, provide response text in constructor"

public fun HttpClientConfig<*>.addDefaultResponseValidation() {
   HttpCallValidatorKt.HttpResponseValidator(`$this$addDefaultResponseValidation`, DefaultResponseValidationKt::addDefaultResponseValidation$lambda$0);
}

fun `addDefaultResponseValidation$lambda$0`(`$this_addDefaultResponseValidation`: HttpClientConfig, `$this$HttpResponseValidator`: HttpCallValidatorConfig): Unit {
   `$this$HttpResponseValidator`.setExpectSuccess$ktor_client_core(`$this_addDefaultResponseValidation`.getExpectSuccess());
   `$this$HttpResponseValidator`.validateResponse(new 1(null));
   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$getLOGGER$p`(): Logger {
   return LOGGER;
}

@JvmSynthetic
fun `access$getValidateMark$p`(): AttributeKey {
   return ValidateMark;
}
