package io.ktor.client.call

import io.ktor.http.Url

/** @deprecated */
@Deprecated(message = "This exception is deprecated, use UnsupportedContentTypeException instead.", replaceWith = @ReplaceWith(expression = "UnsupportedContentTypeException(content)", imports = []), level = DeprecationLevel.WARNING)
public class UnsupportedUpgradeProtocolException(url: Url) : IllegalArgumentException("Unsupported upgrade protocol exception: $url")
