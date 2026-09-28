package io.ktor.client.plugins

import io.ktor.utils.io.KtorDsl

@KtorDsl
public class HttpRedirectConfig {
   public final var checkHttpMethod: Boolean = true
   public final var allowHttpsDowngrade: Boolean
}
