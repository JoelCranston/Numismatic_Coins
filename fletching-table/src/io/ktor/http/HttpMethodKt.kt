package io.ktor.http

import io.ktor.utils.io.InternalAPI

private final val REQUESTS_WITHOUT_BODY: Set<HttpMethod> =
   SetsKt.setOf(new HttpMethod[]{HttpMethod.Companion.getGet(), HttpMethod.Companion.getHead(), HttpMethod.Companion.getOptions(), new HttpMethod("TRACE")})

@InternalAPI
public final val supportsRequestBody: Boolean
   public final get() {
      return !REQUESTS_WITHOUT_BODY.contains(`$this$supportsRequestBody`);
   }

