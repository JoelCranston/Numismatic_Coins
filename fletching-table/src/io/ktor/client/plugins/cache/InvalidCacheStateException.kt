package io.ktor.client.plugins.cache

import io.ktor.http.Url

public class InvalidCacheStateException(requestUrl: Url) : IllegalStateException("The entry for url: $requestUrl was removed from cache")
