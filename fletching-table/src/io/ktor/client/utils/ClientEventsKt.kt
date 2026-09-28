package io.ktor.client.utils

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.events.EventDefinition

public final val HttpRequestCreated: EventDefinition<HttpRequestBuilder> = new EventDefinition()
public final val HttpRequestIsReadyForSending: EventDefinition<HttpRequestBuilder> = new EventDefinition()
public final val HttpResponseReceived: EventDefinition<HttpResponse> = new EventDefinition()
public final val HttpResponseReceiveFailed: EventDefinition<HttpResponseReceiveFail> = new EventDefinition()
public final val HttpResponseCancelled: EventDefinition<HttpResponse> = new EventDefinition()
