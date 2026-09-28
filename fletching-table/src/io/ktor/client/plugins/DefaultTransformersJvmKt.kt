package io.ktor.client.plugins

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultTransformersJvmKt.platformResponseDefaultTransformers.1
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.http.ContentType
import io.ktor.http.content.OutgoingContent

internal fun HttpClient.platformResponseDefaultTransformers() {
   `$this$platformResponseDefaultTransformers`.getResponsePipeline().intercept(HttpResponsePipeline.Phases.getParse(), new 1(null));
}

internal fun platformRequestDefaultTransform(contentType: ContentType?, context: HttpRequestBuilder, body: Any): OutgoingContent? {
   return new io.ktor.client.plugins.DefaultTransformersJvmKt.platformRequestDefaultTransform.1(context, contentType, body) as? OutgoingContent;
}
